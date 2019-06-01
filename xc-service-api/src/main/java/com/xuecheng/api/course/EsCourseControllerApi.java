package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.CoursePub;
import com.xuecheng.framework.domain.search.CourseSearchParam;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 98050
 * @Time: 2019-05-31 16:25
 * @Feature:
 */
@Api(value = "课程搜索",description = "课程搜索",tags = "{课程搜索}")
@RequestMapping("/search/course")
public interface EsCourseControllerApi {

    /**
     * ES搜索
     * @param page 页码
     * @param size 大小
     * @param courseSearchParam 查询参数
     * @return
     */
    @ApiOperation("课程搜索")
    @GetMapping(value = "/list/{page}/{size}")
    QueryResponseResult list(@PathVariable("page") int page, @PathVariable("size") int size, CourseSearchParam courseSearchParam);
}
