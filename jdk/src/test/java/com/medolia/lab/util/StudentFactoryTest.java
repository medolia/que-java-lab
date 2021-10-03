package com.medolia.lab.util;


import com.medolia.lab.util.function.CustomSuppliers;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli
 * @date 2021/10/3
 */
public class StudentFactoryTest {

    @Test
    void supplier() {
        Supplier<StudentFactory.Student> studentSupplier = () -> {
            long id = ThreadLocalRandom.current().nextLong();
            String name = RandomStringUtils.randomAlphabetic(3, 7);
            return new StudentFactory.Student(id, name);
        };

        Supplier<StudentFactory.Student> memoize = CustomSuppliers.memoize(studentSupplier);
        System.out.println(memoize);
        memoize.get();
        Supplier<StudentFactory.Student> memoize1 = CustomSuppliers.memoize(studentSupplier);
        System.out.println(memoize1);
    }
}
