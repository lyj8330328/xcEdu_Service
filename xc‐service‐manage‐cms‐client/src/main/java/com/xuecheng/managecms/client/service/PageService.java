package com.xuecheng.managecms.client.service;

/**
 * @Author: 98050
 * @Time: 2019-04-01 18:39
 * @Feature:
 */
public interface PageService {
    /**
     * 将页面保存到服务器指定路径下
     * @param pageId
     */
    void savePageToServerPath(String pageId);
}
