package com.xuecheng.managecourse.dao;

import com.xuecheng.framework.domain.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 98050
 * @Time: 2019-04-18 22:53
 * @Feature:
 */
public interface CoursePicRepository extends JpaRepository<CoursePic,String> {
    long deleteByCourseid(String id);
}
