package com.medolia.lab.util;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lbli
 * @date 2021/10/3
 */
public class StudentFactory {
    public static Student generate() {
        long id = ThreadLocalRandom.current().nextLong();
        String name = RandomStringUtils.randomAlphabetic(3, 7);
        return new Student(id, name);
    }

    public static Supplier<Student> studentSupplier() {

        Supplier<Student> studentSupplier = () -> {
            long id = ThreadLocalRandom.current().nextLong();
            String name = RandomStringUtils.randomAlphabetic(3, 7);
            return new Student(id, name);
        };
        return Suppliers.memoize(studentSupplier);
    }

    @Data
    @AllArgsConstructor
    static class Student {
        private long id;
        private String name;
    }
}
