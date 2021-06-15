package com.yss.service;

import com.yss.dao.IPlatFormDao;
import com.yss.pojo.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements IPlatFormService{
    @Autowired
    IPlatFormDao dao;
    @Override
    public int addPlatform(Platform platform) {
        List<Platform> platforms = dao.selectPlatForm();
        for (Platform platformT : platforms) {
            if (platformT.getPName().equals(platform.getPName())) {
                return 0;
            }
        }

        return dao.addPlatform(platform);
    }

    @Override
    public List<Platform> selectPlatForm() {
        return dao.selectPlatForm();
    }

    @Override
    public int deletPlatformByPName(String pName) {
        return dao.deletPlatformByPName(pName);
    }
}
