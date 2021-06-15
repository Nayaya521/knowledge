package com.yss.service;

import com.yss.pojo.Answer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AnswerService {
    //1.增加一个问题的答案
    public int addAnswer(Answer answer);
    //2.删除一个问题的答案
    public int deleteAnswer(int aid);
    //3.更新一个问题的答案
    public int updateAnswer(Answer answer);
    //4.查找一个问题的所有答案
    public List<Map<String,Object>>selectAllAnswer();
    //5.根据用户查找所回答的答案
    public List<Map<String,Object>>selectAnswerByUser(String username);

}
