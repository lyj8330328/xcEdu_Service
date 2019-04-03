package com.xuecheng.managecourse.mapper;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author 98050
 */
public interface CourseBaseRepository extends JpaRepository<CourseBase,String> {
}
