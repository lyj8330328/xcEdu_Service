package com.xuecheng.managecourse.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Author: 98050
 * @Time: 2019-04-03 22:58
 * @Feature:
 */
@Mapper
public interface TeachPlanMapper {

    /**
     * 树形查询所有节点
     * @param courseId
     * @return
     */
    TeachplanNode selectList(String courseId);
}
