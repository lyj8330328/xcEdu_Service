package com.xuecheng.managecourse;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 98050
 */
@SpringBootApplication
/**
 * 扫描实体类
 */
@EntityScan("com.xuecheng.framework.domain.cms")
/**
 * 扫描接口，swagger接口文档
 */
@ComponentScan(basePackages = {"com.xuecheng.api"})
/**
 * 扫描本项目下的所有包
 */
@ComponentScan(basePackages = {"com.xuecheng.managecourse"})
/**
 * 扫描common工程下的类
 */
@ComponentScan(basePackages = {"com.xuecheng.framework"})
public class ManageCourseApplication {
    public static void main(String[] args){
        SpringApplication.run(ManageCourseApplication.class, args);
    }
}
