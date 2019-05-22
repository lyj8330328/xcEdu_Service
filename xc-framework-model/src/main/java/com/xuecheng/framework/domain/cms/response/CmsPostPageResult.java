package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 98050
 * @Time: 2019-05-20 22:27
 * @Feature: 课程详情页面发布返回结果
 */
@Data
@NoArgsConstructor
public class CmsPostPageResult extends ResponseResult {
    private String pageUrl;

    public CmsPostPageResult(ResultCode resultCode,String pageUrl) {
        super(resultCode);
        this.pageUrl = pageUrl;
    }
}
