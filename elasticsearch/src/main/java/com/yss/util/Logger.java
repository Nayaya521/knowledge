package com.yss.util;

import com.yss.service.EsQuestionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
public class Logger {
    private final static org.slf4j.Logger logger= LoggerFactory.getLogger(EsQuestionServiceImpl.class);
    public static org.slf4j.Logger getLogger(){
        return logger;
    }
}
