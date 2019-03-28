package com.xuecheng.managecms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;

/**
 * @Author: 98050
 * @Time: 2019-03-28 16:48
 * @Feature:
 */
public interface CmsConfigService {

    /**
     * 根据id查询配置管理信息
     * @param id
     * @return
     */
    CmsConfig getConfigById(String id);
}
