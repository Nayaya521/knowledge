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
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * springboot监听，当spring容器创建结束，自动监听
 */
@Component
public class IndexCommandRunner1 /*implements CommandLineRunner*/ {
   /* @Autowired
    RestHighLevelClient restHighLevelClient;
    @Value("${elasticsearch.index.name}")
    String index_name;
    @Value("${elasticsearch.index.type}")
    String index_type;
    @Value("${elasticsearch.index.shards}")
    String shardsNum;
    @Value("${elasticsearch.index.replicas}")
    String replicasNum;
    @Override
    public void run(String... args) throws Exception {
        if(restHighLevelClient !=null && !exists()){
            //创建索引
            XContentBuilder settings = XContentFactory.jsonBuilder();
        settings.startObject()
                .field("number_of_shards",Integer.parseInt(shardsNum))
                .field("number_of_replicas",Integer.parseInt(replicasNum))
                .endObject();
        //准备关于索引的Mapping
        XContentBuilder mapping= JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("qid").field("type","integer").endObject()
                        .startObject("questiontitle").field("type","text").field("analyzer", "ik_max_word") .field("search_analyzer", "ik_smart")
                            .startObject("fields")
                                .startObject("kw").field("type","completion").endObject()
                            .endObject()
                        .endObject()
                        .startObject("questiondesc").field("type","text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_smart").endObject()
                        .startObject("qtype").field("type","keyword").endObject()
                        .startObject("questiontags").field("type","text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_smart").endObject()
                        .startObject("questionername").field("type","keyword").endObject()
                        .startObject("platformname").field("type","text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_smart").endObject()
                        .startObject("questiontime").field("type","text").endObject()
                        .startObject("questionfileuuid").field("type","keyword").endObject()
                        // 开始answer
                        .startObject("answer").field("type","nested")
                                .startObject("properties")
                                    .startObject("aid").field("type","integer").endObject()
                                    .startObject("answername").field("type","keyword").endObject()
                                    .startObject("answertime").field("type","text").endObject()
                                    .startObject("answerfileuuid").field("type","keyword").endObject()
                                    .startObject("vid").field("type","integer").endObject()
                                    .startObject("numversion").field("type","double").endObject()
                                    .startObject("greekversion").field("type","keyword").endObject()
                                .endObject()
                        .endObject()
                         //comment结束
                        // 开始comment
                        .startObject("comment").field("type","nested")
                                .startObject("properties")
                                    .startObject("cid").field("type","integer").endObject()
                                    .startObject("c_aid").field("type","integer").endObject()
                                    .startObject("commenttime").field("type","text").endObject()
                                    .startObject("commentcontent").field("type","keyword").endObject()
                                    .startObject("commentername").field("type","keyword").endObject()
                                .endObject()
                        .endObject()
                        //comment结束
                        // reply
                        .startObject("reply").field("type","nested")
                            .startObject("properties")
                                .startObject("rid").field("type","integer").endObject()
                                .startObject("r_aid").field("type","integer").endObject()
                                .startObject("replytime").field("type","text").endObject()
                                .startObject("replycontent").field("type","keyword").endObject()
                                .startObject("fromname").field("type","keyword").endObject()
                                .startObject("toname").field("type","keyword").endObject()
                            .endObject()
                        .endObject()
                        //reply结束
                    .endObject()
                .endObject();
        //3.将setting和mapping封装到一个Request对象里面
        CreateIndexRequest request=new CreateIndexRequest(index_name)
                .settings(settings)
                .mapping(index_type,mapping);
        //4.通过client对象去连接es并执行创建索引
        CreateIndexResponse indexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        //5.输出
        System.out.println("resp:"+indexResponse.toString());
        }
    }
    public boolean exists() throws IOException {
        if(restHighLevelClient!=null){
            //1.准备request对象
            GetIndexRequest request=new GetIndexRequest();
            request.indices(index_name);
            //2.通过client去操作,返回
            return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        }
        return false;
    }*/
}
