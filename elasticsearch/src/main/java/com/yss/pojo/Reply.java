package com.yss.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Reply {
    private int rid;//回复表ID
    private String fromname;//回复者名称;
    private String toname;//被回复着名称;
    private String replycontent;//回复的内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replytime;//回复时间
    private int r_aid;//答案ID
}
