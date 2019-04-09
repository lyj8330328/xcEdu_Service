package com.xuecheng.sys.dictionary.dao;

import com.xuecheng.framework.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-04-08 22:03
 * @Feature:
 */
public interface SysDictionaryRepository extends MongoRepository<SysDictionary,String> {

    /**
     * 根据类型查找
     * @param type
     * @return
     */
    SysDictionary findByDType(String type);
}
