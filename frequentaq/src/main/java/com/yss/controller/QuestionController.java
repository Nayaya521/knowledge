package com.yss.controller;

import com.yss.pojo.*;
import com.yss.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/question")
public class QuestionController {

    @Autowired
    IQuestionService service;
    //1.查询所有的问题
    @GetMapping("/selectAllQuestion")
    public RetResult<List<Question>> selectAllQuestion(){
        List<Question> questions = service.selectAllQuestion();
        if(questions.size() !=0) {
          return  RetResponse.makeOKRsp(questions);
        }
       return RetResponse.makeRsp(500,"查询所有的问题失败");
    }
    //3.添加一条问题
    @PostMapping("/addQuestion")
    public RetResult<Integer> addQuestion(Question question){
        int i = service.addQuestion(question);
        if(i!=0){
            return RetResponse.makeOKRsp(i);
        }
        return RetResponse.makeRsp(500,"添加问题失败");
    }
    //4.根据qid查询一条问题
    @GetMapping("/selectQuestionByQid")
    public RetResult<Question>selectQuestionByQid(@RequestParam("qid")int qid){
        Question question = service.selectQuestionByqid(qid);
        if(question!=null){
            return RetResponse.makeOKRsp(question);
        }
        return RetResponse.makeRsp(500,"根据qid查询问题失败");
    }
    //5.删除一条问题
    @GetMapping("/deleteQuestion")
    public RetResult<Integer> deleteQuestion(@RequestParam("qid") int qid){
        int returnNum = service.deleteQuestion(qid);
        if(returnNum !=0) {
            return   RetResponse.makeOKRsp();
        }
        return RetResponse.makeRsp(500,"删除问题失败");
    }
    //6.修改一条问题
    @PostMapping("/updateQuestion")
    public RetResult<Integer> updateQUestion(Question question){
        int q = service.updateQUestion(question);
        if(q !=0) {
            return  RetResponse.makeOKRsp();
        }
        return RetResponse.makeRsp(500,"更新问题失败");
    }
    //7.根据questionTitle查询问题 以后要使用es
    @GetMapping("/selectQuestionsbyTitle")
    public RetResult<List<Question>>selectQuestionsbyTitle(@RequestParam("questionTitle")String questionTitle){
        List<Question> questions = service.selectQuestionsbyTitle(questionTitle);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据问题标题查询失败");
    }
    //8.根据标签查询问题 以后要使用es
    @GetMapping("/selectQuestionsByTag")
    public RetResult<List<Question>>selectQuestionsByTag(@RequestParam("tag")String tag){
        List<Question> questions = service.selectQuestionsByTag(tag);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据标签查询失败");
    }
    //9.根据用户选取 public List<Question> selectQuestionsByCommiter(int uid);
    @GetMapping("/selectQuestionsByCommiter")
    public RetResult<List<Question>>selectQuestionsByCommiter(@RequestParam("uname")String uname){
        List<Question> questions = service.selectQuestionsByCommiter(uname);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据用户查询失败");
    }
    //10.根据年选择问题
    @GetMapping("/selectQuestionsByYear")
    public RetResult<List<Question>>selectQuestionsByYear(@RequestParam("year")int year){
        List<Question> questions = service.selectQuestionsByYear(year);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据年份查询失败");
    }
    //11.根据月份选择问题
    @GetMapping("/selectQuestionsByMonth")
    public RetResult<List<Question>>selectQuestionsByMonth(@RequestParam("month")int month){
        List<Question> questions = service.selectQuestionsByMonth(month);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据月份查询失败");
    }
    //12.根据平台名称查询问题
    @GetMapping("/selectQuestionsByPN")
    public RetResult<List<Question>>selectQuestionsByPN(@RequestParam("platformName")String  platformName){
        List<Question> questions = service.selectQuestionsByPN(platformName);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据平台查询失败");
    }
    //13.根据年月查询问题
    @GetMapping("/selectQuestionsByYM")
    public RetResult<List<Question>>selectQuestionsByYM(@RequestParam("year")int year,@RequestParam("month") int month){
        List<Question> questions = service.selectQuestionsByYM(year,month);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据年月查询失败");
    }
    //14.根据年选择问题和平台名称查询问题
    @GetMapping("/selectQuestionsByYP")
    public RetResult<List<Question>>selectQuestionsByYP(@RequestParam("year")int year,@RequestParam("platformName") String platformName){
        List<Question> questions = service.selectQuestionsByYP(year,platformName);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据年 平台查询失败");
    }
    //15.根据月选择问题和平台名称查询问题
    @GetMapping("/selectQuestionsByMP")
    public RetResult<List<Question>>selectQuestionsByMP(@RequestParam("month")int month,@RequestParam("platformName") String platformName){
        List<Question> questions = service.selectQuestionsByMP(month,platformName);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据月份和平台查询失败");
    }
    //16.根据年月和平台名称查询问题
    @GetMapping("/selectQuestionsByYMP")
    public RetResult<List<Question>>selectQuestionsByYMP(@RequestParam("year")int year,@RequestParam("month")int month,@RequestParam("platformName") String platformName){
        List<Question> questions = service.selectQuestionsByYMP(year,month,platformName);
        if(questions.size()!=0){
            return RetResponse.makeOKRsp(questions);
        }
        return RetResponse.makeRsp(500,"根据年月和平台查询失败");
    }
    //17.根据问题的Id找问题的title
    @GetMapping("/selectQuesionTitleByqid")
    public RetResult<String>selectQuesionTitleByqid(@RequestParam("qid")int qid){
        String questionTitle = service.selectQuesionTitleByqid(qid);
        if(questionTitle!=null){
            return RetResponse.makeOKRsp(questionTitle);
        }
        return RetResponse.makeRsp(500,"根据id找title查询失败");
    }


}
