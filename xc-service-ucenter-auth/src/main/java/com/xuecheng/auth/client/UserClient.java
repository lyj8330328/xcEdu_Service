package com.xuecheng.auth.client;

import com.xuecheng.api.ucenter.UcenterControllerApi;
import com.xuecheng.framework.client.XcServiceList;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 98050
 * @Time: 2019-07-25 22:41
 * @Feature:
 */
@FeignClient(value = XcServiceList.XC_SERVICE_UCENTER)
public interface UserClient extends UcenterControllerApi {
}
