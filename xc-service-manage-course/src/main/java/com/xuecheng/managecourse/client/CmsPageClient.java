package com.xuecheng.managecourse.client;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.client.XcServiceList;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: 98050
 * @Time: 2019-05-11 18:18
 * @Feature:
 */
@FeignClient(value = XcServiceList.XC_SERVICE_MANAGE_CMS)
public interface CmsPageClient{

    @GetMapping("/cms/page/get/{id}")
    CmsPageResult findById(@PathVariable("id") String id);
}
