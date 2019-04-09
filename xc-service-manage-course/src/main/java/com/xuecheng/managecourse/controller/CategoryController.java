package com.xuecheng.managecourse.controller;

import com.xuecheng.api.course.CategoryControllerApi;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.managecourse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 98050
 * @Time: 2019-04-08 17:30
 * @Feature:
 */
@RestController
@RequestMapping("category")
public class CategoryController implements CategoryControllerApi {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @GetMapping("list")
    public CategoryNode findList() {
        return this.categoryService.findCourseCategoryList();
    }
}
