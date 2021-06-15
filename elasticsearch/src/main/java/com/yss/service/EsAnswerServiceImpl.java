package com.yss.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yss.pojo.Answer;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EsAnswerServiceImpl implements AnswerService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ObjectMapper mapper;
    @Value("${elasticsearch.index.answer.name}")
    private String index_name;
    @Value("${elasticsearch.index.answer.type}")
    private String index_type;
    @Override
    public int addAnswer(Answer answer) {
        //1.准备一个json数据
        answer.setAnswerTime(new Date());
        String s = null;
        try {
            s = mapper.writeValueAsString(answer);
            System.out.println(s);
            //2.准备一个request对象(手动指定ID)
            IndexRequest request=new IndexRequest(index_name,index_type,String.valueOf(System.currentTimeMillis()));
            request.source(s, XContentType.JSON);
            //3.通过client对象执行添加
            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            //4.输出
            if(response.status().getStatus()!=0){
                System.out.println("创建完成"+response.getResult().toString());
                return 1;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  0;
    }

    @Override
    public int deleteAnswer(int aid) {
        DeleteResponse response = null;
        try {
            //1.封装Request对象
            DeleteRequest request=new DeleteRequest(index_name,index_type,String.valueOf(aid));
            //2.client执行
            response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.输出结果
        if (response.status().getStatus()!=0) {
            System.out.println("删除完成"+response.getResult().toString());
            return 1;
        }
        return  0;
    }

    @Override
    public int updateAnswer(Answer answer) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectAllAnswer() {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectAnswerByUser(String username) {
        return null;
    }
}
