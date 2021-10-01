package com.medolia.spring.demo.aop.tmp;

import java.lang.annotation.*;

/**
 * @author lbli@trip.com
 * @date 2021/8/26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAround {
}
