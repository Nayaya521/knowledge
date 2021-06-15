package com.yss.service;

import com.yss.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAnswerService {
    //1.增加一个问题的答案
    public int addAnswer(Answer answer);
    //2.删除答案
    public int deleteAnswer( int anId);
    //3.根据问题qid和默认版本查询所有答案
    public List<Answer>selectAnswerByqid(int qid,int vid);

    //3.根据问题qid和选择的版本进行查询答案 评论，回复
    public List<AnswerResult>selectAnswerReultByqid(int qid, int vid);

    //4.根据答案anId查询所有评论
    public List<Comment>selectCommentByAnId(int anId);
    //5.根据答案anId查询所有的回复
    public List<Reply>selectReplyByAnId(int anId);

    //6.增加评论
    public int addComment(Comment comment);
    //7.删除评论
    public int deleteComment(int cid);
    //8.增加回复
    public int addReply(Reply reply);
    //9.删除回复
    public int deleteReply(int rid);
    //10.根据aid,找对应的版本号
    public int selectVidByAid(int aid);
}
