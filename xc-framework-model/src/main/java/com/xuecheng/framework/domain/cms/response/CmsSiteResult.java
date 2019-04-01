package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;

/**
 * @Author: 98050
 * @Time: 2019-03-30 22:59
 * @Feature:
 */
@Data
public class CmsSiteResult extends ResponseResult {
    CmsSite cmsSite;
    public CmsSiteResult(ResultCode resultCode,CmsSite cmsSite){
        super(resultCode);
        this.cmsSite = cmsSite;
    }
}
