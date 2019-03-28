package com.xuecheng.managecms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2019-03-28 17:28
 * @Feature:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageConfigTest {

    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void testRestTemplate(){
        ResponseEntity<Map> entity = this.restTemplate.getForEntity("http://localhost:31001/cms/config/getModel/5a791725dd573c3574ee333f", Map.class);
        System.out.println(entity.getBody());
    }
}
