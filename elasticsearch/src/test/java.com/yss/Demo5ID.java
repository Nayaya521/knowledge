package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo5ID {
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="bank";
    String type="_doc";
    @Test
    public void findById() throws IOException {
        //1.创建GetRequest
        GetRequest request=new GetRequest(index,type,"161");
        //2.执行查询
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        //3.输出查询结果
        System.out.println(response.getSourceAsMap());
    }
    @Test
    public void findByIds() throws IOException {
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.idsQuery().addIds("96","99","161"));
        request.source(builder);

        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }
    @Test
    public void findByPrefix() throws IOException {
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.prefixQuery("address","178"));
        request.source(builder);

        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }
    @Test
    public void findByfuzzy() throws IOException {
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.fuzzyQuery("employer","Progonex").prefixLength(3));
        request.source(builder);

        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }
    @Test
    public void findBywildcard() throws IOException {
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.wildcardQuery("email","*.com"));
        request.source(builder);

        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }
    @Test
    public void findByRange() throws IOException {
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.rangeQuery("account_number").gte(10).lte(30));
        request.source(builder);

        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }
    @Test
    public void findByregexp() throws IOException {
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.regexpQuery("firstname","N[a-s]{6}"));
        request.source(builder);

        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

}
