package com.yss.controller;

import com.yss.pojo.Faq;
import com.yss.pojo.Question;
import com.yss.pojo.RetResponse;
import com.yss.pojo.RetResult;
import com.yss.service.FaqService;
import com.yss.service.QuestionService;
import com.yss.service.ServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/faq")
public class FaqController {
    @Autowired
    ServiceLogic service;
    @GetMapping("/getByKeyWord")
    public RetResult<List<Map<String, Object>>> getByKeyWord(@RequestParam("keyword") String keyword) throws IOException {
        FaqService service = this.service.getFaqService();
        List<Map<String, Object>> list = service.selectFaqDocByKeyWord(keyword);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/selectByPlatform")
    public RetResult<List<Map<String, Object>>> selectByPlatform(@RequestParam("platformname") String platformname) throws IOException {
        FaqService service = this.service.getFaqService();
        List<Map<String, Object>> list = service.selectFaqDocByPlatform(platformname);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("selectByPlatform查询文档失败");
    }
    @GetMapping("/selectFaqByUserName")
    public RetResult<List<Map<String, Object>>> selectFaqByUserName(@RequestParam("questionername")String questionername) throws IOException, ParseException {
        FaqService service = this.service.getFaqService();
        List<Map<String, Object>> list = service.selectFaqDocByUserName(questionername);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/completion")
    public RetResult<List<String>> completion(@RequestParam("prefix") String prefix) throws IOException {
        FaqService service = this.service.getFaqService();
        List<String> list = service.completion(prefix);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/selectByQid")
    public RetResult<Map<String, Object>> selectByQid(long qid) throws IOException, ParseException {
        FaqService service = this.service.getFaqService();
        Map<String, Object> question = service.selectFaqDocByQid(qid);
        if(question !=null){
            return RetResponse.makeOKRsp(question);
        }
        return  RetResponse.makeErrRsp("selectByPlatform查询文档失败");
    }
    @PostMapping("/addFaqDoc")
    public RetResult<Long> addFaqDoc(Faq faq) throws IOException, ParseException {
        FaqService service = this.service.getFaqService();
        long statu = service.createFaqDoc(faq);
        if(statu !=0){
            return RetResponse.makeOKRsp(statu);
        }
        return  RetResponse.makeErrRsp("addQuestion文档失败");
    }
    @GetMapping("/deleteFaqDoc")
    public RetResult<Integer> deleteFaqDoc(long qid) throws IOException {
        FaqService service = this.service.getFaqService();
        int statu = service.deleteFaqDoc(qid);
        if(statu !=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("deleteQuestionDoc查询文档失败");
    }
    @PostMapping("/updateFaqDoc")
    public RetResult<Integer> updateFaqDoc(Faq faq) throws IOException {
        FaqService service = this.service.getFaqService();
        int statu = service.updateFaqDoc(faq);
        if(statu !=0){
            return RetResponse.makeOKRsp();
        }
        return  RetResponse.makeErrRsp("updateQuestionDoc文档失败");
    }
    @GetMapping("/selectByYear")
    public RetResult<List<Map<String, Object>>> selectByYear(@RequestParam("yearNum")int yearNum) throws IOException, ParseException {
        FaqService service = this.service.getFaqService();
        List<Map<String, Object>> list = service.selectByYear(yearNum);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
    @GetMapping("/selectByMonth")
    public RetResult<List<Map<String, Object>>> selectByMont(@RequestParam("monthNum")int monthNum) throws IOException, ParseException {
        FaqService service = this.service.getFaqService();
        List<Map<String, Object>> list = service.selectByMonth(monthNum);
        if(list !=null){
            return RetResponse.makeOKRsp(list);
        }
        return  RetResponse.makeErrRsp("查询文档失败");
    }
}
