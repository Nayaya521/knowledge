package java.com.yss;

/*import com.alibaba.fastjson.JSON;*/
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FrequentaqApplicationTests {
    @Autowired
    private RestHighLevelClient client;
    /*创建索引*/
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request=new CreateIndexRequest("question");
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );
        request.mapping("_doc","questionTime","type=text");
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
        System.out.println(acknowledged+"\n"+shardsAcknowledged);
    }
    /**
     * 创建分词器
     * @throws IOException
     */
    @Test
    public void createAnalyzer(){
        AnalyzeRequest analyzeRequest = new AnalyzeRequest("question");

        analyzeRequest.text("我爱中国","我喜欢中国"); // 设置需要分词的中文字
        analyzeRequest.analyzer("ik_max_word");       // 设置使用什么分词器  也可以使用 ik_max_word 它是细粒度分词

        try {
            AnalyzeResponse analyzeResponse = client.indices().analyze(analyzeRequest, RequestOptions.DEFAULT);
            List<AnalyzeResponse.AnalyzeToken> tokens = analyzeResponse.getTokens(); // 获取所有分词的内容
            // 使用Java 8 语法获取分词内容
            tokens.forEach(token -> {
                // 过滤内容，如果文字小于2位也过滤掉
                if (!"<NUM>".equals(token.getType()) || token.getTerm().length() > 2) {
                    String term = token.getTerm(); // 分词内容
                    System.out.println(term);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取文档
     * @throws IOException
     */
    @Test
    public void getDocument() throws IOException {
        GetRequest getRequest = new GetRequest("twitter", "_doc", "1");
        try {
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                long version = getResponse.getVersion();
                Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            } else {
                System.out.println("文件不存在");
            }
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
            }
        }
    }
    /**
     * 文档是否存在
     */
    @Test
    public void doucumentExsit(){
        GetRequest getRequest = new GetRequest(
                "posts",
                "doc",
                "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false)); //建议
        getRequest.storedFields("_none_");
        try {
            boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
            System.out.println(exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest(
                "posts",
                "doc",
                "1");
        DeleteResponse deleteResponse = client.delete(
                request, RequestOptions.DEFAULT);
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            System.out.println("成功删除文档");
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
            }
        }
    }


}

