package com.xuecheng.media.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 98050
 * @Time: 2019-06-10 21:53
 * @Feature:
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.media")//扫描实体类
@ComponentScan(basePackages={"com.xuecheng.api"})//扫描接口
@ComponentScan(basePackages={"com.xuecheng.media"})
@ComponentScan(basePackages={"com.xuecheng.framework"})//扫描common下的所有类
public class ManageMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageMediaApplication.class, args);
    }
}
