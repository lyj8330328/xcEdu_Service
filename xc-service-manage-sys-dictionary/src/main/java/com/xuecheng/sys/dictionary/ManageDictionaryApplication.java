package com.xuecheng.sys.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * 扫描实体类
 */
@EntityScan("com.xuecheng.framework.domain.system")
/**
 * 扫描接口，swagger接口文档
 */
@ComponentScan(basePackages = {"com.xuecheng.api"})
/**
 * 扫描本项目下的所有包
 */
@ComponentScan(basePackages = {"com.xuecheng.sys.dictionary"})
/**
 * 扫描common工程下的类
 */
@ComponentScan(basePackages = {"com.xuecheng.framework"})
/**
 * @Author: 98050
 * @Time: 2019-04-08 21:52
 * @Feature:
 */
public class ManageDictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageDictionaryApplication.class,args);
    }
}
