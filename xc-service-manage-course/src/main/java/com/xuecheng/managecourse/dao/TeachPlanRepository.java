package com.xuecheng.managecourse.dao;

import com.xuecheng.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 98050
 * @Time: 2019-04-04 22:31
 * @Feature:
 */
public interface TeachPlanRepository extends JpaRepository<Teachplan,String> {
    /**
     * 根据课程id查询课程计划的根节点
     * @param courseId
     * @param parentId
     * @return
     */
    List<Teachplan> findByCourseidAndParentid(String courseId, String parentId);

    /**
     * 根据课程id和pname进行查询
     * @param courseId
     * @param panme
     * @return
     */
    List<Teachplan> findByCourseidAndPname(String courseId,String panme);
}
