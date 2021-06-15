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
    private String commentername;//用户名称
    private int c_aid;//答案ID
    private String commentcontent;//评论内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commenttime;//评论时间
}
