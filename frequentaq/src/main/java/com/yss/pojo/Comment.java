package com.yss.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 问题评论
 */
@Data
@ToString
public class Comment {
    private int cid;
    private String uName;//用户名称
    private int aid;//答案ID
    private int qid;//问题ID
    private String commentContent;//评论内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentTime;//评论时间
    private String timeDifference;//距离现在多久
}
