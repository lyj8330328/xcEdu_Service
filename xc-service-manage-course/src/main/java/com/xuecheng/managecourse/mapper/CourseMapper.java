package com.xuecheng.managecourse.mapper;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @author 98050
 */
@Mapper
public interface CourseMapper {

    CourseBase findCourseBaseById(String s);
}
