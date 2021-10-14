package com.medolia.spring.demo.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/**
 * @author lbli@trip.com
 * @date 2021/9/15
 */
@RestController
public class EventTrigger {

    private Cashier cashier;

    public EventTrigger(Cashier cashier) {
        this.cashier = cashier;
    }

    @RequestMapping("/demo/event")
    public String trigger() throws IOException {
        cashier.checkout(new ShoppingCart());
        return "event trigger";
    }
}
