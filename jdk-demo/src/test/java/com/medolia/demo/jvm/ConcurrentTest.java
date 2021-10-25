package com.medolia.demo.jvm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author lbli@trip.com
 * @date 2021/9/10
 */
public class ConcurrentTest {

    ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newFixedThreadPool(8);
    }

    @Test
    void testHandle() throws ExecutionException, InterruptedException {
        String name = null;

        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        assertEquals("Hello, Stranger!", completableFuture.get());
    }

    @Test
    void testAllOf() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get(200, TimeUnit.MILLISECONDS);

        assertTrue(future1.isDone());
        assertTrue(future2.isDone());
        assertTrue(future3.isDone());

        String result = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join).collect(Collectors.joining(" "));
        assertEquals(result, "Hello Beautiful World");
    }

    @Test
    void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
        assertEquals(completableFuture.get(), "Hello World");
    }

    /**
     * then[verb](supplier): 调用线程执行
     * then[verb]Async(supplier): 调用公共的 ForkJoinPool.commonPool() 执行
     * then[verb]Async(supplier, executorService): 调用给定的线程池执行
     */
    @Test
    void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = calculateAsync().thenApply(s -> s + " World");
        CompletableFuture<String> completableFuture1 = calculateAsync().thenApplyAsync(s -> s + " World");
        CompletableFuture<String> completableFuture2 = calculateAsync().thenApplyAsync(s -> s + " World", executorService);
        assertEquals(completableFuture.get(), "Hello World");
        assertEquals(completableFuture1.get(), "Hello World");
        assertEquals(completableFuture2.get(), "Hello World");
    }

    @Test
    void testAsync() throws Exception {
        Future<String> future = calculateAsync();

        String result = future.get(600, TimeUnit.MILLISECONDS);
        assertEquals(result, "Hello");
    }

    CompletableFuture<String> calculateAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }, executorService);
    }

    @Test
    void stopThread() throws InterruptedException {
        new Thread(() -> {
            boolean stopRequested = false;
            int i = 0;
            while (!stopRequested) {
                stopRequested = ThreadLocalRandom.current().nextBoolean();
                ++i;
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
    }
}
