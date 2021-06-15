package com.yss.service;

import com.yss.pojo.UserResult;

import java.util.List;

public interface IUserInfoservice {
    //1.根据用户查找所有的评论
    public List<UserResult> selectCommentByuName(String uName);
    //2.根据用户查找所有的回复
    public List<UserResult>selectReplyByuName(String uName);
    //3.根据用户查找用户的回答
    public List<UserResult> selectAnswerByuName(String uName);
}
