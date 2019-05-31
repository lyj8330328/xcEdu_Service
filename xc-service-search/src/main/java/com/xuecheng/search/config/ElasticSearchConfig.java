package com.xuecheng.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 98050
 * @Time: 2019-05-29 21:54
 * @Feature:
 */
@Configuration
public class ElasticSearchConfig {

    @Value("${xuecheng.elasticsearch.hostlist}")
    private String hostlist;

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        //1.解析hostlist配置信息
        String[] temp = hostlist.split(",");
        //2.创建httphost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHosts = new HttpHost[temp.length];
        for (int i = 0; i < temp.length; i++) {
            String[] item = temp[i].split(":");
            httpHosts[i] = new HttpHost(item[0],Integer.parseInt(item[1]),"http");
        }
        //3.创建RestHighLevelClient客户端
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
