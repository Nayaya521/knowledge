package com.yss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {
    @Bean
    @Conditional(questionCondition.class)
    public RestHighLevelClient restHighLevelClient(){
        System.out.println("restclient 进行创建le");
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("192.168.252.132", 9200, "http"),
                            new HttpHost("192.168.252.130",9200,"http"),
                            new HttpHost("192.168.252.133",9200,"http")));
        return client;
    }
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
