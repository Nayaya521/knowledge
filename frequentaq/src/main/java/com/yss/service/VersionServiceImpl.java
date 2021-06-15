package com.yss.service;

import com.yss.dao.IVersionDao;
import com.yss.pojo.Version;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionServiceImpl implements IVersionService{
    @Autowired
    IVersionDao dao;
    @Autowired
    Version versionBean;
    @Value("${version.current}")
    String versionName;
    @Override
    public List<Version> selectDefaultVersion() {
        String[] split = versionName.split("\\.");
        versionBean.setMajorVersion(Integer.parseInt(split[0]));
        versionBean.setSecondVersion(Integer.parseInt(split[1]));
        versionBean.setFixedVersion(Integer.parseInt(split[2]));
        return dao.selectVersion(versionBean);
    }
    @Override
    public int addVersion(Version version) {
        //查看当前新增的版本是否在库中
        int vid = dao.selectIdByVersion(version);
        if(vid==0){
            dao.addVersion(version);
            return version.getVid();
        }
        return 0;

    }

    @Override
    public int deleteVersion(int vid) {
        return dao.deleteVersion(vid);
    }

    @Override
    public int updateVersion(Version version) {
        return dao.updateVersion(version);
    }

    @Override
    public int deteleBathVersion(List<Integer> vids) {
        return dao.deteleBathVersion(vids);
    }
}
