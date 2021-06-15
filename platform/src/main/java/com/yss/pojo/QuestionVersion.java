package com.yss.pojo;

import java.util.List;

public class QuestionVersion {
    int vid;//版本号
    int qid;//问题ID
    String questionVersion;
    List<Integer> aid;//回答者ID;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getqid() {
        return qid;
    }

    public void setqid(int qid) {
        this.qid = qid;
    }

    public String getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(String questionVersion) {
        this.questionVersion = questionVersion;
    }

    public List<Integer> getAid() {
        return aid;
    }

    public void setAid(List<Integer> aid) {
        this.aid = aid;
    }
}
