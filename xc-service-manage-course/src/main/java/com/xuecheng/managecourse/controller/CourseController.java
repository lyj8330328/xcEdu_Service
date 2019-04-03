package com.xuecheng.managecourse.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.managecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 98050
 * @Time: 2019-04-03 21:42
 * @Feature:
 */
@RestController
@RequestMapping("/course")
public class CourseController{

//    @Autowired
//    public CourseService courseService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

//    @Override
//    @GetMapping("/teachplan/list/{courseId}")
//    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
//        return courseService.findTeachplanList(courseId);
//    }
}
