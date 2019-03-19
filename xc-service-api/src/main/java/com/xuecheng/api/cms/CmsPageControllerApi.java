package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * @Author: 98050
 * @Time: 2019-03-19 15:00
 * @Feature: 页面查询接口
 */
public interface CmsPageControllerApi {
    /**
     * 页面分页查询
     * @param page 页码
     * @param size 页大小
     * @param queryPageRequest  查询参数
     * @return
     */
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);
}