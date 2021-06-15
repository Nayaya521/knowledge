package com.yss.controller;

import com.yss.pojo.Question;
import com.yss.pojo.RetResponse;
import com.yss.pojo.RetResult;
import com.yss.pojo.Version;
import com.yss.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/version")
public class VersionController {
    @Autowired
    IVersionService service;
    //1.默认展示的版本
    @GetMapping("/selectDefaultVersion")
    public RetResult<List<Version>> selectDefaultVersion(){
        List<Version> versions = service.selectDefaultVersion();
        if(versions.size()!=0){
            return RetResponse.makeOKRsp(versions);
        }
        return RetResponse.makeRsp(500,"查询默认版本失败");
    }
    //2.增加版本信息
    @PostMapping("/addVersion")
    public RetResult<Integer> addVersion(Version version){
       int vid = service.addVersion(version);
        if(vid!=0){
            return RetResponse.makeOKRsp(vid);
        }
        return RetResponse.makeRsp(500,"增加版本信息失败,系统中已经存在此版本了");
    }
    //3.删除版本信息
    @GetMapping("/deleteVersion")
    public RetResult<Integer> deleteVersion(int vid){
        int versionId = service.deleteVersion(vid);
        if(versionId!=0){
            return RetResponse.makeOKRsp(versionId);
        }
        return RetResponse.makeRsp(500,"删除版本信息失败");
    }
    //4.修改版本信息
    @PostMapping("/updateVersion")
    public RetResult<Integer> updateVersion(Version version){
        int versionId = service.updateVersion(version);
        if(versionId!=0){
            return RetResponse.makeOKRsp(versionId);
        }
        return RetResponse.makeRsp(500,"修改版本信息失败");
    }
    //5.批量删除版本信息
    @PostMapping("/deleteBatchVersion")
    public RetResult<Integer> deteleBathVersion(@RequestBody List<Integer> vids){
        int versionId = service.deteleBathVersion(vids);
        if(versionId!=0){
            return RetResponse.makeOKRsp(versionId);
        }
        return RetResponse.makeRsp(500,"批量删除版本信息失败");
    }
}
