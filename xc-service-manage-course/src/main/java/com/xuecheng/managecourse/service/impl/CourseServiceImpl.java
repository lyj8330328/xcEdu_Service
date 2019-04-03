package com.xuecheng.managecourse.service.impl;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.managecourse.mapper.TeachplanMapper;
import com.xuecheng.managecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-04-03 21:30
 * @Feature:
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private TeachplanMapper teachplanMapper;

    @Override
    public TeachplanNode findTeachplanList(String courseId) {
        return teachplanMapper.selectList(courseId);
    }
}
