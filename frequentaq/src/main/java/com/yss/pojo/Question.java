package com.yss.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Data
@ToString
public class Question {
    private int qid; //问题ID
    private String questionTitle;//问题的标题  "tags":["nice","颜值高","good","物美价廉","宅男专属 "]
    private String questionDesc;//问题的描述
    private String qType;//问题的类型
    private String  questionTags;//问题的标签
    private String uName;//用户名称
    private String platformName;//平台名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date questionTime;//问题提问时间
    private String dateDifference;
    
}
