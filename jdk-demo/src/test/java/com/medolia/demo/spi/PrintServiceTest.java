package com.medolia.demo.spi;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
class PrintServiceTest {
    @Test
    void spiDemo() {
        ServiceLoader<PrintService> services = ServiceLoader.load(PrintService.class);
        services.forEach(PrintService::printInfo);
    }
}