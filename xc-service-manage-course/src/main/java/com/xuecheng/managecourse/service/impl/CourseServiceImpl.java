package com.xuecheng.managecourse.service.impl;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.managecourse.dao.TeachPlanMapper;
import com.xuecheng.managecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-04-03 22:57
 * @Feature:
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    public TeachPlanMapper teachPlanMapper;

    @Override
    public TeachplanNode findTeachplanList(String courseId) {
        return teachPlanMapper.selectList(courseId);
    }
}
