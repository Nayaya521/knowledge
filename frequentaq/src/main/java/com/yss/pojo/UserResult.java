package com.yss.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserResult {
    private int aid;
    private String questioner;//问题提问者
    private String questionTitle;//问题的标题
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date datetime;//评论，回复，回答的时间
    private String timeDifference;
}
