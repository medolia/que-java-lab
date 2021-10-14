package com.medolia.demo.dubbo.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lbli@trip.com
 * @date 2021/9/16
 */
public class AnnotationConsumer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        EchoConsumer consumer = context.getBean(EchoConsumer.class);
        String hello = consumer.echo("Hello world!");
        System.out.println("result: " + hello);
    }
}
