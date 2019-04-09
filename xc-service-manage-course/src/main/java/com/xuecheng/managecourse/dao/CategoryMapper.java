package com.xuecheng.managecourse.dao;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @Author: 98050
 * @Time: 2019-04-08 16:57
 * @Feature:
 */
@Mapper
public interface CategoryMapper {

    /**
     * 查询所有课程分类
     * @return
     */
    List<CategoryNode> selectList();
}
