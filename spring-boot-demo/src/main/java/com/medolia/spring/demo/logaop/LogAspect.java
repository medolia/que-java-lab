package com.medolia.spring.demo.logaop;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.medolia.spring.demo.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author lbli@trip.com
 * @date 2021/8/26
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private List<LogEntityConsumer> logEntityConsumerList;

    @Autowired
    public void setLogEntityConsumerList(List<LogEntityConsumer> logEntityConsumerList) {
        this.logEntityConsumerList = logEntityConsumerList;
    }

    @Around("@annotation(com.medolia.spring.demo.logaop.NeedLog)")
    public Object aroundLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        NeedLog needLog = method.getDeclaredAnnotation(NeedLog.class);
        String title = StringUtils.isBlank(needLog.title()) ?
                String.format("%s#%s", signature.getDeclaringType().getSimpleName(), signature.getName()) :
                needLog.title();
        Map<String, String> tags = Maps.newHashMap();

        StringBuilder content = new StringBuilder();
        content.append("[parameters]: \n");

        int i = 0;
        for (Parameter parameter : method.getParameters()) {
            Object arg = joinPoint.getArgs()[i++];
            String[] expressions = Optional.ofNullable(parameter.getAnnotation(NeedAddTag.class))
                    .map(NeedAddTag::value)
                    .orElse(new String[0]);
            tags.putAll(tryParseTag(arg, expressions));
            content.append(String.format("%s=%s%n", parameter.getName(), JsonUtils.serialize(arg)));
        }

        Level level = Level.INFO;
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            level = Level.ERROR;
        }

        content.append("[result]: \n").append(JsonUtils.serialize(result));
        Map<String, String> tagsFromResult = tryParseTag(result, needLog.resTags());

        Map<String, String> allTags = Maps.newHashMap();
        allTags.putAll(tags);
        allTags.putAll(tagsFromResult);
        LogEntity logEntity = LogEntity.builder()
                .title(title)
                .content(content.toString())
                .level(level)
                .tags(allTags)
                .build();
        Optional.ofNullable(this.logEntityConsumerList)
                .orElse(Lists.newArrayList())
                .forEach(logEntityConsumer -> logEntityConsumer.consume(logEntity));

        return result;
    }

    private Map<String, String> tryParseTag(Object obj, String[] expressions) {
        Map<String, String> result = Maps.newHashMap();

        try {
            assert obj != null && expressions.length > 0;
            JsonNode jsonNode = JsonUtils.getMapper().valueToTree(obj);
            for (String expression : expressions) {
                JsonNode tmp = jsonNode;
                String finalNode = StringUtils.EMPTY;
                for (String node : expression.split("\\.")) {
                    tmp = tmp.path(node);
                    finalNode = node;
                }
                result.put(finalNode, tmp.toString());
            }
        } catch (Exception e) {
            // ignore
        }

        return result;
    }

}
