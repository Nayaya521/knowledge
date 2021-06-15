package com.yss.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Draft {
    private int drId;//草稿表ID
    private String Uuid;
    private String draftTitle;//草稿标题
    private String uname;//用户名称
    private String filePath;//文件路径
    private String fileName;//文件名字
    private int fileSize;//文件大小
}
