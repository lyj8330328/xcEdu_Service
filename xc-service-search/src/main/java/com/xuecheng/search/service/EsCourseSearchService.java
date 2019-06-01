package com.xuecheng.search.service;

import com.xuecheng.framework.domain.search.CourseSearchParam;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * @Author: 98050
 * @Time: 2019-05-31 16:33
 * @Feature:
 */
public interface EsCourseSearchService {

    /**
     * ES搜索
     * @param page 页码
     * @param size 大小
     * @param courseSearchParam 查询参数
     * @return
     */
    QueryResponseResult list(int page, int size, CourseSearchParam courseSearchParam);
}
