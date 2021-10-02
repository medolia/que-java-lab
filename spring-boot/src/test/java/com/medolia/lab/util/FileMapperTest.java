package com.medolia.lab.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli
 * @date 2021/10/2
 */
@Slf4j
class FileMapperTest {

    @Test
    void getValue() {
        getKey("暗号");
        getKey("name");
        getKey("待办事项");
    }

    private void getKey(String key) {
        Instant start = Instant.now();
        String value = FileMapper.getValue(key);
        log.info("cost {} ms value: {}", Duration.between(start, Instant.now()).toMillis(), value);
    }
}
