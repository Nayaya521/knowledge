package com.yss.service;

import com.yss.dao.IUserInfoDao;
import com.yss.pojo.UserResult;
import com.yss.utils.TimeTransferutil;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserInfoServiceImpl implements  IUserInfoservice{
    @Autowired
    IUserInfoDao dao;
    @Override
    public List<UserResult> selectCommentByuName(String uName) {
        List<UserResult> userResults = dao.selectCommentByuName(uName);
        for (UserResult userResult : userResults) {
            Date datetime = userResult.getDatetime();
            String dataDifference = TimeTransferutil.formationDate(datetime);
            userResult.setTimeDifference(dataDifference);
        }
        return userResults;
    }

    @Override
    public List<UserResult> selectReplyByuName(String uName) {
        List<UserResult> userResults =  dao.selectReplyByuName(uName);
        for (UserResult userResult : userResults) {
            Date datetime = userResult.getDatetime();
            String dataDifference = TimeTransferutil.formationDate(datetime);
            userResult.setTimeDifference(dataDifference);
        }
        return userResults;
    }

    @Override
    public List<UserResult> selectAnswerByuName(String uName) {
        List<UserResult> userResults = dao.selectAnswerByuName(uName);
        for (UserResult userResult : userResults) {
            Date datetime = userResult.getDatetime();
            String dataDifference = TimeTransferutil.formationDate(datetime);
            userResult.setTimeDifference(dataDifference);
        }
        return userResults;

    }
}
