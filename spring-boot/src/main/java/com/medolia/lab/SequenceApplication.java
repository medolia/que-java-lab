package com.medolia.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lilongbin
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.lab.*"})
public class SequenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SequenceApplication.class, args);
    }

}
