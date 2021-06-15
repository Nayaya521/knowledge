package com.yss.service;

import com.yss.dao.IQuestionDao;
import com.yss.pojo.Platform;
import com.yss.pojo.Question;
import com.yss.util.TimeDifferenceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public class MysqlQuestionServiceImpl implements QuestionService{
    @Autowired
    private IQuestionDao dao;

    @Override
    public List<Map<String, Object>> selectAll() throws IOException {
        try {
           return TimeDifferenceUtils.listAdd(dao.selectAll());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> selectByKeyWord(String keyWord){
        try {
            return TimeDifferenceUtils.listAdd(dao.selectByKeyWord(keyWord));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> selectByPlatform(String platformname){
        try {
            TimeDifferenceUtils.listAdd(dao.selectByPlatform(platformname));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> selectQuestionByUserName(String questionername) {
        try {
          return   TimeDifferenceUtils.listAdd(dao.selectQuestionByUserName(questionername));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> completion(String prefix) throws IOException {
        return dao.completion(prefix);
    }

    @Override
    public Map<String, Object> selectByQid(long qid) {
        try {
            return  TimeDifferenceUtils.mapAdd(dao.selectByQid(qid));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long createQuestionDoc(Question question) {
        try {
            dao.createQuestionDoc(question);
            return (question.getQid());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteQuestionDoc(long qid) {
        try {
            return dao.deleteQuestionDoc(qid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateQuestionDoc(Question question)  {
        try {
            return dao.updateQuestionDoc(question);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectByYear(int yearNum){
        try {
            return TimeDifferenceUtils.listAdd(dao.selectByYear(yearNum));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }

    @Override
    public List<Map<String, Object>> selectByMonth(int monthNum){
        try {
            return TimeDifferenceUtils.listAdd(dao.selectByMonth(monthNum));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //11.查看所有的平台名称
    public List<Platform> selectAllPlatform() {
        return dao.selectAllPlatform();
    }
}
