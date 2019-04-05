package com.xuecheng.managecourse.service.impl;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.CourseCode;
import com.xuecheng.framework.domain.course.response.TeachPlanResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.managecourse.dao.CourseBaseRepository;
import com.xuecheng.managecourse.dao.TeachPlanMapper;
import com.xuecheng.managecourse.dao.TeachPlanRepository;
import com.xuecheng.managecourse.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 98050
 * @Time: 2019-04-03 22:57
 * @Feature:
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final TeachPlanMapper teachPlanMapper;

    private final CourseBaseRepository courseBaseRepository;

    private final TeachPlanRepository teachPlanRepository;

    @Autowired
    public CourseServiceImpl(TeachPlanMapper teachPlanMapper, CourseBaseRepository courseBaseRepository, TeachPlanRepository teachPlanRepository) {
        this.teachPlanMapper = teachPlanMapper;
        this.courseBaseRepository = courseBaseRepository;
        this.teachPlanRepository = teachPlanRepository;
    }

    /**
     * 课程计划查询
     * @param courseId
     * @return
     */
    @Override
    public TeachplanNode findTeachplanList(String courseId) {
        return teachPlanMapper.selectList(courseId);
    }

    /**
     * 课程计划添加
     * 说明：如果当前teachplan对象中parentId为空，说明这是一个二级节点，那么parentId应该为根节点，也就是一级节点
     * @param teachplan
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public TeachPlanResult add(Teachplan teachplan) {
        //1.校验参数对象
        if (teachplan == null) {
            ExceptionCast.cast(CourseCode.COURSE_PLAN_ADD_PARAMETERISNULL);
        }
        //2.取出课程id
        String courseId = teachplan.getCourseid();
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.cast(CourseCode.COURSE_PLAN_ADD_COURSEIDISNULL);
        }
        //3.根据课程id和课程计划名称进行校验
        List<Teachplan> list = this.teachPlanRepository.findByCourseidAndPname(courseId, teachplan.getPname());
        if (list.size() != 0){
            ExceptionCast.cast(CourseCode.COURSE_PLAN_ADD_PLANNAMEISEXISTS);
        }
        //4.取出父节点id
        String parentId = teachplan.getParentid();
        if (StringUtils.isEmpty(parentId)) {
            parentId = getTeachPlanRoot(courseId);
        }
        //5.获取父节点信息
        Optional<Teachplan> optional = this.teachPlanRepository.findById(parentId);
        if (!optional.isPresent()) {
            ExceptionCast.cast(CourseCode.COURSE_PLAN_ADD_PARENTNODEISNULL);
        }
        Teachplan parentNode = optional.get();
        //6.设置父节点
        teachplan.setParentid(parentId);
        //7.未发布
        teachplan.setStatus("0");
        //8.设置子节点的级别，根据父节点来判断
        String level1 = "1";
        String level2 = "2";
        String level3 = "3";
        if (level1.equals(parentNode.getGrade())) {
            teachplan.setGrade(level2);
        } else if (level2.equals(parentNode.getGrade())) {
            teachplan.setGrade(level3);
        }
        //9.设置课程id
        teachplan.setCourseid(parentNode.getCourseid());
        //10.保存
        Teachplan save = this.teachPlanRepository.save(teachplan);
        return new TeachPlanResult(CommonCode.SUCCESS, save);
    }

    /**
     * 课程计划修改
     * @param teachplan
     * @return
     */
    @Override
    public TeachPlanResult edit(Teachplan teachplan) {
        this.teachPlanRepository.save(teachplan);
        return null;
    }

    @Override
    public ResponseResult delete(String id) {
        return null;
    }

    /**
     * 根据课程id获取一级节点
     * 如果没有找到，说明是第一次添加课程计划，需要构造根节点
     * @param courseId
     * @return
     */
    private String getTeachPlanRoot(String courseId) {
        //1.校验课程
        Optional<CourseBase> optional = this.courseBaseRepository.findById(courseId);
        if (!optional.isPresent()){
            ExceptionCast.cast(CourseCode.COURSE_PLAN_ADD_COURSEISNULL);
        }
        CourseBase courseBase = optional.get();
        //2.获取课程计划根节点
        List<Teachplan> teachplanList = this.teachPlanRepository.findByCourseidAndParentid(courseId, "0");
        if (teachplanList == null || teachplanList.size() == 0){
            //3.新增根节点
            Teachplan teachplanRoot = new Teachplan();
            teachplanRoot.setCourseid(courseId);
            teachplanRoot.setPname(courseBase.getName());
            teachplanRoot.setParentid("0");
            //层级：一级
            teachplanRoot.setGrade("1");
            //未发布
            teachplanRoot.setStatus("0");
            Teachplan save = teachPlanRepository.save(teachplanRoot);
            return save.getId();
        }
        Teachplan teachplan = teachplanList.get(0);
        return teachplan.getId();
    }
}
