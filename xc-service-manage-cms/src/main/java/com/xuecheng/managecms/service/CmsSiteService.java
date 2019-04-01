package com.xuecheng.managecms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.request.QuerySiteRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.domain.cms.response.CmsSiteResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @Author: 98050
 * @Time: 2019-03-30 22:57
 * @Feature:
 */
public interface CmsSiteService {

    /**
     * 站点分页查询
     * @param page 页码
     * @param size 页大小
     * @param querySiteRequest 具体请求参数
     * @return 分页列表
     */
    QueryResponseResult queryByPage(int page, int size, QuerySiteRequest querySiteRequest);


    /**
     * 新增站点
     * @param cmsSite
     * @return 操作结果
     */
    CmsSiteResult add(CmsSite cmsSite);

    /**
     * 根据id查询站点
     * @param id
     * @return
     */
    CmsSite findById(String id);

    /**
     * 更新站点
     * @param id 站点id
     * @param cmsSite 站点对象
     * @return
     */
    CmsSiteResult update(String id, CmsSite cmsSite);

    /**
     * 删除页面
     * @param id
     * @return
     */
    ResponseResult delete(String id);
}
