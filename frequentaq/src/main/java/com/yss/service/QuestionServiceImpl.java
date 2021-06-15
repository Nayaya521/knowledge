package com.yss.service;

import com.yss.dao.IQuestionDao;
import com.yss.pojo.Question;
import com.yss.utils.DateToDateTimeUtil;
import com.yss.utils.TimeTransferutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService{
    double DEFAULT_VERSION=3.0;
    @Autowired
    IQuestionDao dao;
    @Override
    public List<Question> selectAllQuestion() {
        return alterDateDifference(dao.selectAllQuestion());
    }

    @Override
    public int addQuestion(Question question) {
        String uname=question.getUName();
        String questiontitle=question.getQuestionTitle();
        int qid = dao.selectQuestionByuNameTitle(uname, questiontitle);
        if(qid==0){
            Timestamp questionTime = DateToDateTimeUtil.transfer();
            question.setQuestionTime(questionTime);
            dao.addQuestion(question);
            return question.getQid();
        }
       return 0;
    }
    @Override
    public Question selectQuestionByqid(int qid) {
        Question question = dao.selectQuestionByqid(qid);
        question.setDateDifference(TimeTransferutil.formationDate(question.getQuestionTime()));
        return question;
    }

    @Override
    public int deleteQuestion(int qid) {
        return dao.deleteQuestion(qid);
    }

    @Override
    public int updateQUestion(Question question) {
        return dao.updateQUestion(question);
    }

    @Override
    public List<Question> selectQuestionsbyTitle(String questionTitle) {
        return alterDateDifference(dao.selectQuestionsbyTitle(questionTitle));
    }

    @Override
    public List<Question> selectQuestionsByTag(String tag) {
        return alterDateDifference(dao.selectQuestionsByTag(tag));
    }

    @Override
    public List<Question> selectQuestionsByCommiter(String  uname) {
        return alterDateDifference(dao.selectQuestionsByCommiter(uname));

    }

    @Override
    public List<Question> selectQuestionsByYear(int year) {
        return alterDateDifference(dao.selectQuestionsByYear(year));
    }

    @Override
    public List<Question> selectQuestionsByMonth(int month) {
        return alterDateDifference(dao.selectQuestionsByMonth(month));
    }

    @Override
    public List<Question> selectQuestionsByPN(String platformName) {
        return alterDateDifference(dao.selectQuestionsByPN(platformName));
    }

    @Override
    public List<Question> selectQuestionsByYM(int year, int month) {
        return alterDateDifference(dao.selectQuestionsByYM(year,month));
    }

    @Override
    public List<Question> selectQuestionsByYP(int year, String platformName) {
        return alterDateDifference(dao.selectQuestionsByYP(year,platformName));
    }

    @Override
    public List<Question> selectQuestionsByMP(int month, String platformName) {
        return alterDateDifference(dao.selectQuestionsByMP(month,platformName));
    }

    @Override
    public List<Question> selectQuestionsByYMP(int year, int month, String platformName) {
        return alterDateDifference(dao.selectQuestionsByYMP(year,month,platformName));
    }

    @Override
    public String selectQuesionTitleByqid(int qid) {
        return dao.selectQuesionTitleByqid(qid);
    }
    public List<Question> alterDateDifference(List<Question>questions){
        for (Question question : questions) {
            question.setDateDifference(TimeTransferutil.formationDate(question.getQuestionTime()));
        }
        return questions;
    }

}
