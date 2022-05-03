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

        log.info("medolia, this info shown by class PrintServiceImpl");
    }
}
