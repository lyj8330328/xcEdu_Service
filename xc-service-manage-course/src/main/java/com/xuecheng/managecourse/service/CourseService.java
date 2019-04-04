package com.xuecheng.managecourse.service;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;

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
}
