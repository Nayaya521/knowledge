package com.yss.service;

import com.yss.pojo.Platform;

import java.util.List;

public interface IPlatFormService {
    //1.增加平台模块
    public int addPlatform(Platform platform);
    //2.查看平台模块
    public List<Platform> selectPlatForm();
    //3.根据平台模块名字，删除平台模块
    public int deletPlatformByPName(String pName);
}
