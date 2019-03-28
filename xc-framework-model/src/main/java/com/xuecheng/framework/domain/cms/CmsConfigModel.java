package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;

import java.util.Map;


/**
 * @author 98050
 */
@Data
@ToString
public class CmsConfigModel {
    /**
     * 主键
     */
    private String key;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目url
     */
    private String url;
    /**
     * 项目复杂值
     */
    private Map mapValue;
    /**
     * 项目简单值
     */
    private String value;

}
