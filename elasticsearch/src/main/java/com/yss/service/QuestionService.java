package com.yss.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yss.pojo.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 定义接口，保证mysql和es的返回值一致
 */
public interface QuestionService {
    //1.查询全部
    public List<Map<String,Object>> selectAll() throws IOException;
    //2.根据输入关键字对title和desc和标签,答案查询
    public List<Map<String,Object>> selectByKeyWord(String keyWord) throws IOException;
    //3.根据平台名称查询问题
    public List<Map<String,Object>> selectByPlatform(String platformname) throws IOException;
    //4.根据用户名查询发表的问题
    public List<Map<String,Object>> selectQuestionByUserName(String questionername) throws IOException, ParseException;
    //5.自动补充
    public List<String> completion(String prefix) throws IOException;
    //6.根据qid查找整个文档
    public Map<String,Object> selectByQid(long qid) throws IOException, ParseException;
    //7.增加一条问题文档
    public long createQuestionDoc(Question question) throws IOException, ParseException;
    //8.删除一条问题文档
    public int deleteQuestionDoc(long qid) throws IOException;
    //9.修改一条问题文档
    public int updateQuestionDoc(Question question) throws IOException;
    //10.根据时间范围来查询,年份
    public List<Map<String,Object>> selectByYear(int yearNum) throws IOException, ParseException;
    //11.根据时间范围来查询,月份
    public List<Map<String,Object>> selectByMonth(int monthNum) throws IOException, ParseException;

}
