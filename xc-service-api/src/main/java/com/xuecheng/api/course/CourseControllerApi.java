package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 98050
 * @Time: 2019-04-03 19:59
 * @Feature:
 */
@RequestMapping("/course")
@Api(value = "课程管理接口",description = "课程管理接口")
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
    TeachPlanResult add(Teachplan teachplan);
}
