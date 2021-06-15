package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo10高亮 {
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="bank";
    String type="_doc";

    @Test
    public void testHighLight() throws IOException {
        //1.SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        //2.指定查询条件(高亮)
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("address","Schenck"));
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("address",10)
                .preTags("<font color='red'>")
                .postTags("</font>");
        builder.highlighter(highlightBuilder);
        request.source(builder);
        //3.client执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4.输出
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getHighlightFields().get("address"));
        }
    }
}
