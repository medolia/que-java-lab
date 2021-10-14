package com.medolia.demo.retry.manual;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public interface RetryTask {

    Object getRetryResult();

    Boolean getRetryStatus();

    void setRetrySuccess();

    void doTask() throws Throwable;
}
