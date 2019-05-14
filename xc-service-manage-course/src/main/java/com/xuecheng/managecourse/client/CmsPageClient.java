package com.xuecheng.managecourse.client;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.client.XcServiceList;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 98050
 * @Time: 2019-05-11 18:18
 * @Feature:
 */
@FeignClient(value = XcServiceList.XC_SERVICE_MANAGE_CMS)
public interface CmsPageClient extends CmsPageControllerApi{
}
