package com.yss.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 回答者
 */
@Data
@ToString
public class Answer {
    int aid;//答案的ID;
    int qid;//问题ID;
    int vid;//答案的版本
    String uName;//用户名称
    String answerContent;//回答内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date answerTime;//回答时间
    String timeDifference;
}
