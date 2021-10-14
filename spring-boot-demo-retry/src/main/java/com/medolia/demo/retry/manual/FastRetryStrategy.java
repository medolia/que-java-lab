package com.medolia.demo.retry.manual;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ExecutorService;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class FastRetryStrategy implements RetryStrategy, ApplicationContextAware {
    private Retry retry;
    private RetryTask retryTask;
    private ApplicationContext applicationContext;
    private ExecutorService retryThreadPool;
    public FastRetryStrategy() {
    }
    public ExecutorService getRetryThreadPool() {
        return retryThreadPool;
    }
    public void setRetryThreadPool(ExecutorService retryThreadPool) {
        this.retryThreadPool = retryThreadPool;
    }
    @Override
    public void initArgs(Retry retry, RetryTask retryTask) {
        this.retry = retry;
        this.retryTask = retryTask;
    }
    @Override
    public void retryTask() {
        if (!FastRetryStrategy.class.equals(retry.strategy())) {
            System.err.println("error retry strategy");
            return;
        }
        //安全类型bean查找
        String[] beanNames = applicationContext.getBeanNamesForType(retry.listener());
        RetryListener retryListener = null;
        if (beanNames != null && beanNames.length > 0) {
            retryListener = applicationContext.getBean(retry.listener());
        }
        Class<? extends Throwable>[] exceptionClasses = retry.value();
        RetryListener finalRetryListener = retryListener;
        //如果没有支持异步功能，那么在进行重试的时候就会一直占用着服务器的业务线程，导致服务器线程负载暴增
        retryThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= retry.maxAttempts(); i++) {
                    int finalI = i;
                    try {
                        retryTask.doTask();
                        retryTask.setRetrySuccess();
                        return;
                    } catch (Throwable e) {
                        for (Class<? extends Throwable> clazz : exceptionClasses) {
                            if (e.getClass().equals(clazz) || e.getClass().isInstance(clazz)) {
                                if (finalRetryListener != null) {
                                    finalRetryListener.notifyObserver();
                                }
                                System.err.println("[FastRetryStrategy] retry again,attempt's time is " + finalI + ",tims is " + System.currentTimeMillis());
                                try {
                                    Thread.sleep(retry.delay());
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                continue;
                            }
                        }
                    }
                }
            }
        });
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ExecutorService executorService = (ExecutorService) applicationContext.getBean("retryThreadPool");
        this.setRetryThreadPool(executorService);
    }
}
