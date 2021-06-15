package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo8删除 {
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="bank";
    String type="_doc";

    @Test
    public void deleteByQuery() throws IOException {
        //1.创建DeleteByQueryRequest
        DeleteByQueryRequest request=new DeleteByQueryRequest(index);
        request.types(type);
        //2.指定检索的条件
        request.setQuery(QueryBuilders.rangeQuery("account_number").lt(6));
        //3.执行删除
        BulkByScrollResponse bulkByScrollResponse = client.deleteByQuery(request, RequestOptions.DEFAULT);
        //4.输出返回结果
        System.out.println(bulkByScrollResponse.toString());
    }
}
