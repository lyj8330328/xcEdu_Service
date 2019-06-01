package com.xuecheng.search.service.impl;

import com.xuecheng.framework.domain.search.CourseSearchParam;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.search.service.EsCourseSearchService;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-05-31 16:35
 * @Feature:
 */
@Service
public class EsCourseSearchServiceImpl implements EsCourseSearchService {


    @Override
    public QueryResponseResult list(int page, int size, CourseSearchParam courseSearchParam) {
        //1.按关键字查询
        //2.按分类等级查询
        //3.分页与高亮
        return null;
    }
}
