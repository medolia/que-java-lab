package com.medolia.spring.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lbli@trip.com
 * @date 2021/8/26
 */
@Component
@Slf4j
public class MainConsumer {

    @NeedAroundLog
    public void proceedMessage(String message) {
        log.info("start to proceed message: {}", message);
    }

}
