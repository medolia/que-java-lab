package com.medolia.demo.retry.manual;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class DefaultRetryListener extends AbstractRetryListener {

    @Override
    public void notifyObserver() {
        System.out.println("this is a DefaultRetryListener");
    }
}
