package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.request.QuerySiteRequest;
import com.xuecheng.framework.domain.cms.response.CmsSiteResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 98050
 * @Time: 2019-03-30 22:38
 * @Feature: cms站点管理
 */
@RequestMapping("/cms/site")
@Api(value = "cms站点管理接口",description = "cms站点管理接口，提供对站点的CRUD")
public interface CmsSiteControllerApi {

    /**
     * 站点分页查询
     * @param page 页码
     * @param size 页大小
     * @param querySiteRequest  查询参数
     * @return
     */
    @ApiOperation("分页查询站点列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "页大小",required = true,paramType = "path",dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    @GetMapping("/list/{page}/{size}")
    QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QuerySiteRequest querySiteRequest);

    /**
     * 站点新增
     * @param cmsSite
     * @return
     */
    @ApiOperation("站点添加")
    @PostMapping("/add")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsSiteResult add(@RequestBody CmsSite cmsSite);

    /**
     * 根据id查询站点
     * @param id
     * @return
     */
    @ApiOperation("根据Id查询站点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "站点Id",required = true,paramType = "path",dataType = "String")
    })
    @GetMapping("/get/{id}")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsSiteResult findById(@PathVariable String id);

    /**
     * 根据id修改站点
     * @param id
     * @param cmsPage
     * @return
     */
    @ApiOperation("修改站点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "站点Id",required = true,paramType = "path",dataType = "String")
    })
    @PutMapping("/update/{id}")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsSiteResult update(@PathVariable String id, @RequestBody CmsPage cmsPage);

    /**
     * 删除站点
     * @param id
     * @return
     */
    @ApiOperation("删除站点")
    @DeleteMapping("/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "站点Id",required = true,paramType = "path",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    ResponseResult delete(@PathVariable String id);
}
