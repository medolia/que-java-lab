package com.medolia.demo.jdk.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lb@li@trip.com
 * @date 2021/8/4
 */
public class OptimisticRefTest {
    static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }
    }

    static AtomicReference<Student> reference = new AtomicReference<>();

    static Student searchOne() {
        Student s = reference.get();
        if (s != null) {
            System.out.println("reference is not null");
            return s;
        }
        s = new Student("melodia");
        if (reference.compareAndSet(null, s)) {
            System.out.println("CAS success");
            return s;
        }
        System.out.println("CAS fail, maybe caused by concurrent problems");
        return reference.get();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(10);
        int i = 0;
        while (i++ < 10) {
            service.submit(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " call searchOne()");
                Student s = OptimisticRefTest.searchOne();
            });
        }
        service.shutdown();
        /*while (!service.isTerminated()) {}

        log.info("----------------------------------------------------");

        Thread.sleep(2000);
        ExecutorService service2 = Executors.newFixedThreadPool(10);
        int j = 0;
        while (j++ < 10) {
            service.submit(() -> {
                log.info(Thread.currentThread().getName() + " call searchOne()");
                Student s = OptimisticRefTest.searchOne();
            });
        }

        service2.shutdown();*/
    }
}


