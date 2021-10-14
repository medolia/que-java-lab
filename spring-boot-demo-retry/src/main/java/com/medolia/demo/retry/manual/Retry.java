package com.medolia.demo.retry.manual;

import java.lang.annotation.*;

/**
 * @author lbli@trip.com
 * @since
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    int maxAttempts() default 3;

    int delay() default 3000;

    Class<? extends Throwable>[] value() default {};

    Class<? extends RetryStrategy> strategy() default FastRetryStrategy.class;

    Class<? extends RetryListener> listener() default AbstractRetryListener.class;
}
