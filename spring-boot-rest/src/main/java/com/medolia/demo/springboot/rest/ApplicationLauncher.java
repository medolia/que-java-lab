package com.medolia.demo.springboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lbli@trip.com
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.demo.springboot.rest"})
public class ApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);

    }
}
