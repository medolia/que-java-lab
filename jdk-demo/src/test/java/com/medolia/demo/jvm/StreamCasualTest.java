package com.medolia.demo.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author lbli@trip.com
 * @date 2021/8/13
 */
@Slf4j
class StreamCasualTest {

    static List<String> strings;
    static List<Person> personList;

    static {
        strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
    }

    @Test
    void count() {
        long count = strings.stream().filter(String::isEmpty).count();
        log.info("空字符串数量为：{}", count);
    }

    @Test
    void collect() {
        String mergeRes = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining("%$"));
        log.info("result: {}", mergeRes);
    }

    @Test
    void max() {
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        log.info("person who earns the most: {}", max.orElse(null));
    }

    /**
     * toMap(keyMapper, valueMapper, mergeFunction)
     * mergeFunction – a merge function, used to resolve collisions between values associated with the same key.
     * (v1, v2) -> v2 means always choose the newer in every collision
     */
    @Test
    void toMapMerge() {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version1", 12.10));
        pairArrayList.add(new Pair<>("version2", 12.19));
        pairArrayList.add(new Pair<>("version1", 6.28));
        //pairArrayList.add(new Pair<>("version4", null));
        // 生成的map集合中只有一个键值对：{version=6.28}
        Map<String, Double> map = pairArrayList.stream().collect(
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
        map.entrySet().forEach(System.out::println);
    }

    /**
     * toMap key 或 value 为 null 时都会抛出 NPE
     */
    @Test
    void toMapNoMerge() {
        String[] departments = new String[] {"iERP", "iERP", "EIBU"};
        // 抛出IllegalStateException异常，因为存在相同 key 且没有传 mergeFunction
        assertThrows(Exception.class, () -> Arrays.stream(departments)
                .collect(Collectors.toMap(String::hashCode, str -> str)));
    }

    @Test
    void toMapMergeNull() {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 12.10));
        pairArrayList.add(new Pair<>("version", null));
        // 抛出 NPE
        assertThrows(NullPointerException.class, () -> pairArrayList.stream().collect(
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2)));
    }

    private static class Pair<K, V> {
        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Data
    @AllArgsConstructor
    private static class Person {
        String name;
        Integer salary;
        Integer age;
        String gender;
        String area;
    }
}