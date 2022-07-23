package com.medolia.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lbli
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.spring.demo"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);

    }

}
