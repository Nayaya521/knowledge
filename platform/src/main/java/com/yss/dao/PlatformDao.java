package com.yss.dao;

import com.yss.pojo.PlatformBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PlatformDao {
    public int add(PlatformBean platformBean);
    public int delete(@Param("version") String version, @Param("platformType") String platformType, @Param("question") String question);
    public PlatformBean findByQuestion(@Param("version") String version,@Param("platformType") String platformType,@Param("question") String question);
    public List<PlatformBean> findByVesion(String version);
    public List<PlatformBean> findByPlatform(String platformType);
    public List<PlatformBean> findByPlatVersion(@Param("platformType") String platformType,@Param("version") String version);
    public List<PlatformBean> selectAll();
    public int findIdByPlatForm(PlatformBean platformBean);
    public int findPlatformById(PlatformBean platformBean);
}
