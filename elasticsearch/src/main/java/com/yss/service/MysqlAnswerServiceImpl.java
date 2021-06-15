package com.yss.service;

import com.yss.pojo.Answer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MysqlAnswerServiceImpl implements AnswerService {

    @Override
    public int addAnswer(Answer answer) {
        return 0;
    }

    @Override
    public int deleteAnswer(int aid) {
        return 0;
    }

    @Override
    public int updateAnswer(Answer answer) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectAllAnswer() {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectAnswerByUser(String username) {
        return null;
    }
}
