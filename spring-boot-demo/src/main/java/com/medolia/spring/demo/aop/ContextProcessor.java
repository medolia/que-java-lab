package com.medolia.spring.demo.aop;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lbli
 */
public abstract class ContextProcessor {

    private static final BlockingQueue<LogRecordContext> QUEUE = new ArrayBlockingQueue<>(500);

    protected void store(LogRecordContext context) {
        QUEUE.add(context);
    }

    protected abstract void consume(LogRecordContext context);

}
