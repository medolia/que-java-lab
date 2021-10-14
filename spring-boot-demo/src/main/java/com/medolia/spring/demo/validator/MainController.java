package com.medolia.spring.demo.validator;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

/**
 * @author lbli@trip.com
 * @date 2021/8/10
 */
@RestController
public class MainController {

    public void addUser(@Validated({Default.class, UpdateAction.class}) UserAO userAO) {

    }
}
