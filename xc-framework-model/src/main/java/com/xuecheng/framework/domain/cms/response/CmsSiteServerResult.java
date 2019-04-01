package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.domain.cms.CmsSiteServer;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Author: 98050
 * @Time: 2019-03-31 13:59
 * @Feature:
 */
public class CmsSiteServerResult extends ResponseResult {
    CmsSiteServer cmsSiteServer;

    public CmsSiteServerResult(ResultCode resultCode,CmsSiteServer cmsSiteServer){
        super(resultCode);
        this.cmsSiteServer = cmsSiteServer;
    }
}
