package com.medolia.demo.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author lbli@trip.com
 * @date 2021/9/8
 */
public class Concurrency {
    @Test
    void listenableFuture() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(6));
        ListenableFuture<String> explosion = service.submit(
                () -> "pushBigRedButton()");

        Futures.addCallback(
                explosion,
                new FutureCallback<String>() {
                    @Override
                    public void onSuccess(@Nullable String result) {
                        System.out.println("success");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("failure");
                    }
                },
                service
        );


    }
}
