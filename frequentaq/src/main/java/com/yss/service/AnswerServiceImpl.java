package com.yss.service;

import com.yss.dao.IAnswerDao;
import com.yss.dao.IVersionDao;
import com.yss.pojo.*;
import com.yss.utils.DateToDateTimeUtil;
import com.yss.utils.TimeTransferutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements IAnswerService{
    @Autowired
    IAnswerDao dao;
    @Autowired
    ApplicationContext context;
    @Override
    public int addAnswer(Answer answer) {
       dao.addAnswer(answer);
       return answer.getAid();
    }

    @Override
    public int deleteAnswer(int anId) {
        return dao.deleteAnswer(anId);
    }

    @Override
    public List<Answer> selectAnswerByqid(int qid, int vid) {
        List<Answer> answers = dao.selectAnswerByqid(qid, vid);
        for (Answer answer : answers) {
            String dateDifference = TimeTransferutil.formationDate(answer.getAnswerTime());
            answer.setTimeDifference(dateDifference);
        }
        return answers;
    }

    @Override
    public List<AnswerResult> selectAnswerReultByqid(int qid, int vid) {
        List<Answer> answers = dao.selectAnswerByqid(qid, vid);
        List<AnswerResult> results=new ArrayList<>();
        for (Answer answer : answers) {
            String dateDifference = TimeTransferutil.formationDate(answer.getAnswerTime());
            answer.setTimeDifference(dateDifference);
            int aid = answer.getAid();
            List<Comment> comments = selectCommentByAnId(aid);
            List<Reply> replies = selectReplyByAnId(aid);
            AnswerResult result = context.getBean("answerResult",AnswerResult.class);
            result.setAnswer(answer);
            result.setComment(comments);
            result.setReply(replies);
            results.add(result);
        }
        return results;
    }

    @Override
    public List<Comment> selectCommentByAnId(int aid) {
        List<Comment> comments = dao.selectCommentByAnId(aid);
        for (Comment comment : comments) {
            String dateDifference = TimeTransferutil.formationDate(comment.getCommentTime());
            comment.setTimeDifference(dateDifference);
        }
        return comments;
    }

    @Override
    public List<Reply> selectReplyByAnId(int aid) {
        List<Reply> replies = dao.selectReplyByAnId(aid);
        for (Reply reply : replies) {
            String dateDifference = TimeTransferutil.formationDate(reply.getReplyTime());
            reply.setTimeDifference(dateDifference);
        }
        return replies;
    }

    @Override
    public int addComment(Comment comment) {
        return dao.addComment(comment);
    }

    @Override
    public int deleteComment(int cid) {
        return dao.deleteComment(cid);
    }

    @Override
    public int addReply(Reply reply) {
        return dao.addReply(reply);
    }

    @Override
    public int deleteReply(int rid) {
        return dao.deleteReply(rid);
    }

    @Override
    public int selectVidByAid(int aid) {
       return  dao.selectVidByAid(aid);
    }


}
