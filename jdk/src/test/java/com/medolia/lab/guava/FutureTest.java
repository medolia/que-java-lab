package com.medolia.lab.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author lbli
 * @date 2021/10/2
 */
public class FutureTest {

    private static final ListeningExecutorService executor =
            MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(8));
    private static final Service service = new Service();
    private static final CountDownLatch latch = new CountDownLatch(1);

    /**
     * 有关多线程测试，使用 CountDownLatch 防止异步结果出来前结束
     */
    @Test
    void demo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        ListenableFuture<Integer> asyncTask = executor.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                return 5;
            } catch (InterruptedException e) {
                return -1;
            }
        });
        asyncTask.addListener(() -> {
            try {
                Integer integer = asyncTask.get(1500L, TimeUnit.MILLISECONDS);
                System.out.printf("result: %s%n", integer);
                latch.countDown();
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                //
            }
        }, executor);

        latch.await();
    }

    /**
     * listener 链
     * 一般建议 任务线程池 和 listener 线程池分开防阻塞
     */
    @Test
    void futureChain() throws Exception {
        ListenableFuture<String> usernameTask = service.generateUsername();
        usernameTask.addListener(() -> {
            try {
                final String userName = usernameTask.get();
                System.out.println("user name: " + userName);
                generatePassword(userName);
            } catch (Exception e) {
                //
            }
        }, executor);

        latch.await();
    }

    private void generatePassword(String userName) {
        ListenableFuture<String> generatePasswordTask = service.generatePassword(userName);
        generatePasswordTask.addListener(() -> {
            try {
                final String password = generatePasswordTask.get();
                System.out.println("password generated: " + password);
                showSuccessMsg();
            } catch (Exception e) {
                //
            }
        }, executor);
    }

    private void showSuccessMsg() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("finish");
            latch.countDown();
        } catch (InterruptedException e) {
            //
        }
    }

    static class Service {

        public ListenableFuture<String> generateUsername() {
            return executor.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    return RandomStringUtils.randomAlphanumeric(4,5);
                } catch (InterruptedException e) {
                    return "";
                }
            });
        }


        public ListenableFuture<String> generatePassword(String name) {
            return executor.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    return RandomStringUtils.randomAlphanumeric(name.length() + 20);
                } catch (InterruptedException e) {
                    return "";
                }
            });
        }
    }
}
