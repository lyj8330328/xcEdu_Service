package com.xuecheng.managecms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-03-28 16:46
 * @Feature:
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {

    /**
     * 根据数据模型名称查询
     * @param name
     * @return
     */
    CmsConfig findByName(String name);
}
