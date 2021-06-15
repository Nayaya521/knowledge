package com.yss.controller;

import com.yss.pojo.Answer;
import com.yss.pojo.Question;
import com.yss.pojo.RetResponse;
import com.yss.pojo.RetResult;
import com.yss.service.AnswerService;
import com.yss.service.QuestionService;
import com.yss.service.ServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/answer")
public class AnswerController {
    @Autowired
    ServiceLogic service;
    //4.查找一个问题的所有答案
    @GetMapping("/getAllAnswer")
    public RetResult<List<Map<String, Object>>> getAllQuestion(@RequestParam("qid") long qid) throws IOException {
        AnswerService service = this.service.getAnswerService();
        System.out.println(service.getClass().getName());
        List<Map<String, Object>> list = service.selectAllAnswer(qid);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    //1.增加一个问题的答案
    @PostMapping("/addAnswer")
    public RetResult<Long> addQuestion(Answer answer) throws IOException, ParseException {
        AnswerService service = this.service.getAnswerService();
        long statu = service.addAnswer(answer);
        if(statu !=0){
            return RetResponse.makeOKRsp(statu);
        }
        return  RetResponse.makeErrRsp("addQuestion文档失败");
    }
    //2.删除一个问题的答案
    @GetMapping("/deleteAnswerDoc")
    public RetResult<Integer> deleteQuestionDoc(@RequestParam("aid") long aid) throws IOException {
        AnswerService service = this.service.getAnswerService();
        int statu = service.deleteAnswer(aid);
        if(statu !=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("deleteQuestionDoc查询文档失败");
    }
    //3.更新一个问题的答案
    @PostMapping("/updateQuestionDoc")
    public RetResult<Integer> updateQuestionDoc(Answer answer) throws IOException {
        AnswerService service = this.service.getAnswerService();
        int statu = service.updateAnswer(answer);
        if(statu !=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("updateQuestionDoc文档失败");
    }

    //5.根据用户查找所回答的答案
    @GetMapping("/selectAnswerByUserName")
        public RetResult<List<Map<String, Object>>> selectQuestionByUserName(@RequestParam("answerName")String answerName) throws IOException, ParseException {
        AnswerService service = this.service.getAnswerService();
        List<Map<String, Object>> list = service.selectAnswerByUser(answerName);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    //3.更新一个问题的答案的赞同数
    @PostMapping("/updateApplaudDoc")
    public RetResult<Integer> updateQuestionDoc(@RequestParam("aid") long aid,@RequestParam("applaudNum") int applaudNum) throws IOException {
        AnswerService service = this.service.getAnswerService();
        int statu = service.updateApplaud(aid,applaudNum);
        if(statu !=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("updateQuestionDoc文档失败");
    }
}
