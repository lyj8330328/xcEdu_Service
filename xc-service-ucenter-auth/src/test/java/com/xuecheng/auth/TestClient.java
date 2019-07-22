package com.xuecheng.auth;

import com.xuecheng.framework.client.XcServiceList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2019-07-20 16:51
 * @Feature:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestClient {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void test(){
        //采用客户端负载均衡，从eureka获取认证服务的ip和端口
        ServiceInstance serviceInstance = loadBalancerClient.choose(XcServiceList.XC_SERVICE_UCENTER_AUTH);
        URI uri = serviceInstance.getUri();
        String authUrl = uri + "/auth/oauth/token";

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        //1.http basic 认证
        String httpBasic =  getHttpBasic("XcWebApp","XcWebApp");
        headers.add("Authorization", httpBasic);
        //2.参数
        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", "admin");
        body.add("password", "123");

        HttpEntity<MultiValueMap<String,String>> multiValueMapHttpEntity = new HttpEntity<>(body,headers);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        //远程调用，申请令牌
        ResponseEntity<Map> entity = restTemplate.exchange(authUrl, HttpMethod.POST, multiValueMapHttpEntity, Map.class);
        Map map = entity.getBody();
        System.out.println(map);
    }

    private String getHttpBasic(String clientId, String clientSecret) {
        String result = clientId + ":" + clientSecret;
        byte[] encode = Base64Utils.encode(result.getBytes());
        return "Basic " + new String(encode);
    }
}
