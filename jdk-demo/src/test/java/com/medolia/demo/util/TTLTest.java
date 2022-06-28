package com.medolia.demo.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lbli
 */
public class TTLTest {

    private String prefix;

    @BeforeEach
    void setUp() {
        prefix = "公众号：码猿技术专栏—";
    }

    @Test
    void testITL() throws Exception {
        //单一线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //InheritableThreadLocal存储
        InheritableThreadLocal<String> username = new InheritableThreadLocal<>();
        for (int i = 0; i < 10; i++) {
            username.set(prefix + i);
            Thread.sleep(300);
            CompletableFuture.runAsync(
                    () -> System.out.println(username.get()),
                    executorService);
        }
    }

    @Test
    public void testTTL() throws Exception {
        //单一线程池
        ExecutorService executorService = null;
        // executorService = Executors.newSingleThreadExecutor();
        executorService = Executors.newFixedThreadPool(8);
        //需要使用TtlExecutors对线程池包装一下
        executorService = TtlExecutors.getTtlExecutorService(executorService);
        //TransmittableThreadLocal创建
        TransmittableThreadLocal<String> username = new TransmittableThreadLocal<>();
        for (int i = 0; i < 10; i++) {
            username.set(prefix + i);
            Thread.sleep(300);
            CompletableFuture.runAsync(
                    () -> System.out.println(username.get()),
                    executorService);
        }
    }
}
