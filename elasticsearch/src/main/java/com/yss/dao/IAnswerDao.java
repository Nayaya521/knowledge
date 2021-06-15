package com.yss.dao;

import com.yss.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface IAnswerDao {
    //1.增加一个问题的答案
    public int addAnswer(Answer answer);
    //2.删除一个问题的答案
    public int deleteAnswer(long aid);
    //3.更新一个问题的答案
    public int updateAnswer(Answer answer);
    //4.查找一个问题的所有答案
    public List<Map<String,Object>> selectAllAnswer(long qid);
    //5.根据用户查找所回答的答案
    public List<Map<String,Object>>selectAnswerByUser(String username);
    //6.更新一个问题的答案的赞同数
    public int updateApplaud(@Param("aid") long aid, @Param("applaudNum") int applaudNum);
}
