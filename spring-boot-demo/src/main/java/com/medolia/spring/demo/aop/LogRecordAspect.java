package com.medolia.spring.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @author lbli
 */
@Aspect
@Component
@Slf4j
public class LogRecordAspect {
    private List<ContextProcessor> processors;

    @Autowired
    public void setProcessors(List<ContextProcessor> processors) {
        this.processors = processors;
    }

    @After("@annotation(com.medolia.spring.demo.aop.LogRecordAnnotation)")
    public Object afterComplete(JoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogRecordAnnotation annotation = AnnotationUtils.findAnnotation(method, LogRecordAnnotation.class);
        if (Objects.isNull(annotation)) {
            return null;
        }

        // TODO: SPEL 解析模板，生成可供消费的 context 实体
        String parsedContent = parseContent(annotation.content(), joinPoint);
        LogRecordContext context = LogRecordContext.builder()
                .bizNumber(annotation.bizNo())
                .category(annotation.category())
                .condition(annotation.condition())
                .operator(annotation.operator())
                .content(parsedContent).build();

        if (!CollectionUtils.isEmpty(processors)) {
            processors.forEach(e -> e.consume(context));
        }

        return null;
    }

    // 例子: 修改订单配送地址 {# delivery.address} 到 {# newAddress}
    private String parseContent(String content, JoinPoint joinPoint) {

        // {# delivery.address} -> 变量名为 delivery, 调用 getAddress(); {# newAddress} -> 变量名为 newAddress

        return null;
    }

}
