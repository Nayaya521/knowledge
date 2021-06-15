package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo7深分页 {
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="bank";
    String type="_doc";

    @Test
    public void scrollQuery() throws IOException {
        //1.创建SearchRequest
        SearchRequest request = new SearchRequest();
        request.indices(index);
        request.types(type);
        //2.指定scroll信息
        request.scroll(TimeValue.timeValueMinutes(1L));
        //3.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(5);
        builder.sort("balance", SortOrder.DESC);
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        //4.获取返回结果scrollId,source
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        System.out.println("这是我的首页");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

        //5.循环-创建SearchScrollRequest
        while (true) {
            //6.指定scollId,生存时间;
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(TimeValue.timeValueMinutes(1L));
            //7.执行查询获取返回结果
            SearchResponse searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            //8.判断是否查询到数据，输出
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits != null && hits.length > 0) {
                System.out.println("下一页");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsMap());
                }
            } else {
                //9.判断没有查询到数据，退出循环
                System.out.println("结束");
                break;
            }
        }
        //10.创建clearScrollRequest
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        //11.指定ScrollId
        clearScrollRequest.addScrollId(scrollId);
        //12.删除ScrollId
        ClearScrollResponse scrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        //13.输出结果
        System.out.println(scrollResponse.isSucceeded());
    }
}
