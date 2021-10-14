package com.medolia.demo.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@RestController
@RequestMapping(value = "/retry")
public class RetryController {

    /**
     * 抛出异常后执行重试逻辑
     */
    @GetMapping(value = "/test")
    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int retryServiceOne(int code) throws Exception {
        System.out.println("retryServiceOne 被调用，时间" + LocalTime.now());
        System.out.println("执行当前线程为:" + Thread.currentThread().getName());
        if(code==0){
            throw new Exception("业务执行异常！");
        }
        System.out.println("retryServiceOne 执行成功！");
        return 200;
    }
}
