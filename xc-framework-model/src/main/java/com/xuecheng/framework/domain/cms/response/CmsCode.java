package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.model.response.ResultCode;
import lombok.ToString;

/**
 * CMS异常错误代码
 * @author 98050
 */
@ToString
public enum CmsCode implements ResultCode {
    /**
     * 页面已经存在
     */
    CMS_ADDPAGE_EXISTSNAME(false,24001,"页面名称已存在！"),
    /**
     * 从页面信息中找不到获取数据的url
     */
    CMS_GENERATEHTML_DATAURLISNULL(false,24002,"从页面信息中找不到获取数据的url！"),
    /**
     * 根据页面的数据url获取不到数据
     */
    CMS_GENERATEHTML_DATAISNULL(false,24003,"根据页面的数据url获取不到数据！"),
    /**
     * 页面模板为空
     */
    CMS_GENERATEHTML_TEMPLATEISNULL(false,24004,"页面模板为空！"),
    /**
     * 生成的静态html为空
     */
    CMS_GENERATEHTML_HTMLISNULL(false,24005,"生成的静态html为空！"),
    /**
     * 保存静态html出错
     */
    CMS_GENERATEHTML_SAVEHTMLERROR(false,24006,"保存静态html出错！"),
    /**
     * 预览页面为空
     */
    CMS_COURSE_PERVIEWISNULL(false,24007,"预览页面为空！"),

    /**
     * 页面不存在
     */
    CMS_PAGE_NOTEXISTS(false,24008,"页面不存在"),

    /**
     * 站点不存在
     */
    CMS_SITE_NOTEXISTS(false,24009,"站点不存在"),

    /**
     * 数据模型不存在
     */
    CMS_CONFIG_NOTEXISTS(false,24010,"数据模型不存在"),

    /**
     * 数据模型名称已经存在
     */
    CMS_ADDCONFIG_EXISTSNAME(false,24011,"数据模型名称已经存在！"),

    CMS_CONFIG_MODEL_ISNULL(false,24012,"数据模型中数据条目为空！");


    /**
     * 操作结果
     */
    boolean success;

    /**
     * 操作结果
     */
    int code;

    /**
     * 提示信息
     */
    String message;
    CmsCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
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
