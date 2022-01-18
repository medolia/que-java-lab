package com.medolia.demo.jvm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <ul>
 *     <li> thenX: 使用上一个任务相同的线程或 main 线程执行下一个任务 </li>
 *     <li> thenXAsync: 是否传入线程池参 ? 使用指定线程池执行下个任务 : 使用 ForkJoinPool.commonPool() 执行下个任务 </li>
 * </ul>
 *
 * <ul>
 *     <li>thenApply: 接受先前结果，再生成下个结果</li>
 *     <li>thenAccept：接受先前结果，不生成下个结果</li>
 *     <li>thenRun：不接受先前结果，不生成下个结果</li>
 *     <li>thenCompose: 接受先前结果，生成下个结果，flat平铺</li>
 * </ul>
 *
 * <ul>
 *     <li>exceptionally: 当异常产生时执行, 逻辑入参里只有异常</li>
 *     <li>handle: 不确定异常是否产生均会执行，逻辑入参里有上一个任务的返回结果和异常</li>
 * </ul>
 *
 * @author lbli@trip.com
 * @date 2021/9/10
 */
public class CompletableFutureTest {

    /**
     * thenApply 与 thenCompose 类似 Stream/Optional 里的 map 与 flatMap
     */
    @Test
    void compare_thenApply_thenCompose() throws Exception {
        String userId = "Q12983891234";
        CompletableFuture<CompletableFuture<String>> creditId1 =
                getUsersDetail(userId).thenApplyAsync(this::getCreditRating, executorService);
        CompletableFuture<String> creditId2 =
                getUsersDetail(userId).thenComposeAsync(this::getCreditRating, executorService);
        while (((ThreadPoolExecutor) executorService).getQueue().size() > 0) {
        }

        assertEquals("user_" + userId + "_credit_239848287129", creditId1.get().get());
        assertEquals("user_" + userId + "_credit_239848287129", creditId2.get());
    }

    CompletableFuture<String> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> "user_" + userId);
    }

    CompletableFuture<String> getCreditRating(String fullUserId) {
        return CompletableFuture.supplyAsync(() -> fullUserId + "_credit_239848287129");
    }

    @Test
    void testExceptionally() throws Exception {
        int age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());
        assertEquals(maturityFuture.get(), "Unknown!");
    }

    @Test
    void testHandle() throws ExecutionException, InterruptedException {
        String name = null;

        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
                    if (Objects.isNull(name)) {
                        throw new RuntimeException("Computation error!");
                    }
                    return "Hello, " + name;
                })
                .handle((res, ex) -> res != null ? res : "Hello, Stranger!");

        assertEquals("Hello, Stranger!", completableFuture.get());
    }

    /**
     * 两个任务（第二个任务放在第一个入参）完成时执行（逻辑在第二个入参）
     */
    @Test
    void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2, executorService);
        assertEquals(completableFuture.get(), "Hello World");
    }

    /**
     * 所有任务完成时
     */
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

    /**
     * 任意一个任务完成时
     */
    @Test
    void testAnyOf() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);
        assertEquals(anyOfFuture.get(), "Result of Future 2");
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

    ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newFixedThreadPool(8);
    }
}
