package java.com.yss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yss.pojo.Person;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo3增删改 {
    @Autowired
    RestHighLevelClient client;
    ObjectMapper mapper=new ObjectMapper();
    String index="person";
    String type="man";

    @Test
    public void createDoc() throws IOException {
        //1.准备一个json数据
        Person person=new Person(1,"李白",1300,new Date());
        String s = mapper.writeValueAsString(person);
        //2.准备一个request对象(手动指定ID)
        IndexRequest request=new IndexRequest(index,type,person.getId().toString());
        request.source(s, XContentType.JSON);
        //3.通过client对象执行添加
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        //4.输出
        System.out.println(response.getResult().toString());
    }

    @Test
    public void updateDoc() throws IOException {
        //1.创建一个Map,指定要修改的内容
        Map<String,Object> doc=new HashMap<>();
        doc.put("name","杜甫");
        String docID="1";
        //2.创建request对象，封装数据
        UpdateRequest request=new UpdateRequest(index,type,docID);
        request.doc(doc);
        //3.创建client对象执行
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult().toString());
    }

    @Test
    public void deleteDoc() throws IOException {
        //1.封装Request对象
        DeleteRequest request=new DeleteRequest(index,type,"1");
        //2.client执行
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        //3.输出结果
        System.out.println(response.getResult().toString());
    }

    @Test
    public void bulkCreateDoc() throws IOException {
        //1.准备多个json数据
        Person p1=new Person(2,"张三",23,new Date());
        Person p2=new Person(3,"李四",23,new Date());
        Person p3=new Person(4,"王五",23,new Date());
        //2.创建Request,将准备好的数据封装进去
        String json1 = mapper.writeValueAsString(p1);
        String json2 = mapper.writeValueAsString(p2);
        String json3 = mapper.writeValueAsString(p3);
        BulkRequest request=new BulkRequest();
        request.add(new IndexRequest(index,type,p1.getId().toString()).source(json1,XContentType.JSON));
        request.add(new IndexRequest(index,type,p2.getId().toString()).source(json2,XContentType.JSON));
        request.add(new IndexRequest(index,type,p3.getId().toString()).source(json3,XContentType.JSON));
        //3.用client执行
        BulkResponse bulk = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulk.toString());
    }

    @Test
    public void bulkDeleteDoc() throws IOException {
        //1.封装Request对象
        BulkRequest request=new BulkRequest();
        request.add(new DeleteRequest(index,type,"2"));
        request.add(new DeleteRequest(index,type,"3"));
        request.add(new DeleteRequest(index,type,"4"));

        //2.client执行
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(responses.toString());
    }
}
