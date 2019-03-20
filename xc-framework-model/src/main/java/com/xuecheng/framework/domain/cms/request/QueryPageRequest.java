package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 98050
 * @Time: 2019-03-19 14:43
 * @Feature:
 */
@Data
public class QueryPageRequest extends RequestData {

    @ApiModelProperty("站点Id")
    private String siteId;
    @ApiModelProperty("页面Id")
    private String pageId;
    @ApiModelProperty("页面名称")
    private String pageName;
    @ApiModelProperty("页面别名")
    private String pageAlias;
    @ApiModelProperty("模板Id")
    private String templateId;
}