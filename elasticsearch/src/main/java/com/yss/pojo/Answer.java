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
   private long aid;//答案的ID
    private long qid;//问题Id
    private String version;//版本
   private String answerName;//用户名称
    private String answerFileUuid;//回答内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date answerTime;//回答时间
    private int answerApplaud;//赞同数
   private String answerContent;//回答的内容
    private String dateDifference;

}
