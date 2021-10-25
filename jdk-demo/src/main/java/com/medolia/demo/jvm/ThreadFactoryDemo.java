package com.medolia.demo.jvm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */
public class ThreadFactoryDemo {
    private static ThreadPoolExecutor executor;

    public static void main(String[] args) {
        int cycleCount = 15;
        while (cycleCount-- > 0) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println("medolia testing...");
            });
        }
        executor.shutdown();
    }

    static {
        executor = new ThreadPoolExecutor(10,10,20L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(50),  new DemoThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
    }

    static class DemoThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public DemoThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "demo-pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(true);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
