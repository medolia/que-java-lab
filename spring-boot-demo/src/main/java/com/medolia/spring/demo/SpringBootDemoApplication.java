package com.medolia.spring.demo;

import com.medolia.spring.demo.validator.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lbli
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.spring.demo"})
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);

    }

}
