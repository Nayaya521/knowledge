package com.yss.dao;

import com.yss.pojo.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface IAnswerDao {
    //1.增加一个问题的答案
    public int addAnswer(Answer answer);
    //2.删除答案
    public int deleteAnswer(int aid);
    //3.根据问题qid和选择的版本进行查询答案
    public List<Answer>selectAnswerByqid(@Param("qid") int qid, @Param("vid") int vid);

    //4.根据答案anId查询所有评论
    public List<Comment>selectCommentByAnId(int aid);
    //5.根据答案anId查询所有的回复
    public List<Reply>selectReplyByAnId(int aid);

    //6.增加评论
    public int addComment(Comment comment);
    //7.删除评论
    public int deleteComment(int cid);
    //8.增加回复
    public int addReply(Reply reply);
    //9.删除回复
    public int deleteReply(int rid);

    //10.根据aid,找对应的版本号
    public int  selectVidByAid(int aid);




}
