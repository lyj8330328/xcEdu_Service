package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
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
    @GetMapping("/list/{page}/{size}")
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "页大小",required = true,paramType = "path",dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功")
    })
    QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest);

    /**
     * 页面新增
     * @param cmsPage
     * @return
     */
    @ApiOperation("页面添加")
    @PostMapping("/add")
    CmsPageResult add(@RequestBody CmsPage cmsPage);
}