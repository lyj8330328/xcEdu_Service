package com.xuecheng.api.ucenter;

import com.xuecheng.framework.domain.ucenter.ext.XcUserExt;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 98050
 * @Time: 2019-07-24 22:46
 * @Feature:
 */
@Api(value = "用户中心",description = "用户管理中心")
@RequestMapping("/ucenter")
public interface UcenterControllerApi {

    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/getUser")
    XcUserExt getUserExt(String username);
}
