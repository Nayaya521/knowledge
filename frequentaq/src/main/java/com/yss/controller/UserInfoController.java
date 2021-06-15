package com.yss.controller;

import com.yss.pojo.AnswerResult;
import com.yss.pojo.RetResponse;
import com.yss.pojo.RetResult;
import com.yss.pojo.UserResult;
import com.yss.service.IUserInfoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/userInfo")
public class UserInfoController {
    @Autowired
    IUserInfoservice service;
    //1.根据用户查找所有的评论
    @GetMapping("/selectCommentByuName")
    public RetResult<List<UserResult>>selectCommentByuName(String uName){
        List<UserResult> results = service.selectCommentByuName(uName);
        if(results.size()!=0){
            return RetResponse.makeOKRsp(results);
        }
        return  RetResponse.makeErrRsp("查询根据用户查找所有的评论失败");
    }
    //2.根据用户查找所有的回复
    @GetMapping("/selectReplyByuName")
    public RetResult<List<UserResult>>selectReplyByuName(String uName){
        List<UserResult> results = service.selectReplyByuName(uName);
        if(results.size()!=0){
            return RetResponse.makeOKRsp(results);
        }
        return  RetResponse.makeErrRsp("查询根据用户查找所有的回复失败");
    }
    //3.根据用户查找用户的回答
    @GetMapping("/selectAnswerByuName")
    public RetResult<List<UserResult>>selectAnswerByuName(String uName){
        List<UserResult> results = service.selectAnswerByuName(uName);
        if(results.size()!=0){
            return RetResponse.makeOKRsp(results);
        }
        return  RetResponse.makeErrRsp("查询根据用户查找用户的回答失败");
    }
}
