package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

/**
 * @Author: 98050
 * @Time: 2019-03-19 14:43
 * @Feature:
 */
@Data
public class QueryPageRequest extends RequestData {

    private String siteId;
    private String pageId;
    private String pageName;
    private String pageAlias;
    private String templateId;
}