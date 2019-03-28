package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 98050
 * @Time: 2019-03-28 16:44
 * @Feature: cms配置管理接口
 */
@RequestMapping("/cms/config")
@Api(value = "cms配置信息管理接口",description = "cms配置信息管理接口，提供对配置信息的CRUD")
public interface CmsConfigControllerApi {

    /**
     * 根据id查询CMS配置信息
     * @param id
     * @return
     */
    @ApiOperation("根据id查询CMS配置信息")
    @GetMapping("/getModel/{id}")
    CmsConfig getModel(@PathVariable("id") String id);
}
