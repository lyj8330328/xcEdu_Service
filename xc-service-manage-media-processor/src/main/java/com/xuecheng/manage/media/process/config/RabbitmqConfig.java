package com.xuecheng.manage.media.process.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 98050
 * @Time: 2019-06-22 22:16
 * @Feature:
 */
@Configuration
public class RabbitmqConfig {
    /**
     * 交换机bean名称
     */
    public static final String EXMEDIAPROCESSTASK = "ex_media_processor";

    /**
     * 队列bean名称
     */
    public static final String QUEUEMEDIAPROCESSTASK = "queue_media_processor";

    //队列名称

    /**
     * 视频处理队列
     */
    @Value("${xc-service-manage-media.mq.queue-media-video-processor}")
    public String queueMediaVideoProcessor;

    /**
     * 视频处理路由key
     */
    @Value("${xc-service-manage-media.mq.routingkey-media-video}")
    public String routingkeyMediaVideo;

    /**
     * 交换机配置
     * @return
     */
    @Bean(EXMEDIAPROCESSTASK)
    public Exchange EX_MEDIA_VIDEOTASK(){
        return ExchangeBuilder.directExchange(EXMEDIAPROCESSTASK).durable(true).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean(QUEUEMEDIAPROCESSTASK)
    public Queue QUEUE_PROCESSTASK(){
        return new Queue(queueMediaVideoProcessor,true,false,true);
    }

    /**
     * 绑定队列到交换机
     * @param queue QUEUEMEDIAPROCESSTASK
     * @param exchange EXMEDIAPROCESSTASK
     * @return
     */
    @Bean
    public Binding BINDING_QUEUE_MEDIA_PROCESSTASK(@Qualifier(QUEUEMEDIAPROCESSTASK) Queue queue, @Qualifier(EXMEDIAPROCESSTASK) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingkeyMediaVideo).noargs();
    }
}
