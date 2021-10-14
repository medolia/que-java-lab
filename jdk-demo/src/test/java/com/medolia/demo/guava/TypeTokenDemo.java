package com.medolia.demo.guava;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli@trip.com
 * @date 2021/9/10
 */
@SuppressWarnings("all")
public class TypeTokenDemo {
    static <K, V> TypeToken<Map<K, V>> mapToken(TypeToken<K> keyToken, TypeToken<V> valueToken) {
        return new TypeToken<Map<K, V>>() {}
                .where(new TypeParameter<K>() {}, keyToken)
                .where(new TypeParameter<V>() {}, valueToken);
    }

    @Test
    void demo() {
        TypeToken<Map<String, BigInteger>> mapToken = mapToken(
                TypeToken.of(String.class),
                TypeToken.of(BigInteger.class));
        TypeToken<Map<Integer, Queue<String>>> complexToken = mapToken(
                TypeToken.of(Integer.class),
                new TypeToken<Queue<String>>() {});

    }

    @Test
    void demo1() throws Exception {
        TypeToken token = new TestAbstractClass<Integer, ComplexType>() {}.valueType;
        Class<ComplexType> rawType = token.getRawType();
        ComplexType value = rawType.newInstance();
        assertNotNull(value);
        System.out.println("");
    }

    static abstract class TestAbstractClass<K, V> {
        TypeToken<K> keyType = new TypeToken<K>(getClass()) {};
        TypeToken<V> valueType = new TypeToken<V>(getClass()) {};
        K key;
        V value;
    }

    static class TestSubClass extends TestAbstractClass<Integer, String> {
    }


    static interface testInterface<K, V> {
        void handle(K key, V value);
    }

    static class ComplexType {
        String str;
        List<String> stringList;
    }
}
