package com.yss.dao;

import com.yss.pojo.UserResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface IUserInfoDao {
    //1.根据用户查找所有的评论
    public List<UserResult> selectCommentByuName(String uName);
    //2.根据用户查找所有的回复
    public List<UserResult>selectReplyByuName(@Param("uName") String uName);
    //3.根据用户查找用户的回答
    public List<UserResult> selectAnswerByuName(String uName);
}
