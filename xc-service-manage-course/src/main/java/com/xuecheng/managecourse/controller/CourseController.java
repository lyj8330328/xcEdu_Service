package com.xuecheng.managecourse.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.managecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 98050
 * @Time: 2019-04-03 22:53
 * @Feature:
 */
@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {

    @Autowired
    private CourseService courseService;

    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachplanList(courseId);
    }

    @Override
    @PostMapping("/teachplan/add")
    public TeachPlanResult add(@RequestBody Teachplan teachplan) {
        return this.courseService.add(teachplan);
    }

    @Override
    public TeachPlanResult edit(Teachplan teachplan) {
        return null;
    }

    @Override
    public ResponseResult delete(Teachplan teachplan) {
        return null;
    }
}
