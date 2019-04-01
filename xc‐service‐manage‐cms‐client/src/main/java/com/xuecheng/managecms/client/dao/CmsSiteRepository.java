package com.xuecheng.managecms.client.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-03-30 22:57
 * @Feature:
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
