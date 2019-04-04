package com.xuecheng.managecourse.service;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;

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
}
