package com.medolia.demo.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author lbli@trip.com
 * @date 2021/8/16
 */
public class CasualTest {
    @Test
    void testMark() {
        /*long flag = 137472577537L;
        String binaryString = Long.toBinaryString(flag);
        for (int i = 0; i < binaryString.toCharArray().length; i++) {
            System.out.printf("%s: %s\n", i, binaryString.charAt(i));
        }*/

        System.out.println(100032087 % 20);
    }

    @Test
    void testObj() {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("1", Lists.newArrayList(1));
        map.put("2", Lists.newArrayList(2));

        List<Integer> list = map.get("1");
        list.add(158);
        System.out.println();

    }

    @Test
    void cpuNum() {
        int num = Runtime.getRuntime().availableProcessors();
        System.out.println(num);
    }

    @Test
    void random() {
        AtomicInteger low = new AtomicInteger();
        IntStream.range(0, 1000000).forEach(i -> {
            if (ThreadLocalRandom.current().nextBoolean()) {
                low.incrementAndGet();
            }
        });
        System.out.println(low.get());
    }

    /**
     * 迭代器指针每次调用 next() 都会前移一次；
     */
    @Test
    void iterator() {
        EnumSet<Face> faces = EnumSet.allOf(Face.class);

        for (Iterator<Face> i = faces.iterator(); i.hasNext(); ) {
            for (Iterator<Face> j = faces.iterator(); j.hasNext(); ) {
                System.out.println(i.next() + " " + j.next());
            }
        }
    }

    @Test
    void collectionRemove() {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        IntStream.range(-3, 3).forEach(e -> {
            // 均为 (T element)
            set.add(e);
            list.add(e);
        });

        IntStream.range(0, 3).forEach(i -> {
            // 移除泛型 (T element)
            set.remove(i);
            // 移除指定位置元素 (int idx)
            list.remove(i);
        });

        System.out.println(set + "" + list);
    }

    @Test
    void timestamp() {
        Date date = new Date(1631005259988L);
        System.out.println();
    }

    @Test
    void optional() {
        int cycleCount = 10;

        while (cycleCount-- > 0) {
            System.out.printf("---cycle：%s---\n", cycleCount);
            String msg = ThreadLocalRandom.current().nextBoolean() ? "msg" : null;
            Optional<String> str = Optional.ofNullable(msg);
            str.ifPresent(System.out::println);
        }
    }

    @Test
    void getOptionList() {
        List<Option> result = of(new Tuple("OutlineImage", "T"),
                new Tuple("EnableSuperMemberPackage", "T"),
                new Tuple("EnableLightPackage", "T"));
        System.out.println();

    }

    List<Option> of(Tuple... options) {
        ArrayList<Option> result = Lists.newArrayList();
        try {
            for (Tuple each : options) {
                result.add(new Option((String) each.first, (String) each.second));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    enum Face {ONE, TWO, THREE, FOUR, FIVE, SIX}

    private class POJO {
        String name;

        public POJO(String name) {
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    private class Option {
        String key;
        String value;
    }

    @Data
    @AllArgsConstructor
    private class Tuple<K, V> {
        K first;
        V second;
    }
}
