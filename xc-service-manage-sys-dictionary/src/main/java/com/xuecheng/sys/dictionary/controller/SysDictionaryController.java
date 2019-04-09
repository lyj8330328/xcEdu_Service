package com.xuecheng.sys.dictionary.controller;

import com.xuecheng.api.system.SysDictionaryControllerApi;
import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.sys.dictionary.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 98050
 * @Time: 2019-04-08 22:05
 * @Feature:
 */
@RestController
@RequestMapping("sys")
public class SysDictionaryController implements SysDictionaryControllerApi {

    private final SysDictionaryService sysDictionaryService;

    @Autowired
    public SysDictionaryController(SysDictionaryService sysDictionaryService) {
        this.sysDictionaryService = sysDictionaryService;
    }

    @Override
    @GetMapping("/dictionary/{type}")
    public SysDictionary getByType(@PathVariable("type") String type) {
        return this.sysDictionaryService.findDictionaryByType(type);
    }
}
