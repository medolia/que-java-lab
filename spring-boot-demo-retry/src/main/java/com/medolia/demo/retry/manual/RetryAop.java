package com.medolia.demo.retry.manual;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@Aspect
@Component
public class RetryAop {

    @Resource
    private ApplicationContext applicationContext;
    @Pointcut("@annotation(com.medolia.demo.retry.manual.Retry)")
    public void pointCut() {
    }
    @Around(value = "pointCut()")
    public Object doBiz(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Retry retry = method.getDeclaredAnnotation(Retry.class);
        RetryStrategy retryStrategy = applicationContext.getBean(retry.strategy());
        RetryTask retryTask = new RetryTaskImpl(point);
        retryStrategy.initArgs(retry, retryTask);
        try {
            Object result = point.proceed();
            return result;
        } catch (Throwable throwable) {
            retryStrategy.retryTask();
        }
        return null;
    }

    private class RetryTaskImpl implements RetryTask {
        private ProceedingJoinPoint proceedingJoinPoint;
        private Object result;
        private volatile Boolean asyncRetryState = null;
        public RetryTaskImpl(ProceedingJoinPoint proceedingJoinPoint) {
            this.proceedingJoinPoint = proceedingJoinPoint;
        }
        public ProceedingJoinPoint getProceedingJoinPoint() {
            return proceedingJoinPoint;
        }
        public void setProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
            this.proceedingJoinPoint = proceedingJoinPoint;
        }
        public Object getResult() {
            return result;
        }
        public void setResult(Object result) {
            this.result = result;
        }
        public Boolean getAsyncRetryState() {
            return asyncRetryState;
        }
        public void setAsyncRetryState(Boolean asyncRetryState) {
            this.asyncRetryState = asyncRetryState;
        }
        @Override
        public Object getRetryResult() {
            return result;
        }
        @Override
        public Boolean getRetryStatus() {
            return asyncRetryState;
        }
        @Override
        public void setRetrySuccess() {
            this.setAsyncRetryState(true);
        }
        @Override
        public void doTask() throws Throwable {
            this.result = proceedingJoinPoint.proceed();
        }
    }
}
