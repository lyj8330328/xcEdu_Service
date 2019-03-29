package com.xuecheng.managecms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-03-29 18:02
 * @Feature:
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {
}
