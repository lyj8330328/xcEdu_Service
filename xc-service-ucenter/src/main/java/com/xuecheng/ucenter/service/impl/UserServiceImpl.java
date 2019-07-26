package com.xuecheng.ucenter.service.impl;

import com.xuecheng.framework.domain.ucenter.XcCompanyUser;
import com.xuecheng.framework.domain.ucenter.XcUser;
import com.xuecheng.framework.domain.ucenter.ext.XcUserExt;
import com.xuecheng.framework.domain.ucenter.response.UcenterCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.ucenter.dao.XcCompanyUserRepository;
import com.xuecheng.ucenter.dao.XcUserRepository;
import com.xuecheng.ucenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-07-24 22:59
 * @Feature:
 */
@Service
public class UserServiceImpl implements UserService {

    private final XcUserRepository xcUserRepository;

    private final XcCompanyUserRepository xcCompanyUserRepository;

    @Autowired
    public UserServiceImpl(XcUserRepository xcUserRepository, XcCompanyUserRepository xcCompanyUserRepository) {
        this.xcUserRepository = xcUserRepository;
        this.xcCompanyUserRepository = xcCompanyUserRepository;
    }


    public XcUser findXcUserByUserName(String username){
        return this.xcUserRepository.findXcUserByUsername(username);
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public XcUserExt findXcUserExtByUserName(String username) {
        XcUser user = this.findXcUserByUserName(username);
        if (user == null){
            return null;
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(user, xcUserExt);
        //获取用户id
        String userId = user.getId();
        //查询用户所属公司
        XcCompanyUser xcCompanyUser = this.xcCompanyUserRepository.findXcCompanyUserByUserId(userId);
        if (xcCompanyUser != null){
            String companyId = xcCompanyUser.getCompanyId();
            xcUserExt.setCompanyId(companyId);
        }
        return xcUserExt;
    }
}
