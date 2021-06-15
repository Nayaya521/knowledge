package com.yss.dao;

import com.yss.pojo.Platform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface IPlatFormDao {
    //1.增加平台模块
    public int addPlatform(Platform platform);
    //2.查看平台模块
    public List<Platform> selectPlatForm();
    //3.根据平台模块名字，删除平台模块
    public int deletPlatformByPName(@Param("pName")String pName);
}
