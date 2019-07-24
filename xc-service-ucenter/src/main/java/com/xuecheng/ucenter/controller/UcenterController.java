package com.xuecheng.ucenter.controller;

import com.xuecheng.api.ucenter.UcenterControllerApi;
import com.xuecheng.framework.domain.ucenter.ext.XcUserExt;
import com.xuecheng.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 98050
 * @Time: 2019-07-24 23:15
 * @Feature:
 */
@RestController
public class UcenterController implements UcenterControllerApi {

    private final UserService service;

    @Autowired
    public UcenterController(UserService service) {
        this.service = service;
    }

    @Override
    public XcUserExt getUserExt(@RequestParam("username") String username) {
        return this.service.findXcUserExtByUserName(username);
    }
}
