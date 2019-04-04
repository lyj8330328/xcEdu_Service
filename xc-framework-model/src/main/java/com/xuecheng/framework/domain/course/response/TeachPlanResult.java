package com.xuecheng.framework.domain.course.response;

        import com.xuecheng.framework.domain.course.Teachplan;
        import com.xuecheng.framework.model.response.ResponseResult;
        import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Author: 98050
 * @Time: 2019-04-04 21:38
 * @Feature:
 */
public class TeachPlanResult extends ResponseResult {

    Teachplan teachplan;
    public TeachPlanResult(ResultCode resultCode,Teachplan teachplan){
        super(resultCode);
        this.teachplan = teachplan;
    }
}
