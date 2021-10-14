package com.medolia.spring.demo.refactor.handlers;

import com.medolia.spring.demo.refactor.Order;
import com.medolia.spring.demo.refactor.OrderHandlerType;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */
@OrderHandlerType(source = "pc", payMethod = "")
public class PCOrderHandler implements OrderHandler{
    @Override
    public void handle(Order order) {
        System.out.println("处理 PC 端订单");
    }
}
