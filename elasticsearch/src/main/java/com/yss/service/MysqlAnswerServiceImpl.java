package com.yss.service;

import com.yss.dao.IAnswerDao;
import com.yss.pojo.Answer;
import com.yss.util.TimeDifferenceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public class MysqlAnswerServiceImpl implements AnswerService {
    @Autowired
    IAnswerDao dao;

    @Override
    public int addAnswer(Answer answer) {
        return dao.addAnswer(answer);
    }

    @Override
    public int deleteAnswer(long aid) {
        return dao.deleteAnswer(aid);
    }

    @Override
    public int updateAnswer(Answer answer) {
        return dao.updateAnswer(answer);
    }

    @Override
    public List<Map<String, Object>> selectAllAnswer(long qid) {
        try {
            return TimeDifferenceUtils.listAdd(dao.selectAllAnswer(qid),"answerTime");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> selectAnswerByUser(String username) {
        try {
            return TimeDifferenceUtils.listAdd(dao.selectAnswerByUser(username),"answerTime");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateApplaud(long aid,int applaudNum) {
         applaudNum+=1;
         dao.updateApplaud(aid,applaudNum);
         return applaudNum;
    }
}
