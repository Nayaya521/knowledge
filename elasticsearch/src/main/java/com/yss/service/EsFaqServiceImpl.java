package com.yss.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yss.pojo.Faq;
import com.yss.pojo.Question;
import com.yss.util.TimeDifferenceUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
public class EsFaqServiceImpl implements FaqService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ObjectMapper mapper;
    @Value("${elasticsearch.index.frequentAskedQuestion.name}")
    private String index_name;
    @Value("${elasticsearch.index.frequentAskedQuestion.type}")
    private String index_type;
    @Override
    public long createFaqDoc(Faq faq) {
        long timeMillis = System.currentTimeMillis();
        //1.????????????json??????
        faq.setQid(timeMillis);
        faq.setQuestionTime(new Date());
        String s = null;
        try {
            s = mapper.writeValueAsString(faq);
            System.out.println(s);
            //2.????????????request??????(????????????ID)
            IndexRequest request=new IndexRequest(index_name,index_type,String.valueOf(timeMillis));
            request.source(s, XContentType.JSON);
            //3.??????client??????????????????
            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            //4.??????
            if(response.status().getStatus()!=0){
                System.out.println("????????????"+response.getResult().toString());
                return 1;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  0;
    }

    @Override
    public int deleteFaqDoc(long qid) {
        //1.??????Request??????
        DeleteRequest request=new DeleteRequest(index_name,index_type,String.valueOf(qid));
        //2.client??????
        DeleteResponse response = null;
        try {
            response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            //3.????????????
            if (response.status().getStatus()!=0) {
                System.out.println("????????????"+response.getResult().toString());
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  0;
    }

    @Override
    public int updateFaqDoc(Faq faq) {
        //1.????????????Map,????????????????????????
        Map<String, String> doc=new HashMap<>();
        if(faq!=null){
            doc = JSON.parseObject(JSON.toJSONString(faq), new TypeReference<Map<String, String>>() {
            });
        }
        UpdateResponse response = null;
        try {
            //2.??????request?????????????????????
            UpdateRequest request=new UpdateRequest(index_name,index_type,String.valueOf(faq.getQid()));
            request.doc(doc);
            //3.??????client????????????
            response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            if (response.status().getStatus()!=0) {
                System.out.println("????????????"+response.getResult().toString());
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectFaqDocByPlatform(String platformname) {
        List<Map<String,Object>>list=new ArrayList<>();
        //1.??????Request
        SearchRequest request=new SearchRequest(index_name);
        request.types(index_type);
        //2.??????????????????
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("platformName",platformname));
        builder.from(0);
        builder.size(100);//ES????????????10???????????????????????????????????????????????????size
        request.source(builder);
        //3.????????????
        SearchResponse response= null;
        try {
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            //4.????????????
            for (SearchHit hit : response.getHits().getHits()) {
                list.add(TimeDifferenceUtils.mapAdd(hit.getSourceAsMap(),"questionTime"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectFaqDocByKeyWord(String keyWord) {
        //1.SearchRequest
        SearchRequest request=new SearchRequest(index_name);
        request.types(index_type);
        //3.??????????????????(??????)
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(100);
        builder.query(QueryBuilders.multiMatchQuery(keyWord,"questionTitle","answerContent"));
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("questionTitle",10)
                .preTags("<font color='red'>")
                .postTags("</font>")
                .field("answerContent",30)
                .preTags("<font color='red'>")
                .postTags("</font>");
        builder.highlighter(highlightBuilder);
        request.source(builder);
        //4.client??????
        SearchResponse response = null;
        List<Map<String,Object>> list=new ArrayList<>();
        try {
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            //4.??????
            for (SearchHit hit : response.getHits().getHits()) {
                HighlightField questiontitle = hit.getHighlightFields().get("questionTitle");
                HighlightField answerContent = hit.getHighlightFields().get("answerContent");
                if(questiontitle!=null){
                    Text[] texts = questiontitle.getFragments();
                    System.out.println("texts"+texts.length);
                    String QuehightStr = null;
                    if(texts !=null){
                        for (Text text : texts) {
                            QuehightStr = text.string();
                            System.out.println("QuehighStr"+ QuehightStr);
                        }
                        hit.getSourceAsMap().put("questionTitle",QuehightStr);
                    }
                }
                if(answerContent!=null){
                    Text[] descTexts = answerContent.getFragments();
                    System.out.println("descTexts"+descTexts.length);
                    String descHightStr = null;
                    if(descTexts !=null){
                        for (Text text : descTexts) {
                            descHightStr = text.string();
                            System.out.println("QuehighStr"+ descHightStr);
                        }
                        hit.getSourceAsMap().put("answerContent",descHightStr);
                    }
                }

                try {
                    list.add(TimeDifferenceUtils.mapAdd(hit.getSourceAsMap(),"questionTime"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> completion(String prefix) {
        List<String>result=new ArrayList<>();
        //1.??????Requests??????
        SearchRequest request=new SearchRequest(index_name);
        request.types(index_type);
        //2.??????????????????
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        // ?????????user?????????kmichy?????? TermSuggestionBuilder
        SuggestionBuilder completionSuggestion = SuggestBuilders.completionSuggestion("questionTitle.kw").text(prefix);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        // ??????completionSuggestion???suggestBuilder??????????????????suggest_questiontitle
        suggestBuilder.addSuggestion("suggest_questionTitle", completionSuggestion);
        searchSourceBuilder.suggest(suggestBuilder);
        request.source(searchSourceBuilder);
        //3.????????????
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            //??????es????????????
            List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> list = response
                    .getSuggest().getSuggestion("suggest_questionTitle").getEntries();
            if (list == null) {
                return null;
            }
            else {
                //??????list?????????????????????
                for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> e : list) {
                    for (Suggest.Suggestion.Entry.Option option : e) {
                        result.add(option.getText().toString());
                        System.out.println(option.getText().toString());
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> selectFaqDocByUserName(String questionername) {
        List<Map<String,Object>>list=new ArrayList<>();
        //1.??????Requests??????
        SearchRequest request=new SearchRequest(index_name);
        request.types(index_type);
        //2.??????????????????
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(100);
        builder.query(QueryBuilders.termsQuery("questionerName",questionername));
        request.source(builder);

        //3.????????????
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            List<Question> questionResult=new ArrayList<>();
            for (SearchHit hit : response.getHits()) {
                list.add(TimeDifferenceUtils.mapAdd(hit.getSourceAsMap(),"questionTime"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectByYear(int yearNum) {
        List<Map<String,Object>>list=new ArrayList<>();
        SearchResponse response = null;
        try {
            //1.??????Requests??????
            SearchRequest request=new SearchRequest(index_name);
            request.types(index_type);
            //2.??????????????????
            SearchSourceBuilder builder=new SearchSourceBuilder();
            builder.from(0);
            builder.size(100);
            builder.query(QueryBuilders.rangeQuery("questionTime").gte("now-"+yearNum+"y/d").lte("now"));
            request.source(builder);
            //3.????????????
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()) {
                list.add(TimeDifferenceUtils.mapAdd(hit.getSourceAsMap(),"questionTime"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectByMonth(int monthNum) {
        List<Map<String,Object>>list=new ArrayList<>();

        SearchResponse response = null;
        try {
            //1.??????Requests??????
            SearchRequest request=new SearchRequest(index_name);
            request.types(index_type);
            //2.??????????????????
            SearchSourceBuilder builder=new SearchSourceBuilder();
            builder.from(0);
            builder.size(100);
            builder.query(QueryBuilders.rangeQuery("questionTime").gte("now-"+monthNum+"M/d").lte("now"));
            request.source(builder);
            //3.????????????
            response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()) {
                list.add(hit.getSourceAsMap());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Map<String, Object> selectFaqDocByQid(long qid) {
        Map<String,Object> map=new HashMap<>();
        GetResponse response = null;
        try {
            //1.??????GetRequest
            GetRequest request=new GetRequest(index_name,index_type,String.valueOf(qid));
            //2.????????????
            response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            map=TimeDifferenceUtils.mapAdd(response.getSourceAsMap(),"questionTime");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //3.??????????????????
        return map;
    }
}
