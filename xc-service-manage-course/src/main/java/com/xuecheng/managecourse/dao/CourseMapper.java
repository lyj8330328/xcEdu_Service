package com.xuecheng.managecourse.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator.
 */
@Mapper
public interface CourseMapper {
   CourseBase findCourseBaseById(String id);
}
