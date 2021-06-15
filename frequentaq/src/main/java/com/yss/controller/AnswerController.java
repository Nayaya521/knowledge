package com.yss.controller;

import com.yss.pojo.*;
import com.yss.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/answer")
public class AnswerController {
    @Autowired
    IAnswerService service;
    //1.增加一个问题的答案
    @PostMapping("/addAnswer")
    public RetResult<Integer> addAnswer(Answer answer){
        int returnNum = service.addAnswer(answer);
        if(returnNum!=0){
           return    RetResponse.makeOKRsp(returnNum);
        }
        return  RetResponse.makeErrRsp("添加问题答案失败,你之前添加过此问题");
    }
    //2.删除答案
    @GetMapping("/deleteAnswer")
    public RetResult<Integer> deleteAnswer(@RequestParam("aid") int aid){
        int returnNum = service.deleteAnswer(aid);
        if(returnNum!=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("删除答案失败");
    }
    //3.根据问题qid和默认版本查询所有答案
    @GetMapping("/selectAnswerBypid")
    public RetResult<List<Answer>>selecAnswertBypid(@RequestParam("qid") int qid,int vid){
        List<Answer> list = service.selectAnswerByqid(qid,vid);
        if(list.size()!=0){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询答案失败");
    }

    //3.根据问题qid和默认版本查询所有答案
    @GetMapping("/selectAnswerReultByqid")
    public RetResult<List<AnswerResult>>selectAnswerReultByqid(@RequestParam("qid") int qid,int vid){
        List<AnswerResult> results = service.selectAnswerReultByqid(qid, vid);
        if(results.size()!=0){
            return RetResponse.makeOKRsp(results);
        }
        return  RetResponse.makeErrRsp("查询答案评论回复失败");
    }
    //4.根据答案aid查询所有评论
    @GetMapping("/selectCommentByAnId")
    public RetResult<List<Comment>>selectCommentByAnId(@RequestParam("aid") int aid){
        List<Comment> list = service.selectCommentByAnId(aid);
        if(list.size()!=0){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询评论失败");
    }
    //5.根据答案aid查询所有的回复
    @GetMapping("/selectReplyByAnId")
    public RetResult<List<Reply>>selectReplyByAnId(@RequestParam("aid") int aid){
        List<Reply> list = service.selectReplyByAnId(aid);
        if(list.size()!=0){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询回复失败");
    }

    //6.增加评论
    @PostMapping("/addComment")
    public RetResult<Integer> addComment(Comment comment){
        int returnNum = service.addComment(comment);
        if(returnNum!=0){
            return    RetResponse.makeOKRsp(returnNum);
        }
        return  RetResponse.makeErrRsp("添加评论失败");
    }
    //7.删除评论
    @GetMapping("/deleteComment")
    public RetResult<Integer> deleteComment(int cid){
        int returnNum = service.deleteComment(cid);
        if(returnNum!=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("删除评论失败");
    }
    //8.增加评论
    @PostMapping("/addReply")
    public RetResult<Integer> addReply(Reply reply){
        int returnNum = service.addReply(reply);
        if(returnNum!=0){
            return    RetResponse.makeOKRsp(returnNum);
        }
        return  RetResponse.makeErrRsp("添加回复失败");
    }
    //9.删除回复
    @GetMapping("/deleteReply")
    public RetResult<Integer> deleteReply(int rid){
        int returnNum = service.deleteReply(rid);
        if(returnNum!=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("删除回复失败");
    }

}
