package com.medolia.spring.demo.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class HashedWheelTimerTest {

    @Test
    void testDemo() {

        CountDownLatch latch = new CountDownLatch(1);

        HashedWheelTimer timer = new HashedWheelTimer(
                new DefaultThreadFactory("hadluo-timer"),
                100,
                TimeUnit.MILLISECONDS,
                1024,
                false);
        // 构建一个延时任务
        timer.newTimeout(
                (time) -> {
                    System.err.println("12s time's up, hah~~");
                    latch.countDown();
                },
                5, TimeUnit.SECONDS);

        try {
            latch.await();
        } catch (InterruptedException e) {
            //
        }

    }
}
