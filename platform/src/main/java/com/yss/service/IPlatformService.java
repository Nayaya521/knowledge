package com.yss.service;

import com.yss.pojo.PlatformBean;

import java.util.List;

public interface IPlatformService{
    public int add(PlatformBean platformBean);
    public int delete(String version,String platformType,String question);
    public PlatformBean findByQuestion(String version,String platformType,String question);
    public List<PlatformBean> findByVesion(String version);
    public List<PlatformBean> findByPlatform(String platformType);
    public List<PlatformBean> findByPlatVersion(String platformType,String version);
    public List<PlatformBean> selectAll();
    public int findIdByPlatForm(PlatformBean platformBean);
    public int findPlatformById(PlatformBean platformBean);
}
