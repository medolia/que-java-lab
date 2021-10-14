package com.medolia.spring.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author lbli@trip.com
 * @date 2021/8/26
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Around("@annotation(NeedAroundLog)")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("before method execution");
        Object proceed = joinPoint.proceed();
        log.info("after method execution");
        return proceed;
    }
}
