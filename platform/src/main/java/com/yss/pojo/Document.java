package com.yss.pojo;

import java.util.Date;
import java.util.List;

/**
 * 文章
 */
public class Document {
    int did;   //文章ID
    String docTitle; //文章标题
    String docType; //文章类型 faq platform
    List<String> docTags; //文章标签
    String docColumn; //文章专栏
    String docSubmitter; //文章提交者
    Date docSubmissionTime; //文章提交时间

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public List<String> getDocTags() {
        return docTags;
    }

    public void setDocTags(List<String> docTags) {
        this.docTags = docTags;
    }

    public String getDocColumn() {
        return docColumn;
    }

    public void setDocColumn(String docColumn) {
        this.docColumn = docColumn;
    }

    public String getDocSubmitter() {
        return docSubmitter;
    }

    public void setDocSubmitter(String docSubmitter) {
        this.docSubmitter = docSubmitter;
    }

    public Date getDocSubmissionTime() {
        return docSubmissionTime;
    }

    public void setDocSubmissionTime(Date docSubmissionTime) {
        this.docSubmissionTime = docSubmissionTime;
    }
}
