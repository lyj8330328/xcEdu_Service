package com.xuecheng.sys.dictionary.service.impl;

import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.framework.domain.system.response.SysDictionaryCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.sys.dictionary.dao.SysDictionaryRepository;
import com.xuecheng.sys.dictionary.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-04-08 22:10
 * @Feature:
 */
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    private final SysDictionaryRepository sysDictionaryRepository;

    @Autowired
    public SysDictionaryServiceImpl(SysDictionaryRepository sysDictionaryRepository) {
        this.sysDictionaryRepository = sysDictionaryRepository;
    }

    @Override
    public SysDictionary findDictionaryByType(String type) {

        SysDictionary dictionary = this.sysDictionaryRepository.findByDType(type);
        if (dictionary == null){
            ExceptionCast.cast(SysDictionaryCode.SYS_DICTIONARY_IS_NULL);
        }
        return dictionary;
    }
}
