package com.medolia.lab.util.function;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author lbli
 * @date 2021/10/3
 */
@Slf4j
public class CustomSuppliersTest {

    CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void memoize() {
        Supplier<Integer> supplier = () -> {
            try {
                AtomicInteger result = new AtomicInteger(0);
                IntStream.range(0, 100000).forEach(e -> {
                    result.getAndAdd(RandomUtils.nextInt());
                });
                return result.intValue();
            } catch (Exception e) {
                return 0;
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(8);
        IntStream.range(0, 20).forEach(e -> {
            service.submit(() -> logDuration(e, supplier));
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            //
        }
    }

    private void logDuration(int e, Supplier<Integer> supplier) {
        // log.info("the {} time call", e);
        if (e >= 19)
            latch.countDown();
        Instant start = Instant.now();
        Integer value = CustomSuppliers.memoize(supplier).get();
        log.info("cost {} ms value: {}", Duration.between(start, Instant.now()).toNanos() / 1000_000.0, value);
    }
}
