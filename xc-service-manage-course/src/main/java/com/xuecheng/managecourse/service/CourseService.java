package com.xuecheng.managecourse.service;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.AddCourseResult;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @Author: 98050
 * @Time: 2019-04-03 22:57
 * @Feature:
 */
public interface CourseService {

    /**
     * 查询课程计划
     * @param courseId
     * @return
     */
    TeachplanNode findTeachplanList(String courseId);

    /**
     * 课程计划添加
     * @param teachplan
     * @return
     */
    TeachPlanResult add(Teachplan teachplan);

    /**
     * 课程计划修改
     * @param teachplan
     * @return
     */
    TeachPlanResult edit(Teachplan teachplan);

    /**
     * 课程计划删除
     * @param id
     * @return
     */
    ResponseResult delete(String id);

    /**
     * 分页查询课程
     * @param page
     * @param size
     * @param courseListRequest
     * @return
     */
    QueryResponseResult queryByPage(int page, int size, CourseListRequest courseListRequest);


    /**
     * 添加课程
     * @param courseBase
     * @return
     */
    AddCourseResult courseAdd(CourseBase courseBase);

    /**
     * 根据id查询课程
     * @param id
     * @return
     */
    CourseBase getCourseById(String id);
}
