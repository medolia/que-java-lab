package com.medolia.demo.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lbli
 * @date 2022/6/28
 */
@SuppressWarnings("all")
@Slf4j
public class RateLimiterTest {

    @Test
    void testDemo() {
        //限流器流速：2个请求/秒
        RateLimiter limiter = RateLimiter.create(2.0);
        //执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(8);
        //记录上一次执行时间
        final long[] prev = {System.nanoTime()};
        //测试执行20次
        for (int i = 0; i < 30; i++) {
            //提交任务异步执行
            int finalI = i;
            es.execute(() -> {
                //限流器限流
                limiter.acquire();
                long cur = System.nanoTime();
                if (finalI > 0) {
                    //打印时间间隔：毫秒
                    log.info("{}", (cur - prev[0]) / 1000_000);
                }
                prev[0] = cur;
            });
        }

        es.shutdown();

        while (!es.isTerminated()) {
            //
        }
    }
}
