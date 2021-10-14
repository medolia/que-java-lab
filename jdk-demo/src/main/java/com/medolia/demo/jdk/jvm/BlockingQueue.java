package com.medolia.demo.jdk.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lb@li@trip.com
 * @date 2021/7/9 11:37
 */
public class BlockingQueue {

    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    LinkedList<String> list = new LinkedList<>();
    final int SIZE_LIMIT = 10;

    /**
     * @implSpec
     * This implementation returns {@code this.size() == 0}
     *
     * @return true if this collection is empty
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void enqueue(String msg) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (list.size() >= SIZE_LIMIT) {
                notFull.await();
            }
            System.out.printf("msg %s enqueued%n", msg);
            list.addFirst(msg);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String dequeue() throws InterruptedException {
        String ret;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (list.size() <= 0) {
                notEmpty.await();
            }
            ret = list.removeLast();
            System.out.printf("msg %s dequeued%n", ret);
            notFull.signal();
        } finally {
            lock.unlock();
        }
        return ret;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new BlockingQueue();
        queue.enqueue("hello");
        queue.enqueue("medolia");
        queue.dequeue();
        queue.dequeue();
    }
}
