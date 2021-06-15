package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
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
public class Demo4 {
    //1.term查询  term查询是代表完全匹配，搜索之前不会对你搜索的关键字进行分词，对你的关键字去文档分词库中去匹配内容
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="bank";
    String type="_doc";

    @Test
    public void termQuery() throws IOException {
        //1.创建Requests对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.termQuery("email","hattiebond@netagy.com"));
        request.source(builder);

        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void termsQuery() throws IOException {
        //1.创建Request
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.封装查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.termsQuery("email","hattiebond@netagy.com","daleadams@boink.com","aureliaharding@orbalix.com"));
        request.source(builder);
        //3.client去执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4.输出
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }



    }

    @Test
    public void matchAllQuery() throws IOException {
        //1.创建Request
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.size(20);//ES默认查询10条数据，如果想要查询更多，需要设置size
        request.source(builder);
        //3.执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        //4.输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void matchQuery() throws IOException {
        //1.创建Request
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("address","Court Street"));
        builder.size(20);//ES默认查询10条数据，如果想要查询更多，需要设置size
        request.source(builder);
        //3.执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        //4.输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
    @Test
    public void matchBoolQuery() throws IOException {
        //1.创建Request
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("address","Court Street").operator(Operator.OR));
        builder.size(20);//ES默认查询10条数据，如果想要查询更多，需要设置size
        request.source(builder);
        //3.执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        //4.输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
    @Test
    public void multi_matchQuery() throws IOException {
        //1.创建Request
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery("Sutton","address","email","city"));
        builder.size(20);//ES默认查询10条数据，如果想要查询更多，需要设置size
        request.source(builder);
        //3.执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        //4.输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
