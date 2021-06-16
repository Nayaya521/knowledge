package com.yss.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class Faq {
    private long qid;
    private String questionTitle;//问题的标题  "tags":["nice","颜值高","good","物美价廉","宅男专属 "]
    private String qType;//问题的类型
    private String questionFileUuid;
    private String questionTags;//问题的标签
    private String questionerName;//用户名称
    private String platformName;//平台名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date questionTime;//问题提问时间
    private String dateDifference;
    private String answerContent;//回答的内容
}
