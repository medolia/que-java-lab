package com.medolia.demo.springcloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@SpringBootApplication(scanBasePackages = {"com.medolia.demo.springcloud.nacos"})
@EnableDiscoveryClient
public class NacosDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryApplication.class, args);
    }

}
