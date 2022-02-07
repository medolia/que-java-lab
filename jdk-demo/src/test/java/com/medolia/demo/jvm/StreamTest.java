package com.medolia.demo.jvm;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class StreamTest {

    @Test
    void testSequentialAndParallelStream() {

        System.out.println("--- seq ---");
        List<Integer> listOfNumbers1 = Arrays.asList(1, 2, 3, 4);
        listOfNumbers1.stream().forEach(number ->
                System.out.println(number + " " + Thread.currentThread().getName())
        );

        System.out.println("---multi---");
        List<Integer> listOfNumbers2 = Arrays.asList(1, 2, 3, 4);
        listOfNumbers2.parallelStream().forEach(number ->
                System.out.println(number + " " + Thread.currentThread().getName())
        );
    }

    @Test
    void testStream_givenInfiniteStream_whenLimit_thenReturnLimitElements() {
        // given
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 2);

        // when
        List<Integer> collect = infiniteStream
                .limit(10)
                .collect(Collectors.toList());

        // then
        assertEquals(collect, Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18));
    }

    @Test
    void tryAdvanceDemo() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        Spliterator<Integer> spliterator = list.stream().spliterator();
        final AtomicInteger round = new AtomicInteger(1);
        final AtomicInteger loop = new AtomicInteger(1);
        while (spliterator.tryAdvance(num -> System.out.printf("第%d轮回调Action,值:%d\n", round.getAndIncrement(), num))) {
            System.out.printf("第%d轮循环\n", loop.getAndIncrement());
        }
    }

    @Test
    void testForEachRemaining() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        Spliterator<Integer> spliterator = list.stream().spliterator();
        final AtomicInteger round = new AtomicInteger(1);
        spliterator.forEachRemaining(num -> System.out.printf("[第一次遍历forEachRemaining]第%d轮回调Action,值:%d\n", round.getAndIncrement(), num));
        round.set(1);
        spliterator.forEachRemaining(num -> System.out.printf("[第二次遍历forEachRemaining]第%d轮回调Action,值:%d\n", round.getAndIncrement(), num));
    }

    /**
     * ArrayList Spliterator.trySplit() 实现：对半分，类似于归并排序时的递归
     */
    @Test
    void testTrySplit() {
        List<Integer> arr1 = Lists.newArrayList();
        // 1 - 19
        IntStream.range(1,20).forEach(arr1::add);
        Spliterator<Integer> spliterator = arr1.spliterator();
        // 分隔出去的: 1-9
        Spliterator<Integer> sp1 = spliterator.trySplit();
        // 剩余（保留）的 10-19
        Spliterator<Integer> sp2 = spliterator;
        System.out.println("---sp1---");
        sp1.forEachRemaining(System.out::println);
        System.out.println("---sp2---");
        sp2.forEachRemaining(System.out::println);
    }
}
