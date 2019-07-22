package com.xuecheng.auth.service;

import com.xuecheng.framework.domain.ucenter.ext.AuthToken;

/**
 * @Author: 98050
 * @Time: 2019-07-22 19:54
 * @Feature:
 */
public interface AuthService {

    /**
     * 用户登录
     * @param username 账号
     * @param password 密码
     * @param clientId 客户端id
     * @param clientSecret 客户端密码
     * @return
     */
    AuthToken login(String username,String password,String clientId,String clientSecret);
}
