package com.medolia.demo.retry.manual;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public abstract class AbstractRetryListener implements RetryListener {
    @Override
    public abstract void notifyObserver();
}
