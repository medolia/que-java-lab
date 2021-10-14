package com.medolia.spring.demo.refactor.handlers;

import com.medolia.spring.demo.refactor.Order;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */
public interface OrderHandler {
    void handle(Order order);
}
