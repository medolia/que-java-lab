package com.medolia.spring.demo.refactor.handlers;

import com.medolia.spring.demo.refactor.Order;
import com.medolia.spring.demo.refactor.OrderHandlerType;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */
@OrderHandlerType(source = "mobile", payMethod = "")
public class MobileOrderHandler implements OrderHandler{
    @Override
    public void handle(Order order) {
        System.out.println("处理移动端订单");
    }
}
