package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 98050
 * @Time: 2019-07-18 22:49
 * @Feature:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testRedis(){
        //定义key
        String key = "user_token:1231231qqeqdasdada";
        BoundValueOperations<String,String> boundValueOperations = this.stringRedisTemplate.boundValueOps(key);
        //定义Map
        Map<String,String> map = new HashMap<>();
        map.put("id", "101");
        map.put("username", "zzc");
        String json = JSON.toJSONString(map);
        //向redis中存储字符串
        boundValueOperations.set(json,60, TimeUnit.SECONDS);
        System.out.println("---------------------------------------------");
        //读取过期时间
        Long expire = boundValueOperations.getExpire();
        System.out.println("过期时间：" + expire);
        //根据key获取value
        String s = boundValueOperations.get();
        System.out.println(s);
    }
}
