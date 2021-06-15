package com.yss.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yss.pojo.Answer;
import com.yss.pojo.Question;
import com.yss.util.TimeDifferenceUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
public class EsAnswerServiceImpl implements AnswerService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired EsQuestionServiceImpl questionService;
    @Autowired
    private ObjectMapper mapper;
    @Value("${elasticsearch.index.answer.name}")
    private String index_name;
    @Value("${elasticsearch.index.answer.type}")
    private String index_type;
    @Override
    public int addAnswer(Answer answer) {
        //1.准备一个json数据
        long timestamp = System.currentTimeMillis();
        answer.setAid(timestamp);
        answer.setAnswerTime(new Date());
        String s = null;
        try {
            s = mapper.writeValueAsString(answer);
            System.out.println(s);
            //2.准备一个request对象(手动指定ID)
            IndexRequest request=new IndexRequest(index_name,index_type,String.valueOf(timestamp));
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
    public int deleteAnswer(long aid) {
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
        //1.创建一个Map,指定要修改的内容
        Map<String, String> doc=new HashMap<>();
        if(answer!=null){
            doc = JSON.parseObject(JSON.toJSONString(answer), new TypeReference<Map<String, String>>() {
            });
        }

        UpdateResponse response = null;
        try {
            //2.创建request对象，封装数据
            UpdateRequest request=new UpdateRequest(index_name,index_type,String.valueOf(answer.getAid()));
            request.doc(doc);
            //3.创建client对象执行
            response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.status().getStatus()!=0) {
            System.out.println("更新完毕"+response.getResult().toString());
            return 1;
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectAllAnswer(long qid) {
        List<Map<String,Object>>list=new ArrayList<>();
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index_name);
        request.types(index_type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(100);
        builder.sort(SortBuilders.fieldSort("answerApplaud").order(SortOrder.DESC));
        builder.query(QueryBuilders.termsQuery("qid",String.valueOf(qid)));
        request.source(builder);
        //3.执行查询
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            List<Question> questionResult=new ArrayList<>();
            for (SearchHit hit : response.getHits()) {
                list.add(TimeDifferenceUtils.mapAdd(hit.getSourceAsMap(),"answerTime"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectAnswerByUser(String username) {
        List<Map<String,Object>>list=new ArrayList<>();
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index_name);
        request.types(index_type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(100);
        builder.query(QueryBuilders.termsQuery("answerName",username));
        request.source(builder);

        //3.执行查询
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            List<Question> questionResult=new ArrayList<>();
            for (SearchHit hit : response.getHits()) {
                long qid = (long) hit.getSourceAsMap().get("qid");
                Map<String, Object> questionMap = questionService.selectByQid(qid);
                hit.getSourceAsMap().put("questionerName",questionMap.get("questionerName"));
                hit.getSourceAsMap().put("questionTitle",questionMap.get("questionTitle"));
                list.add(TimeDifferenceUtils.mapAdd(hit.getSourceAsMap(),"answerTime"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int updateApplaud(long aid,int applaudNum) {
        //1.创建一个Map,指定要修改的内容
        Map<String,Object> doc=new HashMap<>();
        doc.put("answerApplaud",String.valueOf(applaudNum+1));
        //2.创建request对象，封装数据
        UpdateRequest request=new UpdateRequest(index_name,index_type,String.valueOf(aid));
        request.doc(doc);
        //3.创建client对象执行
        UpdateResponse response = null;
        try {
            response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            System.out.println(response.getResult().toString());
            return response.status().getStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
