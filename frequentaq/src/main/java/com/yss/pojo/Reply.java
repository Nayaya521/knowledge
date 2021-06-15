package com.yss.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Reply {
    private int rid;//回复表ID
    private String fromuName;//回复者名称;
    private String touName;//被回复着名称;
    private String replyContent;//回复的内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyTime;//回复时间
    private int aid;//答案ID
    private int qid;//问题ID
    private String timeDifference;//距离现在多久
}
