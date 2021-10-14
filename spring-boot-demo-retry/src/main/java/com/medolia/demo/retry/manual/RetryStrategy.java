package com.medolia.demo.retry.manual;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public interface RetryStrategy {

    /**
     * 初始化一些参数配置
     *
     * @param retry
     * @param retryTask
     */
    void initArgs(Retry retry,RetryTask retryTask);

    /**
     * 重试策略
     */
    void retryTask();

}
