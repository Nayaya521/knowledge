package com.yss.service;

import com.yss.pojo.Faq;
import com.yss.pojo.Question;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface FaqService {
    //1.增加一个问题
    public long createFaqDoc(Faq faq);
    //2.删除一个问题
    public int deleteFaqDoc(long qid);
    //3.修改一个问题的答案
    public int updateFaqDoc(Faq faq);
    //4.查找某个平台下的所有问题
    public List<Map<String,Object>> selectFaqDocByPlatform(String platformname);
    //5.高亮显示搜索
    public List<Map<String,Object>> selectFaqDocByKeyWord(String keyWord);
    //6.搜索补充
    public List<String> completion(String prefix);
    //7.根据用户搜索问题
    public List<Map<String,Object>> selectFaqDocByUserName(String questionername);
    //8.根据年搜索问题
    public List<Map<String,Object>> selectByYear(int yearNum);
    //9.根据月搜索问题
    public List<Map<String,Object>> selectByMonth(int monthNum);
    //10.根据qid查询问题
    public Map<String,Object> selectFaqDocByQid(long qid);
    //11.根据qid查询fileUuid
}
