package com.yss.pojo;

import java.util.Date;
import java.util.List;

public class Question {
    int qid; //问题ID
    String questionTitle;//问题的标题
    String qType;//问题的类型
    List<String> questionTags;//问题的标签
    String questioner;//问题提问者
    Date questionTime;//问题提问时间

    public int getqid() {
        return qid;
    }

    public void setqid(int qid) {
        this.qid = qid;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionType() {
        return qType;
    }

    public void setQuestionType(String qType) {
        this.qType = qType;
    }

    public List<String> getQuestionTags() {
        return questionTags;
    }

    public void setQuestionTags(List<String> questionTags) {
        this.questionTags = questionTags;
    }

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }
}
