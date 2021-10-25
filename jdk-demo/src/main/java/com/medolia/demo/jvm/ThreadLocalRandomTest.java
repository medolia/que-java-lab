package com.medolia.demo.jvm;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lb@li@trip.com
 * @date 2021/7/9
 */
public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        LinkedList<Integer> list = new LinkedList<>();
        int limit = 15;
        while (list.size() < limit) {
            list.addLast(random.nextInt());
        }

        for (int num : list) {
            System.out.printf("%s ", num);
        }
    }
}
