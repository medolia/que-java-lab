package com.medolia.spring.demo.logaop;

import java.lang.annotation.*;

/**
 * @author lbli
 * @date 2022/7/23
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedAddTag {

    /**
     * 定位 tag 的表达式
     */
    String[] value();

}
