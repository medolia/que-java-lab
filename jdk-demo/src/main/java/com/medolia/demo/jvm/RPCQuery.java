package com.medolia.demo.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 已知一个业务查询操作涉及3 个RPC 服务调用: query1, query2, query3, 其中
 * query1 耗时约1 秒， query2 耗时约0.5 秒，query3 耗时约0.6 秒，且query3
 * 查询条件依赖query2 的查询结果，
 * 请编写代码，使该业务查询总体耗时最小。
 *
 * @author lb@li@trip.com
 * @date 2021/7/20
 */
public class RPCQuery {
    public static void query1() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("耗时 1 秒");
    }

    static AtomicInteger query2ctl = new AtomicInteger(0);
    static AtomicInteger query3ctl = new AtomicInteger(0);
    public static void query2() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("耗时 0.5 秒");
        query2ctl.getAndIncrement();
    }

    public static void query3() throws InterruptedException {
        Thread.sleep(600);
        for (;query2ctl.get() <= 0;) {}
        System.out.println("耗时 0.6 秒");
        query3ctl.getAndIncrement();
    }

    public void execute() {
        long start = System.currentTimeMillis();
        RPCQuery query = new RPCQuery();
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(() -> {
            try {
                query1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            try {
                query2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            try {
                query3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
        while (!service.isTerminated()) {}
        long end = System.currentTimeMillis();
        System.out.printf("总耗时: %s\n", (end - start) / 1000.0);
    }

    public static void main(String[] args) {
        new RPCQuery().execute();
    }
}
