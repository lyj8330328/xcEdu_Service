package com.xuecheng.managecms;

import com.xuecheng.managecms.service.CmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 98050
 * @Time: 2019-03-29 18:59
 * @Feature: 页面静态化测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageStaticTest {

    @Autowired
    private CmsService cmsService;


    @Test
    public void pageStatic(){
        System.out.println(cmsService.getPageHtml("5c9df9dadff2be27386be51b"));
    }
}
