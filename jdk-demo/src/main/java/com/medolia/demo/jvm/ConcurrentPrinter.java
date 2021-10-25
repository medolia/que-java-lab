package com.medolia.demo.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * （JAVA）有3 个独立的线程，一个只会输出A，一个只会输出L，一个只会输出I。
 * 在三个线程同时启动的情况下，请用合理的方式让他们按顺序打印ALIALI。
 * 三个线程开始正常输出后，主线程若检测到用户任意的输入则停止三个打印线程的工
 * 作，整体退出。
 *
 * @author lb@li@trip.com
 * @date 2021/7/20
 */
public class ConcurrentPrinter {

    public static void main(String[] args) {
        ConcurrentPrinter printer = new ConcurrentPrinter();
        printer.print();
    }

    AtomicInteger cursor = new AtomicInteger(0);
    ReentrantLock lock = new ReentrantLock();
    Condition ready = lock.newCondition();
    String[] arr = new String[]{"A", "L", "I"};
    private final int CHAR_NUM_LIMIT = 4;


    public void print() {
        ExecutorService service = Executors.newFixedThreadPool(3);

        service.submit(() -> {
            for (;;) {
                if (cursor.get() >= CHAR_NUM_LIMIT) break;
                lock.lock();
                try {
                    while (cursor.get() % 3 != 0) {
                        ready.await();
                    }
                    System.out.print(arr[0]);
                    cursor.getAndIncrement();
                    ready.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        service.submit(() -> {
            for (; ; ) {
                if (cursor.get() >= CHAR_NUM_LIMIT) break;
                lock.lock();
                try {
                    while (cursor.get() % 3 != 1) {
                        ready.await();
                    }
                    System.out.print(arr[1]);
                    cursor.getAndIncrement();
                    ready.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        service.submit(() -> {
            for (; ; ) {
                if (cursor.get() >= CHAR_NUM_LIMIT) break;
                lock.lock();
                try {
                    while (cursor.get() % 3 != 2) {
                        ready.await();
                    }
                    System.out.print(arr[2]);
                    cursor.getAndIncrement();
                    ready.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        service.shutdown();
    }
}
