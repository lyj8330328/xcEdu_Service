package com.xuecheng.managecourse.mapper;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 98050
 * @Time: 2019-04-03 21:33
 * @Feature:
 */
@Mapper
public interface TeachplanMapper {

    /**
     * 根据课程id查询所有子节点
     * @param courseId
     * @return
     */
    @Select("SELECT  \n" +
            "a.id one_id,\n" +
            "a.pname one_name,\n" +
            "b.id two_id,\n" +
            "b.pname two_name,\n" +
            "c.id three_id,\n" +
            "c.pname three_name\n" +
            "FROM teachplan a LEFT JOIN teachplan b ON a.id = b.parentid LEFT JOIN teachplan c ON b.id = c.parentid\n" +
            "WHERE a.parentid = '0' AND a.courseid = #{courseId};\n" +
            "ORDER BY a.orderby,b.orderby,c.orderby")
    TeachplanNode selectList(String courseId);
}
