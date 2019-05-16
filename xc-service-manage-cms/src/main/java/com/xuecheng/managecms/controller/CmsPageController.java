package com.xuecheng.managecms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.managecms.service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 98050
 * @Time: 2019-03-19 19:06
 * @Feature:
 */
@RestController
public class CmsPageController implements CmsPageControllerApi {

    private final CmsService cmsService;

    @Autowired
    public CmsPageController(CmsService cmsService) {
        this.cmsService = cmsService;
    }

    @Override
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        return cmsService.queryByPage(page, size, queryPageRequest);
    }

    @Override
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsService.add(cmsPage);
    }

    @Override
    public CmsPageResult findById(@PathVariable String id) {
        CmsPage cmsPage = cmsService.findById(id);
        if (cmsPage != null){
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }else {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
    }

    @Override
    public CmsPageResult update(@PathVariable String id, @RequestBody CmsPage cmsPage) {
        return cmsService.update(id,cmsPage);
    }

    @Override
    public ResponseResult delete(@PathVariable String id) {
        return cmsService.delete(id);
    }

    @Override
    public ResponseResult post(@PathVariable("pageId") String pageId) {
        return this.cmsService.postPage(pageId);
    }

    @Override
    public ResponseResult postRollBack(@PathVariable("pageId") String pageId) {
        return this.cmsService.postPageRollBack(pageId);
    }
}