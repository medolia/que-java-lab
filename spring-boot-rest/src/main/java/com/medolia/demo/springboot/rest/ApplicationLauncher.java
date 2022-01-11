package com.medolia.demo.springboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.demo.springboot.rest"})
public class ApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);

    }
}
