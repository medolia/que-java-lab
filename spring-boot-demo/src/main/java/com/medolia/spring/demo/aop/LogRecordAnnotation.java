package com.medolia.spring.demo.aop;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author lbli
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecordAnnotation {
    /**
     * 操作日志成功的文本模板
     */
    String success();
    /**
     * 操作日志失败的文本模板
     */
    String fail() default "";
    /**
     * 操作日志的执行人
     */
    String operator() default "";
    /**
     * 操作日志绑定的业务对象标识
     */
    String bizNo();
    /**
     * 操作日志的种类
     */
    String category() default "";
    /**
     * 扩展参数，记录操作日志的修改详情
     */
    String content() default "";
    /**
     * 记录日志的条件
     */
    String condition() default "";
}
