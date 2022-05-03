package com.medolia.spring.demo.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli
 * @date 2022/5/1
 */
@SpringBootTest
class MainConsumerTest {

    @Autowired
    private MainConsumer mainConsumer;

    @Test
    void proceedMessage() {
        mainConsumer.proceedMessage("90% discount for diablo 3");
    }
}