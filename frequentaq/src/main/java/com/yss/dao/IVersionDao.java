package com.yss.dao;

import com.yss.pojo.Version;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface IVersionDao {
    //1.可视化的版本 version是默认版本
    public List<Version> selectVersion(Version version);
    //2.增加版本信息
    public int addVersion(Version version);
    //3.删除版本信息
    public int deleteVersion(int vid);
    //4.修改版本信息
    public int updateVersion(Version version);
    //5.批量删除版本信息
    public int deteleBathVersion(@Param("vids") List<Integer> vids);
    //6.根据version，查vid;
    public int selectIdByVersion(Version version);
}
