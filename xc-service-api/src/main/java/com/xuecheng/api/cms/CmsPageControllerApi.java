package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 98050
 * @Time: 2019-03-19 15:00
 * @Feature: 页面查询接口
 */
@RequestMapping("/cms/page")
@Api(value = "cms页面管理接口",description = "cms页面管理接口，提供对页面的CRUD")
public interface CmsPageControllerApi {
    /**
     * 页面分页查询
     * @param page 页码
     * @param size 页大小
     * @param queryPageRequest  查询参数
     * @return
     */
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "页大小",required = true,paramType = "path",dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    @GetMapping("/list/{page}/{size}")
    QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest);

    /**
     * 页面新增
     * @param cmsPage
     * @return
     */
    @ApiOperation("页面添加")
    @PostMapping("/add")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsPageResult add(@RequestBody CmsPage cmsPage);

    /**
     * 根据id查询页面
     * @param id
     * @return
     */
    @ApiOperation("根据Id查询页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "页面Id",required = true,paramType = "path",dataType = "String")
    })
    @GetMapping("/get/{id}")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsPageResult findById(@PathVariable String id);

    /**
     * 根据id修改页面
     * @param id
     * @param cmsPage
     * @return
     */
    @ApiOperation("修改页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "页面Id",required = true,paramType = "path",dataType = "String")
    })
    @PutMapping("/update/{id}")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    CmsPageResult update(@PathVariable String id,@RequestBody CmsPage cmsPage);

    /**
     * 删除页面
     * @param id
     * @return
     */
    @ApiOperation("删除页面")
    @DeleteMapping("/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "页面Id",required = true,paramType = "path",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    ResponseResult delete(@PathVariable String id);
}