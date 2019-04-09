package com.xuecheng.framework.domain.course.ext;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.xuecheng.framework.domain.course.Category;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 98050
 */
@Data
@ToString
public class CategoryNode extends Category {

    List<CategoryNode> children;

}
