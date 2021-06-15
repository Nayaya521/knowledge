package com.yss.controller;

import com.yss.pojo.Platform;
import com.yss.pojo.RetResponse;
import com.yss.pojo.RetResult;
import com.yss.service.IPlatFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/platform")
public class PlatFormController {
    @Autowired
    IPlatFormService service;
    //1.增加平台模块
    @PostMapping("/addPlatform")
    public RetResult<Integer> addPlatform(Platform platform){
        int id= service.addPlatform(platform);
        if(id!=0){
            return RetResponse.makeOKRsp(id);
        }
        return RetResponse.makeRsp(500,"增加平台模块失败,平台模块已经存在");
    }
    //2.查看平台模块
    @GetMapping("/selectPlatForm")
    public RetResult<List<Platform>>selectPlatForm(){
        List<Platform> platforms = service.selectPlatForm();
        if(platforms.size()!=0){
            return RetResponse.makeOKRsp(platforms);
        }
        return RetResponse.makeRsp(500,"查看平台模块失败");
    }
    //3.根据平台模块名字，删除平台模块
    @GetMapping("/deletPlatformByPName")
    public RetResult<Integer>deletPlatformByPName(String pName){
        int id= service.deletPlatformByPName(pName);
        if(id!=0){
            return RetResponse.makeOKRsp(id);
        }
        return RetResponse.makeRsp(500,"删除模块失败");
    }
}
