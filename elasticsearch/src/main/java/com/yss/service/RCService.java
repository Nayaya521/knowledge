package com.yss.service;

import com.yss.pojo.Comment;
import com.yss.pojo.Reply;

import java.io.IOException;
import java.util.List;

public interface RCService {
    //5.根据用户名查询发表的评论
    public List<Comment> selectCommentByUserName(String username) throws IOException;
    //6.根据用户名查询发表的回复
    public List<Reply> selectReplyByUserName(String username) throws IOException;
}
