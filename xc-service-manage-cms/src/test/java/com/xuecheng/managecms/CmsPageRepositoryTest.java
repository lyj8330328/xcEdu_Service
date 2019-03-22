package com.xuecheng.managecms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.managecms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 98050
 * @Time: 2019-03-20 14:12
 * @Feature: 测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 分页查询
     */
    @Test
    public void testFindPage(){
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> pages = cmsPageRepository.findAll(pageable);
        for (CmsPage cmsPage : pages){
            System.out.println(cmsPage);
        }
    }

    /**
     * 添加
     */
    @Test
    public void add(){
        //定义实体类
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);
    }

    @Test
    public void get(){
        System.out.println(cmsPageRepository.findById("5c91de185019c208f80da99e"));
    }

    @Test
    public void delete(){
        cmsPageRepository.deleteById("5c91de185019c208f80da99e");
    }


    @Test
    public void update(){
        Optional<CmsPage> optionalCmsPage = cmsPageRepository.findById("5c91df435019c26ff0b8a2d0");
        if (optionalCmsPage.isPresent()){
            CmsPage cmsPage = optionalCmsPage.get();
            cmsPage.setPageName("修改页面001");
            cmsPageRepository.save(cmsPage);
        }
    }

    @Test
    public void testFindAll(){
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        //条件值对象
        CmsPage cmsPage = new CmsPage();
        //查询条件
        //cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        cmsPage.setPageAliase("轮播");
        //定义匹配器
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher = matcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //模糊查询匹配规则
//        ExampleMatcher.GenericPropertyMatchers.contains(); //包含
//        ExampleMatcher.GenericPropertyMatchers.startsWith(); //前缀匹配
//        ExampleMatcher.GenericPropertyMatchers.endsWith(); //末尾匹配


        Example<CmsPage> example = Example.of(cmsPage,matcher);

        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> content = cmsPages.getContent();
        System.out.println(content);
    }
}
