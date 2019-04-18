package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.AddCourseResult;
import com.xuecheng.framework.domain.course.response.CourseMarketResult;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
import com.xuecheng.framework.domain.course.response.UpdateCourseResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 98050
 * @Time: 2019-04-03 19:59
 * @Feature:
 */
@RequestMapping("/course")
@Api(value = "课程管理接口",description = "课程管理接口",tags = {"课程管理接口"})
public interface CourseControllerApi {
    /**
     * 课程计划查询
     * @param courseId
     * @return
     */
    @ApiOperation("课程计划查询")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    @GetMapping("/teachplan/list/{courseId}")
    TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId);

    /**
     * 课程计划添加
     * @param teachplan
     * @return
     */
    @ApiOperation("课程计划添加")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    @PostMapping("/teachplan/add")
    TeachPlanResult add(@RequestBody Teachplan teachplan);


    /**
     * 课程计划添加
     * @param teachplan
     * @return
     */
    @ApiOperation("课程计划添加")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    TeachPlanResult edit(@RequestBody Teachplan teachplan);


    /**
     * 课程计划添加
     * @param teachplan
     * @return
     */
    @ApiOperation("课程计划添加")
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    ResponseResult delete(@RequestBody Teachplan teachplan);


    /**
     * 课程列表分页查询
     * @param page
     * @param size
     * @param courseListRequest
     * @return
     */
    @ApiOperation("课程列表分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "页大小",required = true,paramType = "path",dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    @GetMapping("/list/{page}/{size}")
    QueryResponseResult findCourseList(@PathVariable("page") int page, @PathVariable("size") int size, CourseListRequest courseListRequest);


    /**
     * 课程信息添加
     * @param courseBase
     * @return
     */
    @ApiOperation("课程信息添加")
    @PostMapping("/coursebase/add")
    AddCourseResult courseAdd(@RequestBody CourseBase courseBase);

    /**
     * 根据id查询课程信息
     * @param id
     * @return
     */
    @ApiOperation("根据id查询课程信息")
    @GetMapping("/coursebase/{id}")
    CourseBase getCoureseById(@PathVariable("id") String id);


    /**
     * 更新课程信息
     * @param courseBase
     * @return
     */
    @ApiOperation("更新课程信息")
    @PutMapping("/coursebase/update")
    UpdateCourseResult updateCourse(@RequestBody CourseBase courseBase);


    /**
     * 根据课程id查询课程营销信息
     * @param courseId
     * @return
     */
    @ApiOperation("获取课程营销信息")
    @GetMapping("/coursemarket/{id}")
    CourseMarket getCourseMarketById(@PathVariable("id") String courseId);

    /**
     * 更新或者新增课程营销信息
     * @param courseId
     * @param courseMarket
     * @return
     */
    @ApiOperation("更新课程营销信息")
    @PutMapping("/coursemarket/{id}")
    CourseMarketResult updateOrInsertCourseMarket(@PathVariable("id") String courseId,@RequestBody CourseMarket courseMarket);

    /**
     * 课程图片信息保存
     * @param courseId
     * @param pic
     * @return
     */
    @ApiOperation("添加课程图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId",value = "课程id",required = true,paramType = "path",dataType = "String"),
            @ApiImplicitParam(name = "pic",value = "图片url",required = true,paramType = "path",dataType = "String")
    })
    @PostMapping("/coursepic/add")
    ResponseResult addCoursePic(String courseId,String pic);


    /**
     * 课程图片查询
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("课程图片查询")
    @GetMapping("/coursepic/list/{courseId}")
    CoursePic findCoursePic(@PathVariable("courseId") String courseId);


    /**
     * 课程图片查询
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("课程图片查询")
    @DeleteMapping("/coursepic/delete/{courseId}")
    ResponseResult deleteCoursePic(@PathVariable("courseId") String courseId);
}
