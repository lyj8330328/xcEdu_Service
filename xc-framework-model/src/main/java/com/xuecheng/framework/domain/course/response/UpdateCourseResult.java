package com.xuecheng.framework.domain.course.response;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Author: 98050
 * @Time: 2019-04-11 16:50
 * @Feature:
 */
public class UpdateCourseResult extends ResponseResult {

    private CourseBase courseBase;

    public UpdateCourseResult(CourseBase courseBase, ResultCode resultCode) {
        super(resultCode);
        this.courseBase = courseBase;
    }
}
