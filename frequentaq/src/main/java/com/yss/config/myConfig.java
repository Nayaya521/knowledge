package com.yss.config;

import com.yss.pojo.AnswerResult;
import com.yss.pojo.Version;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class myConfig {

    @Bean
    public Version version(){
        return new Version();
    }
    @Bean
    @Scope("prototype")
    public AnswerResult answerResult(){
        return new AnswerResult();
    }
}
