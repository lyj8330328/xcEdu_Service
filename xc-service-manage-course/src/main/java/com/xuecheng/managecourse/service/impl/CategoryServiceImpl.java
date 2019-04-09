package com.xuecheng.managecourse.service.impl;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.managecourse.dao.CategoryMapper;
import com.xuecheng.managecourse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-04-08 16:55
 * @Feature:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryNode findCourseCategoryList() {
        return this.categoryMapper.selectList();
    }
}
