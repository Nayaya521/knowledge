package com.yss.service;

import com.yss.pojo.Platform;
import com.yss.pojo.Question;

import java.util.List;

public interface IQuestionService {
    //1.查询所有的问题
    public List<Question> selectAllQuestion();
    //3.添加一条问题
    public int addQuestion(Question question);
    //4.根据qid查询一条问题
    public Question selectQuestionByqid(int qid);
    //5.删除一条问题
    public int deleteQuestion(int qid);
    //6.修改一条问题
    public int updateQUestion(Question question);
    //7.根据questionTitle查询问题 以后要使用es
    public List<Question> selectQuestionsbyTitle(String questionTitle);
    //8.根据标签查询问题
    public List<Question> selectQuestionsByTag(String tag);
    //9.根据用户选取
    public List<Question> selectQuestionsByCommiter(String  uname);
    //10.根据年选择问题
    public List<Question> selectQuestionsByYear(int year);
    //11.根据月份选择问题
    public List<Question> selectQuestionsByMonth(int month);
    //12.根据平台名称查询问题
    public List<Question> selectQuestionsByPN(String platformName);
    //13.根据年月查询问题
    public List<Question> selectQuestionsByYM(int year,int month);
    //14.根据年选择问题和平台名称查询问题
    public List<Question> selectQuestionsByYP(int year,String platformName);
    //15.根据月选择问题和平台名称查询问题
    public List<Question> selectQuestionsByMP(int month,String platformName);
    //16.根据年月和平台名称查询问题
    public List<Question> selectQuestionsByYMP(int year,int month,String platformName);
    //17.根据问题的Id找问题的title
    public String selectQuesionTitleByqid(int qid);



}
