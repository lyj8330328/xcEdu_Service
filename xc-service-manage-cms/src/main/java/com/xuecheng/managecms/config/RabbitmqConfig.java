package com.xuecheng.managecms.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 98050
 * @Time: 2019-04-01 16:47
 * @Feature: Rabbitmq配置
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 交换机bean的名称
     */
    public static final String EX_ROUTING_CMS_POSTPAGE="ex_routing_cms_postpage";

    /**
     * 交换机配置使用direct类型
     * @return the exchange
     */
    @Bean(EX_ROUTING_CMS_POSTPAGE)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        /**
         * durable(true)持久化，消息队列重启后交换机仍然存在
         */
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
    }
}
