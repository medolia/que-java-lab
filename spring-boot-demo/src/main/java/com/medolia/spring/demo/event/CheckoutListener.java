package com.medolia.spring.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lbli@trip.com
 * @date 2021/9/15
 */
@Component
public class CheckoutListener {

    @EventListener
    public void onApplicationEvent(CheckoutEvent event) {
        System.out.printf("checkout event [ %s ]\n", event.getTime());
    }
}
