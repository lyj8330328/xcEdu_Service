package com.xuecheng.ucenter.service;

import com.xuecheng.framework.domain.ucenter.ext.XcUserExt;

/**
 * @Author: 98050
 * @Time: 2019-07-24 22:57
 * @Feature:
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    XcUserExt findXcUserExtByUserName(String username);
}
