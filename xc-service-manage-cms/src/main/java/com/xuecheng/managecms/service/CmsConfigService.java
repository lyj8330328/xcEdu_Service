package com.xuecheng.managecms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.response.CmsConfigResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @Author: 98050
 * @Time: 2019-03-28 16:48
 * @Feature:
 */
public interface CmsConfigService {

    /**
     * 根据id查询数据模型
     * @param id
     * @return
     */
    CmsConfig getConfigById(String id);



    /**
     * 数据模型分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页列表
     */
    QueryResponseResult queryByPage(int page, int size);


    /**
     * 新增数据模型
     * @param cmsConfig
     * @return 操作结果
     */
    CmsConfigResult add(CmsConfig cmsConfig);

    /**
     * 根据id查询数据模型
     * @param id
     * @return
     */
    CmsConfig findById(String id);

    /**
     * 更新数据模型
     * @param id
     * @param cmsConfig
     * @return
     */
    CmsConfigResult update(String id, CmsConfig cmsConfig);

    /**
     * 删除页面
     * @param id
     * @return
     */
    ResponseResult delete(String id);
}
