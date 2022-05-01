package com.medolia.spring.data.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.spring.data.custom"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}

