package com.medolia.spring.demo.refactor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void processOrderNew() {
        Order order = new Order();
        order.setSource("mobile");
        order.setPayMethod("");
        orderService.processOrderNew(order);
    }
}