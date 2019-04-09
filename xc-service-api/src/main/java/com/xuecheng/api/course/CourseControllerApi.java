package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
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
}
