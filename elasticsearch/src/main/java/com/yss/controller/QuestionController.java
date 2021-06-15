package com.yss.controller;

import com.yss.pojo.*;
import com.yss.service.QuestionService;
import com.yss.service.ServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/question")
public class QuestionController {
    @Autowired
    ServiceLogic service;
    @GetMapping("/getAllQuestion")
    public RetResult<List<Map<String, Object>>> getAllQuestion() throws IOException {
        QuestionService service = this.service.getQuestionService();
        System.out.println(service.getClass().getName());
        List<Map<String, Object>> list = service.selectAll();
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/getByKeyWord")
    public RetResult<List<Map<String, Object>>> getByKeyWord(@RequestParam("keyword") String keyword) throws IOException {
        QuestionService service = this.service.getQuestionService();
        List<Map<String, Object>> list = service.selectByKeyWord(keyword);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/selectByPlatform")
    public RetResult<List<Map<String, Object>>> selectByPlatform(@RequestParam("platformname") String platformname) throws IOException {
        QuestionService service = this.service.getQuestionService();
        List<Map<String, Object>> list = service.selectByPlatform(platformname);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("selectByPlatform查询文档失败");
    }
    @GetMapping("/selectQuestionByUserName")
    public RetResult<List<Map<String, Object>>> selectQuestionByUserName(@RequestParam("questionername")String questionername) throws IOException, ParseException {
        QuestionService service = this.service.getQuestionService();
        List<Map<String, Object>> list = service.selectQuestionByUserName(questionername);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/completion")
    public RetResult<List<String>> completion(@RequestParam("prefix") String prefix) throws IOException {
        QuestionService service = this.service.getQuestionService();
        List<String> list = service.completion(prefix);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/selectByQid")
    public RetResult<Map<String, Object>> selectByQid(long qid) throws IOException, ParseException {
        QuestionService service = this.service.getQuestionService();
        Map<String, Object> question = service.selectByQid(qid);
        if(question !=null){
            return RetResponse.makeOKRsp(question);
        }
        return  RetResponse.makeErrRsp("selectByPlatform查询文档失败");
    }
    @PostMapping("/addQuestion")
    public RetResult<Long> addQuestion(Question question) throws IOException, ParseException {
        QuestionService service = this.service.getQuestionService();
        long statu = service.createQuestionDoc(question);
        if(statu !=0){
            return RetResponse.makeOKRsp(statu);
        }
        return  RetResponse.makeErrRsp("addQuestion文档失败");
    }
    @GetMapping("/deleteQuestionDoc")
    public RetResult<Integer> deleteQuestionDoc(long qid) throws IOException {
        QuestionService service = this.service.getQuestionService();
        int statu = service.deleteQuestionDoc(qid);
        if(statu !=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("deleteQuestionDoc查询文档失败");
    }
    @PostMapping("/updateQuestionDoc")
    public RetResult<Integer> updateQuestionDoc(Question question) throws IOException {
        QuestionService service = this.service.getQuestionService();
        int statu = service.updateQuestionDoc(question);
        if(statu !=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("updateQuestionDoc文档失败");
    }
    @GetMapping("/selectByYear")
    public RetResult<List<Map<String, Object>>> selectByYear(@RequestParam("yearNum")int yearNum) throws IOException, ParseException {
        QuestionService service = this.service.getQuestionService();
        List<Map<String, Object>> list = service.selectByYear(yearNum);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/selectByMonth")
    public RetResult<List<Map<String, Object>>> selectByMont(@RequestParam("monthNum")int monthNum) throws IOException, ParseException {
        QuestionService service = this.service.getQuestionService();
        List<Map<String, Object>> list = service.selectByMonth(monthNum);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
}
