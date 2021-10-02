package com.medolia.lab.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.math.BigInteger;

/**
 * @author lbli
 * @date 2021/10/2
 */
public class Factorial {
    private static final LoadingCache<Integer, BigInteger> memo = CacheBuilder.newBuilder()
            .build(CacheLoader.from(Factorial::getFactorial));

    public static BigInteger getFactorial(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(n).multiply(getFactorial(n - 1));
        }
    }

    public static BigInteger getFactorialWithCache(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(n).multiply(memo.getUnchecked(n - 1));
        }
    }
}
