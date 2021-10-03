package com.medolia.lab.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli
 * @date 2021/10/2
 */
@Slf4j
class FileMapperTest {

    @Test
    void getValue() {
        String[] keyArr = new  String[]{"暗号","name","待办事项","key","key1"};
        Stream.of(keyArr).forEach(this::getKey);
    }

    private void getKey(String key) {
        Instant start = Instant.now();
        String value = FileMapper.getValue(key);
        log.info("cost {} ms value: {}", Duration.between(start, Instant.now()).toMillis(), value);
    }
}
