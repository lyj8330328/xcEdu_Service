package com.xuecheng.manage.media.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 98050
 * @Time: 2019-06-22 21:55
 * @Feature:
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.media")//扫描实体类
@ComponentScan(basePackages={"com.xuecheng.api"})//扫描接口
@ComponentScan(basePackages={"com.xuecheng.manage.media.process"})
@ComponentScan(basePackages={"com.xuecheng.framework"})//扫描common下的所有类
public class ManageMediaProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageMediaProcessApplication.class, args);
    }
}
