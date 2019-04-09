package com.xuecheng.framework.domain.system.response;

import com.xuecheng.framework.model.response.ResultCode;
import lombok.ToString;

/**
 * @author 98050
 */
@ToString
public enum SysDictionaryCode implements ResultCode {
    /**
     * 数据字典为空
     */
    SYS_DICTIONARY_IS_NULL(false,26001,"数据字典为空！");


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
    SysDictionaryCode(boolean success, int code, String message){
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
