package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: 98050
 * @Time: 2019-04-08 15:53
 * @Feature:
 */
@Api(value = "课程分类管理",description = "课程分类管理",tags = {"课程分类管理"})
@RequestMapping("category")
public interface CategoryControllerApi {

    /**
     * 课程分类查询
     * @return
     */
    @ApiOperation("查询分类")
    @GetMapping("list")
    List<CategoryNode> findList();
}
