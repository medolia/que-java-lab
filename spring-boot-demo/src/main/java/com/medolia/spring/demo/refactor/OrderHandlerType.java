package com.medolia.spring.demo.refactor;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OrderHandlerType {
    String source();
    String payMethod();
}
