package com.xuecheng.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.xuecheng.auth.service.AuthService;
import com.xuecheng.framework.client.XcServiceList;
import com.xuecheng.framework.domain.ucenter.ext.AuthToken;
import com.xuecheng.framework.domain.ucenter.response.AuthCode;
import com.xuecheng.framework.exception.ExceptionCast;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 98050
 * @Time: 2019-07-22 19:58
 * @Feature:
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${auth.tokenValiditySeconds}")
    private int tokenValiditySeconds;


    /**
     * 登录认证
     * @param username 账号
     * @param password 密码
     * @param clientId 客户端id
     * @param clientSecret 客户端密码
     * @return
     */
    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        //1.申请令牌
        AuthToken authToken = getToken(username,password,clientId,clientSecret);
        if (authToken == null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_GETTOKEN_FAIL);
        }
        //2.将token存储到redis中
        String jti = authToken.getJti();
        String content = JSON.toJSONString(authToken);
        boolean save = saveTokenInRedis(jti,content,tokenValiditySeconds);
        if (!save){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVE_FAIL);
        }
        return authToken;
    }

    private Boolean saveTokenInRedis(String jti, String content, int tokenValiditySeconds) {
        //1.key
        String key = "user_token:" + jti;
        //2.保存
        stringRedisTemplate.opsForValue().set(key, content, tokenValiditySeconds, TimeUnit.SECONDS);
        //3.获取过期时间
        Long expire = Objects.requireNonNull(stringRedisTemplate.getExpire(key));
        return expire > 0;
    }

    private AuthToken getToken(String username, String password, String clientId, String clientSecret) {
        //1.采用客户端负载均衡，从eureka获取认证服务的ip和端口
        ServiceInstance serviceInstance = loadBalancerClient.choose(XcServiceList.XC_SERVICE_UCENTER_AUTH);
        if (serviceInstance == null){
            LOGGER.error("choose an auth server fail");
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_AUTHSERVER_NOTFOUND);
        }
        //2.获取认证地址
        URI uri = serviceInstance.getUri();
        String authUrl = uri + "/auth/oauth/token";

        //3.构建请求头
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        String httpBasic =  getHttpBasic(clientId,clientSecret);
        headers.add("Authorization", httpBasic);
        //4.构建请求体
        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);
        //5.当遇到401或者400错误时不要抛出异常，应返回正常值
        HttpEntity<MultiValueMap<String,String>> multiValueMapHttpEntity = new HttpEntity<>(body,headers);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });
        //6.远程调用，申请令牌
        Map result = null;
        try {
            ResponseEntity<Map> entity = restTemplate.exchange(authUrl, HttpMethod.POST, multiValueMapHttpEntity, Map.class);
            result = entity.getBody();
        }catch (RestClientException e){
            e.printStackTrace();
            LOGGER.error("request oauth_token_password error: {}",e.getMessage());
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_GETTOKEN_FAIL);
        }
        if (result == null || result.get("access_token") == null || result.get("refresh_token") == null || result.get("jti") == null){

            String errorDescription = (String) result.get("error_description");
            if (errorDescription.equals("坏的凭证")){
                ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
            }else if (errorDescription.equals("Cannot pass null or empty values to constructor")){
                ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
            }
        }
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) result.get("access_token"));
        authToken.setRefreshToken((String) result.get("refresh_token"));
        authToken.setJti((String) result.get("jti"));
        return authToken;
    }

    private String getHttpBasic(String clientId, String clientSecret) {
        String result = clientId + ":" + clientSecret;
        byte[] encode = Base64Utils.encode(result.getBytes());
        return "Basic " + new String(encode);
    }
}
