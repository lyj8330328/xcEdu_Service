package com.xuecheng.framework.domain.course.response;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * @author 98050
 */

@ToString
public enum CourseCode implements ResultCode {


    /**
     * 删除课程失败，只允许删除本机构的课程！
     */
    COURSE_DENIED_DELETE(false,31001,"删除课程失败，只允许删除本机构的课程！"),
    COURSE_PUBLISH_PERVIEWISNULL(false,31002,"还没有进行课程预览！"),
    COURSE_PUBLISH_CDETAILERROR(false,31003,"创建课程详情页面出错！"),
    COURSE_PUBLISH_COURSEIDISNULL(false,31004,"课程Id为空！"),
    COURSE_PUBLISH_VIEWERROR(false,31005,"发布课程视图出错！"),
    COURSE_PUBLISH_CREATE_INDEX_ERROR(false,31010,"创建课程索引出错！"),

    COURSE_MEDIS_URLISNULL(false,31006,"选择的媒资文件访问地址为空！"),
    COURSE_MEDIS_NAMEISNULL(false,31007,"选择的媒资文件名称为空！"),
    COURSE_COMPANYISNULL(false,31008,"机构id为空"),
    COURSE_PICISNULL(false,31009,"课程图片为空"),


    COURSE_ADD_PARAMTERISNULL(false,32111,"课程参数为空！"),
    COURSE_ADD_NAMEISNULL(false,32112,"课程名称为空！"),
    COURSE_ADD_NAMEEXISTS(false,32113,"课程名称为空！"),


    COURSE_GET_ISNULL(false,32114,"课程信息为空！"),

    COURSE_NAME_ISNULL(false,32115,"课程名称为空"),
    COURSE_USERS_ISNULL(false,32116,"适用人群为空"),
    COURSE_MT_ISNULL(false,32117,"课程分类为空"),
    COURSE_GRADE_ISNULL(false,32118,"课程等级为空"),
    COURSE_STUDYMODEL_ISNULL(false,32119,"课程学习模式为空"),
    COURSE_TEACHMODEL_ISNULL(false,32120,"授课模式为空"),
    COURSE_DESCRIPTION_ISNULL(false,32121,"课程描述为空"),
    COURSE_ST_ISNULL(false,32122,"课程分类为空"),
    COURSE_STATUS_ISNULL(false,32123,"课程状态为空"),
    COURSE_COMPANYID_ISNULL(false,32124,"教育机构ID为空"),
    COURSE_USERID_ISNULL(false,32125,"创建用户为空"),

    COURSE_ID_ISNULL(false,33111,"课程id为空！"),
    COURSE_MARKET_INFO_CHARGE_ISNULL(false,33112,"课程收费规则为空！"),
    COURSE_MARKET_INFO_VALID_ISNULL(false,33113,"课程有效性为空！"),
    COURSE_MARKET_INFO_QQ_ISNULL(false,33114,"QQ为空！"),
    COURSE_MARKET_INFO_ISNULL(false,33115,"课程营销信息为空！"),

    COURSE_VIEW_QUERY_ERROR(false,33333,"课程详情查询出错！"),


    COURSE_PLAN_ADD_PARAMETERISNULL(false,31111,"课程计划添加参数为空！"),
    COURSE_PLAN_ADD_COURSEIDISNULL(false,31112,"课程Id为空！"),
    COURSE_PLAN_ADD_COURSEISNULL(false,31113,"课程不存在！"),
    COURSE_PLAN_ADD_PARENTNODEISNULL(false,31114,"父结点不存在！"),
    COURSE_PLAN_ADD_PLANNAMEISEXISTS(false,31115,"课程计划名称已存在！"),

    COURSE_PUBLISH_ERROR(false,31116,"课程详情发布出错！"),

    COURSE_SEARCH_KEYWORD_ISNULL(false,31117,"课程搜索关键字为空！");



    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private CourseCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, CourseCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, CourseCode> builder = ImmutableMap.builder();
        for (CourseCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
