package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;


/**
 * @author 98050
 */
@Data
@ToString
@Document(collection = "cms_site")
public class CmsSite {


    @Id
    private String siteId;
    private String siteName;
    private String siteDomain;
    private String sitePort;
    private String siteWebPath;
    private String sitePhysicalPath;
    private Date siteCreateTime;

}
