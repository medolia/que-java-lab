package com.medolia.spring.demo.event;

import java.util.Date;

/**
 * @author lbli@trip.com
 * @date 2021/9/15
 */
public class CheckoutEvent {
    private final ShoppingCart cart;
    private final Date time;

    public CheckoutEvent(ShoppingCart cart, Date time) {
        this.cart = cart;
        this.time = time;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Date getTime() {
        return time;
    }
}
