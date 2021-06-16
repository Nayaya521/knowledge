package com.yss.service;

import com.yss.pojo.Faq;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MysqlFaqServiceImpl implements FaqService{
    @Override
    public long createFaqDoc(Faq faq) {
        return 0;
    }

    @Override
    public int deleteFaqDoc(long qid) {
        return 0;
    }

    @Override
    public int updateFaqDoc(Faq faq) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectFaqDocByPlatform(String platformname) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectFaqDocByKeyWord(String keyWord) {
        return null;
    }

    @Override
    public List<String> completion(String prefix) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectFaqDocByUserName(String questionername) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectByYear(int yearNum) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectByMonth(int monthNum) {
        return null;
    }

    @Override
    public Map<String, Object> selectFaqDocByQid(long qid) {
        return null;
    }
}
