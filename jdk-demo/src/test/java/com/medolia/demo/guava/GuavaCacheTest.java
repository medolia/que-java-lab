package com.medolia.demo.guava;


import com.google.common.base.Suppliers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author lbli
 * @date 2021/10/2
 */
@Slf4j
public class GuavaCacheTest {

    @Test
    public void factorial() {
        int n = 1000;
        // 耗时 ｜ 结果
        ImmutablePair<Long, BigInteger> rawRes = calculate(n, false);
        ImmutablePair<Long, BigInteger> cacheRes = calculate(n, true);
        assertTrue(rawRes.left > cacheRes.left * 3);
        assertEquals(rawRes.right, cacheRes.right);

    }

    private ImmutablePair<Long, BigInteger> calculate(int n, boolean useCache) {
        Instant start = Instant.now();
        BigInteger result = useCache ? Factorial.getFactorialWithCache(n) : Factorial.getFactorial(n);
        long duration = Duration.between(start, Instant.now()).toMillis();
        log.info("computation finish, cache: {}, cost {} ms, result: {}", useCache, duration, result);
        return ImmutablePair.of(duration, result);
    }

    /**
     * 首次调用需要 2 s，然后返回值将存入内存，接下来的调用都取缓存值
     */
    @Test
    public void givenMemoizedSupplier_whenGet_thenSubsequentGetsAreFast() {
        Supplier<BigInteger> memoizedSupplier;
        memoizedSupplier = Suppliers.memoize(CostlySupplier::generateBigNumber);

        BigInteger expectedValue = new BigInteger("12345");
        assertSupplierGetExecutionResultAndDuration(
                memoizedSupplier, expectedValue, 2000D);
        assertSupplierGetExecutionResultAndDuration(
                memoizedSupplier, expectedValue, 0D);
        assertSupplierGetExecutionResultAndDuration(
                memoizedSupplier, expectedValue, 0D);
    }

    private <T> void assertSupplierGetExecutionResultAndDuration(
            Supplier<T> supplier, T expectedValue, double expectedDurationInMs) {
        Instant start = Instant.now();
        T value = supplier.get();
        long durationInMs = Duration.between(start, Instant.now()).toMillis();
        double marginOfErrorInMs = 100D;

        assertThat(value, is(equalTo(expectedValue)));
        assertThat(
                (double) durationInMs,
                is(closeTo(expectedDurationInMs, marginOfErrorInMs)));
        log.info("var1:{}, var2:{}, var3:{}", (double) durationInMs, expectedDurationInMs, marginOfErrorInMs);
    }
}
