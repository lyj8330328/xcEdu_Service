package com.xuecheng.framework.domain.course.response;

import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Author: 98050
 * @Time: 2019-04-12 16:13
 * @Feature:
 */
public class CourseMarketResult extends ResponseResult {

    private CourseMarket courseMarket;

    public CourseMarketResult(CourseMarket courseMarket, ResultCode code) {
        super(code);
        this.courseMarket = courseMarket;
    }
}
