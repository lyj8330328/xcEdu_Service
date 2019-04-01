package com.xuecheng.managecms.service.impl;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.request.QuerySiteRequest;
import com.xuecheng.framework.domain.cms.response.CmsSiteResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.managecms.service.CmsSiteService;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-03-30 23:05
 * @Feature:
 */
@Service
public class CmsSiteServiceImpl implements CmsSiteService {
    @Override
    public QueryResponseResult queryByPage(int page, int size, QuerySiteRequest querySiteRequest) {
        return null;
    }

    @Override
    public CmsSiteResult add(CmsSite cmsSite) {
        return null;
    }

    @Override
    public CmsSite findById(String id) {
        return null;
    }

    @Override
    public CmsSiteResult update(String id, CmsSite cmsSite) {
        return null;
    }

    @Override
    public ResponseResult delete(String id) {
        return null;
    }
}
