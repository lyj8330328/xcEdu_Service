package com.xuecheng.managecourse.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.AddCourseResult;
import com.xuecheng.framework.domain.course.response.CourseMarketResult;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
import com.xuecheng.framework.domain.course.response.UpdateCourseResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
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

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

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

    @Override
    @GetMapping("coursebase/list/{page}/{size}")
    public QueryResponseResult findCourseList(@PathVariable("page") int page, @PathVariable("size") int size, CourseListRequest courseListRequest) {
        return this.courseService.queryByPage(page, size, courseListRequest);
    }

    @Override
    @PostMapping("/coursebase/add")
    public AddCourseResult courseAdd(@RequestBody CourseBase courseBase) {
        return this.courseService.courseAdd(courseBase);
    }

    @Override
    @GetMapping("/coursebase/{id}")
    public CourseBase getCoureseById(@PathVariable("id") String id) {
        return this.courseService.getCourseById(id);
    }

    @Override
    @PutMapping("/coursebase/update")
    public UpdateCourseResult updateCourse(@RequestBody CourseBase courseBase) {
        System.out.println(courseBase);
        return this.courseService.courseUpdate(courseBase);
    }

    @Override
    @GetMapping("/coursemarket/{id}")
    public CourseMarket getCourseMarketById(@PathVariable("id") String courseId) {
        return this.courseService.getCourseMarketById(courseId);
    }

    @Override
    @PutMapping("/coursemarket/{id}")
    public CourseMarketResult updateOrInsertCourseMarket(@PathVariable("id") String courseId, @RequestBody CourseMarket courseMarket) {
        return this.courseService.updateOrInsertCourseMarket(courseId, courseMarket);
    }


}
