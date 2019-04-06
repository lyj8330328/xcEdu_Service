package com.xuecheng.managecourse.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;
import com.github.pagehelper.Page;


/**
 * @author 98050
 */
@Mapper
public interface CourseMapper {

   /**
    * 课程分页查询
    * @param courseListRequest
    * @return
    */
   Page<CourseInfo> findCourseListPage(CourseListRequest courseListRequest);


   /**
    * 根据id查询课程
    * @param id
    * @return
    */
   CourseBase findCourseBaseById(String id);

}
