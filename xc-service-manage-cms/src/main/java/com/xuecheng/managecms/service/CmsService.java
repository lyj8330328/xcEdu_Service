package com.xuecheng.managecms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * @Author: 98050
 * @Time: 2019-03-20 14:54
 * @Feature: CMS服务端
 */
public interface CmsService {

    /**
     * 页面分页查询
     * @param page 页码
     * @param size 页大小
     * @param queryPageRequest 具体请求参数
     * @return 分页列表
     */
    QueryResponseResult queryByPage(int page, int size, QueryPageRequest queryPageRequest);


    /**
     * 新增页面
     * @param cmsPage cms对象
     * @return 操作结果
     */
    CmsPageResult add(CmsPage cmsPage);
}
