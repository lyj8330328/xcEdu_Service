package com.xuecheng.ucenter.dao;

import com.xuecheng.framework.domain.ucenter.XcUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 98050
 * @Time: 2019-07-24 22:50
 * @Feature:
 */
public interface XcUserRepository extends JpaRepository<XcUser,String> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    XcUser findXcUserByUsername(String username);
}
