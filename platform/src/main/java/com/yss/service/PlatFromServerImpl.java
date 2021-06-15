package com.yss.service;

import com.yss.dao.PlatformDao;
import com.yss.pojo.PlatformBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatFromServerImpl implements  IPlatformService{

    @Autowired
    PlatformDao platformDao;

    /**
     * 增加一条文档
     * @param platformBean
     * @return
     */
    @Override
    public int add(PlatformBean platformBean) {
        System.out.println(platformBean);
       return platformDao.add(platformBean);
    }

    /**
     * 删除一条文档
     * @param version
     * @param platformType
     * @param key
     * @return
     */
    @Override
    public int delete(String version, String platformType, String key) {
        return platformDao.delete(version, platformType, key);
    }

    /**
     * 根据系统版本和平台类型以及问题定位到一条数据并获得
     * @param version
     * @param platformType
     * @param question
     * @return
     */
    @Override
    public PlatformBean findByQuestion(String version, String platformType, String question) {
      return platformDao.findByQuestion(version,platformType,question);
    }

    /**
     * 通过版本查询一条文档
     * @param version
     * @return
     */
    @Override
    public List<PlatformBean> findByVesion(String version) {
        List<PlatformBean> vesions = platformDao.findByVesion(version);
        return vesions;
    }

    /**
     * 根据平台类型查询一条文档
     * @param platformType
     * @return
     */
    @Override
    public List<PlatformBean> findByPlatform(String platformType) {
        return platformDao.findByPlatform(platformType);
    }

    /**
     * 根据平台类型和版本查询所有的文档
     * @param platformType
     * @param version
     * @return
     */
    @Override
    public List<PlatformBean> findByPlatVersion(String platformType, String version) {
        return platformDao.findByPlatVersion(platformType,version);
    }

    /**
     * 查询所有的文档
     * @return
     */
    @Override
    public List<PlatformBean> selectAll() {
        return platformDao.selectAll();
    }

    /**
     * 根据文档查询id
     * @param platformBean
     * @return
     */
    @Override
    public int findIdByPlatForm(PlatformBean platformBean) {
        return platformDao.findIdByPlatForm(platformBean);
    }

    /**
     * 根据id查询文档并更新
     * @param platformBean
     * @return
     */
    @Override
    public int findPlatformById(PlatformBean platformBean) {
        System.out.println(platformBean);
        return platformDao.findPlatformById(platformBean);
    }

}
