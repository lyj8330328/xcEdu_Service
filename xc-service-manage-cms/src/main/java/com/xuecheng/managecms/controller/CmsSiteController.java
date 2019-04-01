package com.xuecheng.managecms.controller;

import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.request.QuerySiteRequest;
import com.xuecheng.framework.domain.cms.response.CmsSiteResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 98050
 * @Time: 2019-03-30 22:56
 * @Feature:
 */
@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSiteControllerApi {

    @Override
    public QueryResponseResult findList(int page, int size, QuerySiteRequest querySiteRequest) {
        return null;
    }

    @Override
    public CmsSiteResult add(CmsSite cmsSite) {
        return null;
    }

    @Override
    public CmsSiteResult findById(String id) {
        return null;
    }

    @Override
    public CmsSiteResult update(String id, CmsPage cmsPage) {
        return null;
    }

    @Override
    public ResponseResult delete(String id) {
        return null;
    }
}
