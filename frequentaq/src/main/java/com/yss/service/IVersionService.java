package com.yss.service;

import com.yss.pojo.Version;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IVersionService {
    //2.默认展示的版本
    public List<Version> selectDefaultVersion();
    //3.增加版本信息
    public int addVersion(Version version);
    //4.删除版本信息
    public int deleteVersion(int vid);
    //5.修改版本信息
    public int updateVersion(Version version);
    //6.批量删除版本信息
    public int deteleBathVersion(List<Integer> vids);
}
