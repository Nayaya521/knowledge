package com.yss.config;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * springboot监听，当spring容器创建结束，自动监听
 */
@Component
@Conditional(questionCondition.class)
public class IndexCommandRunner implements CommandLineRunner {
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Value("${elasticsearch.index.shards}")
    String ShardsNum;
    @Value("${elasticsearch.index.replicas}")
    String ReplicasNum;
    @Value("${elasticsearch.index.frequentAskedQuestion.name}")
    String index_faq_name;
    @Value("${elasticsearch.index.frequentAskedQuestion.type}")
    String index_faq_type;
    @Override
    public void run(String... args) throws Exception{
        System.out.println("执行es操作");
        if(!existsIndexfaq()){
            createfaqIndex();
        }

    }
    public void createfaqIndex() throws IOException {
        //创建索引
        //创建question索引
        XContentBuilder settings = XContentFactory.jsonBuilder();
        settings.startObject()
                .field("number_of_shards",Integer.parseInt(ShardsNum))
                .field("number_of_replicas",Integer.parseInt(ReplicasNum))
                .endObject();
        //准备关于索引的Mapping
        XContentBuilder mapping= JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")
                .startObject("qid").field("type","long").endObject()
                .startObject("questionTitle").field("type","text").field("analyzer", "ik_max_word") .field("search_analyzer", "ik_smart")
                    .startObject("fields")
                         .startObject("kw").field("type","completion").endObject()
                    .endObject()
                .endObject()
                .startObject("qType").field("type","keyword").endObject()
                .startObject("questionTags").field("type","text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_smart").endObject()
                .startObject("questionerName").field("type","keyword").endObject()
                .startObject("platformName").field("type","text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_smart").endObject()
                .startObject("questionTime").field("type","date").field("format","yyyy-MM-dd HH:mm:ss").endObject()
                .startObject("questionFileUuid").field("type","keyword").endObject()
                .startObject("answerContent").field("type","text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_smart").endObject()
                .endObject()
                .endObject();
        //3.将setting和mapping封装到一个Request对象里面
        CreateIndexRequest request=new CreateIndexRequest(index_faq_name)
                .settings(settings)
                .mapping(index_faq_type,mapping);
        //4.通过client对象去连接es并执行创建索引
        CreateIndexResponse indexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        //5.输出
        System.out.println("resp: Question index "+indexResponse.toString());
    }

    public boolean existsIndexfaq() throws IOException {
        if(restHighLevelClient!=null){
            //1.准备request对象
            GetIndexRequest request=new GetIndexRequest();
            request.indices(index_faq_name);
            //2.通过client去操作,返回
            return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        }
        return false;
    }

}
