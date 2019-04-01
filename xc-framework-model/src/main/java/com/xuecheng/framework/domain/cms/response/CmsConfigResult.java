package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;

/**
 * @Author: 98050
 * @Time: 2019-03-30 23:07
 * @Feature:
 */
@Data
public class CmsConfigResult extends ResponseResult {
    CmsConfig cmsConfig;

    public CmsConfigResult(ResultCode resultCode, CmsConfig cmsConfig){
        super(resultCode);
        this.cmsConfig = cmsConfig;
    }
}
