package com.yss.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AnswerResult {
    private Answer answer;
    private List<Comment> comment;
    private List<Reply> reply;
}
