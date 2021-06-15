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
    long aid;//答案的ID
    long qid;//问题Id
    String version;//版本
    String answerName;//用户名称
    String answerFileUuid;//回答内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date answerTime;//回答时间
    int answerApplaud;//赞同数
    String answerContent;//回答的内容

}
