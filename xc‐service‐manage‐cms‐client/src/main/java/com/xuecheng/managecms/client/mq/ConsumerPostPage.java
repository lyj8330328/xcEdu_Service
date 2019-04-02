package com.xuecheng.managecms.client.mq;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.managecms.client.dao.CmsPageRepository;
import com.xuecheng.managecms.client.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @Author: 98050
 * @Time: 2019-04-01 20:38
 * @Feature: cms消费客户端
 */
@Component
public class ConsumerPostPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private PageService pageService;

    @RabbitListener(queues = {"${xuecheng.mq.queue}"})
    public void postPage(String msg){
        //1.解析消息
        Map map = JSON.parseObject(msg,Map.class);
        LOGGER.info("receive cms post page:{}", msg);
        //2.取出页面id
        String pageId = (String) map.get("pageId");
        //3.取出发布类型
        String type = (String) map.get("type");
        //4.查询页面信息
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent()){
            LOGGER.info("receive cms post page,cmsPage is null:{}",msg);
            return;
        }
        //5.根据发布类型，将页面保存到服务器物理路径下
        this.pageService.savePageToServerPath(pageId,type);
    }
}
