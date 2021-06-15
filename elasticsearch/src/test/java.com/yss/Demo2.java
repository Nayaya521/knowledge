package java.com.yss;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo2 {
    @Autowired
    RestHighLevelClient client;

    String index="bank";
    String type="_doc";

    @Test
    public void createIndex() throws IOException {
        System.out.println(client);
        //准备关于索引的Setting
      /*  Settings.Builder settings=Settings.builder()
                .put("number_of_shards",3)
                .put("number_of_replicas",2);
        //准备关于索引的Mapping
        XContentBuilder mapping= JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("account_name")
                            .field("type","integer")
                            //插入时分词
                            .field("analyzer", "ik_smart")
                            //搜索时分词
                            .field("search_analyzer", "ik_max_word")
                        .endObject()
                        .startObject("balance")
                            .field("type","integer")
                        .endObject()
                        .startObject("firstname")
                            .field("type","keyword")
                        .endObject()
                        .startObject("lastname")
                            .field("type","keyword")
                        .endObject()
                        .startObject("age")
                            .field("type","integer")
                        .endObject()
                        .startObject("gender")
                            .field("type","keyword")
                        .endObject()
                        .startObject("address")
                                .field("type","text")
                        .endObject()
                        .startObject("employer")
                                .field("type","keyword")
                        .endObject()
                        .startObject("email")
                                .field("type","keyword")
                        .endObject()
                        .startObject("city")
                             .field("type","text")
                        .endObject()
                        .startObject("state")
                            .field("type","keyword")
                        .endObject()
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
*/
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
