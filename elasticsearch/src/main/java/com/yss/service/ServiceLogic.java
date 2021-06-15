package com.yss.service;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * restHighLevelClient是否为null,如果为null，直接调用mysql
 */
@Service
public class ServiceLogic {
    @Value("${elasticsearch.started}")
    private String isStarted;
    @Autowired
    private EsQuestionServiceImpl esQuestionService;
    @Autowired
    private MysqlQuestionServiceImpl mysqlQuestionService;
    @Autowired
    private EsAnswerServiceImpl esAnswerService;
    @Autowired
    private MysqlAnswerServiceImpl mysqlAnswerService;
    public QuestionService getQuestionService(){
        if(isStarted.equals("true")){
            System.out.println("选用esQuestionService");
            return esQuestionService;
        }else {
            System.out.println("选用mysqlQuestionService");
            return mysqlQuestionService;
        }
    }
    public AnswerService getAnswerService(){
        if(isStarted.equals("true")){
            System.out.println("选用esAnswerService");
            return esAnswerService;
        }else {
            System.out.println("选用mysqlAnswerService");
            return mysqlAnswerService;
        }
    }

}
