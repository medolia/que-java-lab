package com.medolia.demo.refactor;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

/**
 * @author lbli@trip.com
 * @date 2021/9/10
 */
public class PECSDesign<E> {

    Queue<E> queue = new ArrayDeque<>();

    /**
     * Producer extends
     * 方法从参数 src 中拿元素置入内部集合中，src 为生产者；
     */
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    /**
     * Consumer super
     * 方法将内部集合的元素全部拿出置入参数 dst 中，dst 为消费者；
     */
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    private void push(E e) {
        queue.add(e);
    }

    private E pop() {
        return queue.poll();
    }

    private boolean isEmpty() {
        return queue.isEmpty();
    }
}
