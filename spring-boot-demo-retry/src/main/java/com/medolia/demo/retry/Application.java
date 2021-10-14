package com.medolia.demo.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.demo.retry"})
@EnableRetry
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
