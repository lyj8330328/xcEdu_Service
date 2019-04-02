package com.xuecheng.managecms.client.service;

/**
 * @Author: 98050
 * @Time: 2019-04-01 18:39
 * @Feature:
 */
public interface PageService {

    /**
     * 将页面保存到服务器指定路径下
     * @param pageId 页面id
     * @param type 发布类型：post或redo
     */
    void savePageToServerPath(String pageId,String type);
}
