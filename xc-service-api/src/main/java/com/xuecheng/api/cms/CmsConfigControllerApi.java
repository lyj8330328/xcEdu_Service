package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.request.QuerySiteRequest;
import com.xuecheng.framework.domain.cms.response.CmsConfigResult;
import com.xuecheng.framework.domain.cms.response.CmsSiteResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 98050
 * @Time: 2019-03-28 16:44
 * @Feature: cms配置管理接口
 */
@RequestMapping("/cms/config")
@Api(value = "cms数据模型管理接口",description = "cms配置信息管理接口，提供对配置信息的CRUD")
public interface CmsConfigControllerApi {

    /**
     * 根据id查询CMS数据模型
     * @param id
     * @return
     */
    @ApiOperation("根据id查询CMS配置信息")
    @GetMapping("/getModel/{id}")
    CmsConfig getModel(@PathVariable("id") String id);



    /**
     * 数据模型分页查询
     * @param page 页码
     * @param size 页大小
     * @return
     */
    @ApiOperation("分页查询数据模型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "页大小",required = true,paramType = "path",dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    @GetMapping("/list/{page}/{size}")
    QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size);

    /**
     * 数据模型新增
     * @param cmsConfig
     * @return
     */
    @ApiOperation("数据模型添加")
    @PostMapping("/add")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsConfigResult add(@RequestBody CmsConfig cmsConfig);

    /**
     * 根据id查询数据模型
     * @param id
     * @return
     */
    @ApiOperation("根据Id查询数据模型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "数据模型Id",required = true,paramType = "path",dataType = "String")
    })
    @GetMapping("/get/{id}")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsConfigResult findById(@PathVariable String id);

    /**
     * 根据id修改数据模型
     * @param id
     * @param cmsPage
     * @return
     */
    @ApiOperation("修改数据模型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "数据模型Id",required = true,paramType = "path",dataType = "String")
    })
    @PutMapping("/update/{id}")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsConfigResult update(@PathVariable String id, @RequestBody CmsPage cmsPage);

    /**
     * 删除数据模型
     * @param id
     * @return
     */
    @ApiOperation("删除数据模型")
    @DeleteMapping("/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "数据模型Id",required = true,paramType = "path",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    ResponseResult delete(@PathVariable String id);
}
