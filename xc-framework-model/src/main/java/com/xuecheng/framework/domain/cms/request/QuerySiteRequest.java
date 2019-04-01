package com.xuecheng.framework.domain.cms.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 98050
 * @Time: 2019-03-30 22:42
 * @Feature:
 */
@Data
public class QuerySiteRequest {

    @ApiModelProperty("站点名称")
    private String siteName;
    @ApiModelProperty("站点域名")
    private String siteDomain;
    @ApiModelProperty("站点端口")
    private String sitePort;
    @ApiModelProperty("站点路径")
    private String siteWebPath;

}
