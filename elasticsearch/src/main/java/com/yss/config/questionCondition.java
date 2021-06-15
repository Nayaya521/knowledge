package com.yss.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 是否创建restHighLevelClient的条件
 */
public class questionCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        System.out.println("是否为true:"+conditionContext.getEnvironment().getProperty("elasticsearch.started").equals("true"));
        return conditionContext.getEnvironment().getProperty("elasticsearch.started").equals("true");
    }
}
