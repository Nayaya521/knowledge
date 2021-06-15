package com.yss.pojo;

/**
 * 文章版本
 */
public class DocVersion {
    int vid; //文章版本ID
    int did; //文章的ID
    String uuid;
    String docVersion; //版本
    String docFilePath;//文章内容的存放路径
    String docFileName;//文章内容的存放名称
    int docFileSize;//文章的大小

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDocVersion() {
        return docVersion;
    }

    public void setDocVersion(String docVersion) {
        this.docVersion = docVersion;
    }

    public String getDocFilePath() {
        return docFilePath;
    }

    public void setDocFilePath(String docFilePath) {
        this.docFilePath = docFilePath;
    }

    public String getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    public int getDocFileSize() {
        return docFileSize;
    }

    public void setDocFileSize(int docFileSize) {
        this.docFileSize = docFileSize;
    }
}
