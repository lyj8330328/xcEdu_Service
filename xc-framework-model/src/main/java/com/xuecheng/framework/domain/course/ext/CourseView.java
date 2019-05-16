package com.xuecheng.framework.domain.course.ext;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: 98050
 * @Time: 2019-05-15 21:46
 * @Feature:
 */
@Data
@ToString
@NoArgsConstructor
public class CourseView implements Serializable {

    private static final long serialVersionUID = -915749022529361861L;
    /**
     * 课程基础信息
     */
    private CourseBase courseBase;
    /**
     * 课程营销信息
     */
    private CourseMarket courseMarket;
    /**
     * 课程图片
     */
    private CoursePic coursePic;
    /**
     * 教学计划
     */
    private TeachplanNode teachplanNode;
}
