package com.medolia.demo.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@Slf4j
public class PrintServiceImpl implements PrintService{
    @Override
    public void printInfo() {
        int retryTime = 10;
        while (retryTime-- > 0) {
            log.info("嗯？");
        }

        log.info("medolia, this info shown by class PrintServiceImpl");
    }
}
