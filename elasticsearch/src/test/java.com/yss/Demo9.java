package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo9 {
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="bank";
    String type="_doc";
    @Test
    public void boolSearch() throws IOException {
        //1.创建searchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder= QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.termQuery("email","hattiebond@netagy.com"));
        queryBuilder.should(QueryBuilders.termQuery("email","nanettebates@quility.com"));
        queryBuilder.mustNot(QueryBuilders.termQuery("balance",3278));
        queryBuilder.must(QueryBuilders.termQuery("gender","M"));
        builder.query(queryBuilder);
        request.source(builder);
        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
    @Test
    public void boostingSearch() throws IOException {
        //1.创建searchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoostingQueryBuilder queryBuilder=QueryBuilders.boostingQuery(
                QueryBuilders.matchQuery("address","Street"),
                QueryBuilders.matchQuery("address","Pierrepont")
        ).negativeBoost(0.8f);
        builder.query(queryBuilder);
        request.source(builder);
        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
    @Test
    public void FilterSearch() throws IOException {
        //1.创建searchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
        queryBuilder.filter(QueryBuilders.termQuery("gender","M"));
        queryBuilder.filter(QueryBuilders.rangeQuery("balance").lt(2000));
        builder.query(queryBuilder);
        request.source(builder);
        //3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }


}
