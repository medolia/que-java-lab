package com.medolia.spring.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class RedissonDemo {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:16379");
        RedissonClient redisson = Redisson.create(config);

        RBlockingQueue<String> blockingQueue = redisson.getBlockingQueue("dest_queue1");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(blockingQueue);

        Executors.newFixedThreadPool(8).submit(() -> {
            while (true) {
                try {
                    //阻塞队列有数据就返回，否则wait
                    System.err.println(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 1; i <= 5; i++) {
            // 向阻塞队列放入数据
            delayedQueue.offer("fffffffff" + i, 13, TimeUnit.SECONDS);
        }
    }

}
