package java.com.yss;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * 在tag指定
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo2_2 {
    @Autowired
    RestHighLevelClient client;

    String index="question";
    String type="doc";

    @Test
    public void createIndex() throws IOException {
        //准备关于索引的Setting
        XContentBuilder settings = XContentFactory.jsonBuilder();
        settings.startObject()
                .field("number_of_shards",3)
                .field("number_of_replicas",2)
                .startObject("analysis")
                    .startObject("analyzer")
                        .startObject("default")
                            .field("type","custom")
                            .field("tokenizer","ik_max_word")
                            .field("filter","stemmer")
                        .endObject()
                        .startObject("searchIk")
                            .field("type","custom")
                            .field("tokenizer","ik_smart")
                            .field("filter","stemmer")
                        .endObject()
                    .endObject()
                .endObject()
                .endObject();
        //准备关于索引的Mapping
        XContentBuilder mapping= JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("qid").field("type","integer").endObject()
                        .startObject("questiontitle").field("type","text").field("analyzer", "default") .field("search_analyzer", "searchIk")
                            .startObject("fields")
                                .startObject("kw").field("type","completion").endObject()
                                .startObject("py").field("type","completion").field("analyzer","pinyin").endObject()
                            .endObject()
                        .endObject()
                        .startObject("questiondesc").field("type","text").field("analyzer", "default").field("search_analyzer", "searchIk").endObject()
                        .startObject("qtype").field("type","keyword").endObject()
                        .startObject("questiontags").field("type","text").field("analyzer", "default").field("search_analyzer", "searchIk").endObject()
                        .startObject("questionername").field("type","keyword").endObject()
                        .startObject("platformname").field("type","text").field("analyzer", "default").field("search_analyzer", "searchIk").endObject()
                        .startObject("questiontime").field("type","date").field("format","yyyy-MM-dd hh:mm:ss").endObject()
                        .startObject("questionfileuuid").field("type","keyword").endObject()
                        // 开始answer
                        .startObject("answer").field("type","nested")
                                .startObject("properties")
                                    .startObject("aid").field("type","integer").endObject()
                                    .startObject("answername").field("type","keyword").endObject()
                                    .startObject("answertime").field("type","date").field("format","yyyy-MM-dd hh:mm:ss").endObject()
                                    .startObject("answerfileuuid").field("type","keyword").endObject()
                                    .startArray("vid").field("type","integer").endObject()
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
                                    .startObject("commenttime").field("type","date").field("format","yyyy-MM-dd hh:mm:ss").endObject()
                                    .startObject("commentcontent").field("type","keyword").endObject()
                                    .startArray("commentername").field("type","keyword").endObject()
                                .endObject()
                        .endObject()
                        //comment结束
                        // reply
                        .startObject("reply").field("type","nested")
                            .startObject("properties")
                                .startObject("rid").field("type","integer").endObject()
                                .startObject("r_aid").field("type","integer").endObject()
                                .startObject("replytime").field("type","date").field("format","yyyy-MM-dd hh:mm:ss").endObject()
                                .startObject("replycontent").field("type","keyword").endObject()
                                .startArray("fromname").field("type","keyword").endObject()
                                .startArray("toname").field("type","keyword").endObject()
                            .endObject()
                        .endObject()
                        //reply结束
                    .endObject()
                .endObject();
        //3.将setting和mapping封装到一个Request对象里面
        CreateIndexRequest request=new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type,mapping);
        //4.通过client对象去连接es并执行创建索引
        CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        //5.输出
        System.out.println("resp:"+indexResponse.toString());

    }

    @Test
    public void exists() throws IOException {
        //1.准备request对象
        GetIndexRequest request=new GetIndexRequest();
        request.indices(index);

        //2.通过client去操作
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        //3.输出
        System.out.println(exists);

    }
    @Test
    public void delete() throws IOException {
        //1.准备Request对象
        DeleteIndexRequest request=new DeleteIndexRequest();
        request.indices(index);
        //2.通过client对象执行
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        //输出
        System.out.println(delete.isAcknowledged());
    }
}
