package com.xuecheng.sys.dictionary.service;

import com.xuecheng.framework.domain.system.SysDictionary;

/**
 * @Author: 98050
 * @Time: 2019-04-08 22:09
 * @Feature:
 */
public interface SysDictionaryService {

    /**
     * 根据类型查询数据字典
     * @param type
     * @return
     */
    SysDictionary findDictionaryByType(String type);
}
