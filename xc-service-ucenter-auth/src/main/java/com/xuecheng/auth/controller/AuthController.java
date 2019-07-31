package com.xuecheng.auth.controller;

import com.xuecheng.api.ucenter.AuthControllerApi;
import com.xuecheng.auth.service.AuthService;
import com.xuecheng.framework.domain.ucenter.ext.AuthToken;
import com.xuecheng.framework.domain.ucenter.request.LoginRequest;
import com.xuecheng.framework.domain.ucenter.response.AuthCode;
import com.xuecheng.framework.domain.ucenter.response.JwtResult;
import com.xuecheng.framework.domain.ucenter.response.LoginResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: 98050
 * @Time: 2019-07-22 20:51
 * @Feature:
 */
@RestController
public class AuthController implements AuthControllerApi {

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    /**
     * maxAge 可以为正数，表示此cookie从创建到过期所能存在的时间，以秒为单位，此cookie会存储到客户端电脑，以cookie文件形式保存，不论关闭浏览器或关闭电脑，直到时间到才会过期。
     *
     * 可以为负数，表示此cookie只是存储在浏览器内存里，只要关闭浏览器，此cookie就会消失。maxAge默认值为-1。
     *
     * 还可以为0，表示从客户端电脑或浏览器内存中删除此cookie。
     */
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    @Override
    public LoginResult login(LoginRequest loginRequest) {
        if (loginRequest == null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_REQUEST_ERROR);
        }
        //1.校验账号是否输入
        if (StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        //2.校验密码是否输入
        if (StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        //3.获取token
        AuthToken authToken = this.authService.login(username, password, clientId, clientSecret);
        //4.将token写入cookie中
        String jti = authToken.getJti();
        saveTokenInCookie(jti);
        return new LoginResult(CommonCode.SUCCESS,jti);
    }

    private void saveTokenInCookie(String jti) {
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        //将令牌添加到cookie中，最后一个参数设置为false，表示允许浏览器获取
        if (response != null) {
            CookieUtil.addCookie(response, cookieDomain, "/", "uid", jti, cookieMaxAge, false);
        }else {
            ExceptionCast.cast(AuthCode.AUTH_COOKIE_SET_ERROR);
        }
    }

    /**
     * 用户退出
     * @return
     */
    @Override
    public ResponseResult logout() {
        //1.取出jti
        String jti = getTokenFromCookie();
        //2.删除redis中的令牌
        this.authService.delJwtInRedis(jti);
        //3.删除cookie中的令牌
        clearCookie(jti);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    private void clearCookie(String jti) {
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        CookieUtil.addCookie(Objects.requireNonNull(response), cookieDomain, "/", "uid", jti, 0, false);
    }

    /**
     * 根据cookie中的短令牌查询redis中的长令牌
     * @return
     */
    @Override
    public JwtResult getJwt() {
        //获取cookie中的令牌
        String jti = getTokenFromCookie();
        if (StringUtils.isEmpty(jti)){
            return new JwtResult(CommonCode.FAIL, null);
        }
        //根据令牌从redis查询jwt
        AuthToken authToken = this.authService.getJwtFromRedis(jti);
        if (authToken == null){
            return new JwtResult(CommonCode.FAIL, null);
        }
        return new JwtResult(CommonCode.SUCCESS, authToken.getAccessToken());
    }

    private String getTokenFromCookie() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        return map.get("uid");
    }
}
