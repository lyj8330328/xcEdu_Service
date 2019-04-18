package com.xuecheng.managecourse.service;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.AddCourseResult;
import com.xuecheng.framework.domain.course.response.CourseMarketResult;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
import com.xuecheng.framework.domain.course.response.UpdateCourseResult;
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

    /**
     * 修改课程信息
     * @param courseBase
     * @return
     */
    UpdateCourseResult courseUpdate(CourseBase courseBase);

    /**
     * 根据课程id查询课程营销信息
     * @param id
     * @return
     */
    CourseMarket getCourseMarketById(String id);

    /**
     * 根据课程id查询营销信息，查到就做修改，查不到就做插入
     * @param id
     * @param courseMarket
     * @return
     */
    CourseMarketResult updateOrInsertCourseMarket(String id,CourseMarket courseMarket);

    /**
     * 添加课程图片
     * @param courseId 课程id
     * @param pic 图片地址
     * @return
     */
    ResponseResult saveCoursePic(String courseId,String pic);

    /**
     * 查询课程图片
     * @param courseId 课程id
     * @return
     */
    CoursePic findCoursePic(String courseId);

    /**
     * 删除课程图片
     * @param courseId 课程id
     * @return
     */
    ResponseResult deleteCoursePic(String courseId);
}
