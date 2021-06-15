package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo11 {
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="bank";
    String type="_doc";
    @Test
    public void testCardinality() throws IOException {
        //1.创建searchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定使用的聚合参数
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.cardinality("agg").field("employer"));
        request.source(builder);
        //执行查询
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //输出结果
        Cardinality agg = response.getAggregations().get("agg");
        System.out.println(agg.getValue());

    }

    @Test
    public void testAggRange() throws IOException {
        //1.创建searchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定使用的聚合参数
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.range("agg").field("balance")
                                                      .addUnboundedTo(2000)
                                                       .addRange(2000,20000)
                                                       .addUnboundedFrom(20000));
        request.source(builder);
        //执行查询
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //输出结果
        Range agg = response.getAggregations().get("agg");
        for (Range.Bucket bucket : agg.getBuckets()) {
            String key = bucket.getKeyAsString();
            Object from = bucket.getFrom();
            Object to = bucket.getTo();
            long docCount = bucket.getDocCount();
            System.out.println("key:"+key+"from:"+ from+"to:"+to+" docCount:"+docCount);
        }


    }
    @Test
    public void testExtendStats() throws IOException {
        //1.创建searchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定使用的聚合参数
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.extendedStats("agg").field("balance"));
        request.source(builder);
        //执行查询
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //输出结果
        ExtendedStats agg = response.getAggregations().get("agg");
        System.out.println(agg.getMax()+":"+agg.getMin());
    }
}
