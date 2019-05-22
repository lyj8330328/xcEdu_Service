package com.xuecheng.managecms.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * 扫描实体类
 */
@EntityScan("com.xuecheng.framework.domain.cms")
/**
 * 扫描common工程下的类
 */
@ComponentScan(basePackages = {"com.xuecheng.framework"})
/**
 * 扫描本项目下的所有包
 */
@ComponentScan(basePackages = {"com.xuecheng.managecms.client"})
/**
 * @Author: 98050
 * @Time: 2019-04-01 16:40
 * @Feature: CMS客户端启动器
 */
public class ManageCmsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsClientApplication.class,args);
    }
}
