package com.xuecheng.api.ucenter;

import com.xuecheng.framework.domain.ucenter.request.LoginRequest;
import com.xuecheng.framework.domain.ucenter.response.LoginResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 98050
 * @Time: 2019-07-20 16:39
 * @Feature:
 */
@Api(value = "用户认证",description = "用户认证接口")
@RequestMapping("/")
public interface AuthControllerApi {

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/userLogin")
    LoginResult login(LoginRequest loginRequest);

    /**
     * 用户退出
     * @return
     */
    @ApiOperation("退出")
    @PostMapping("/userLogout")
    ResponseResult logout();
}
