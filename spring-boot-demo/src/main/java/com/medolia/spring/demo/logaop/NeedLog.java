package com.medolia.spring.demo.logaop;

import java.lang.annotation.*;

/**
 * @author lbli@trip.com
 * @date 2021/8/26
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLog {
    /**
     * 日志标题
     */
    String title() default "";

    /**
     * 返回值 定位tag 的表达式
     */
    String[] resTags() default {};
}
