package com.medolia.demo.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lb@li@trip.com
 * @date 2021/7/14
 */
public class ThreadLoaclTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> holder = new ThreadLocal<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger count = new AtomicInteger(0);

        while (count.addAndGet(1) <= 100) {
            executor.submit(() -> {
                System.out.printf("count: %s\n", count.get());
                Integer num = ThreadLocalRandom.current().nextInt();
                holder.set(num);
                System.out.printf("number stored in current thread: %s\n", num);
            });
        }

        executor.shutdown();

        holder.set(100);
        Integer num = holder.get();
        System.out.println(num);
    }
}
