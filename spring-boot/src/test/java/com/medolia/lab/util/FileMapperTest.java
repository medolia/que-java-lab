package com.medolia.lab.util;

import com.google.common.collect.Iterators;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli
 * @date 2021/10/2
 */
@Slf4j
class FileMapperTest {

    /**
     * 多线程下仍然生效
     */
    @Test
    void getValueMultiThread() throws Exception {
        String[] keyArr = new  String[]{"暗号","name","待办事项","key","key1"};

        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(8);
        Stream.of(keyArr).forEach(e -> {
            service.submit(() -> {
                getKey(e);
                if (e.equals(Iterators.getLast(Iterators.forArray(keyArr)))) {
                    latch.countDown();
                }
            });
        });

        latch.await();
    }

    @Test
    void getValueSingleThread() {
        String[] keyArr = new  String[]{"暗号","name","待办事项","key","key1"};

        Stream.of(keyArr).forEach(this::getKey);

    }

    private void getKey(String key) {
        Instant start = Instant.now();
        String value = FileMapper.getValue(key);
        log.info("cost {} ms value: {}", Duration.between(start, Instant.now()).toMillis(), value);
    }
}
