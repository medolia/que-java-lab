package com.medolia.demo.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lbli@trip.com
 * @date 2021/8/13
 */
class GuavaTest {

    @Test
    @SuppressWarnings("all")
    void bloomFilter1() {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(),
                5000, 0.00000001);

        IntStream.range(0, 100_000).forEach(filter::put);

        assertThat(filter.mightContain(1)).isTrue();
        assertThat(filter.mightContain(2)).isTrue();
        assertThat(filter.mightContain(3)).isTrue();
        // actually not
        assertThat(filter.mightContain(1_000_000)).isTrue();
    }

    @Test
    @SuppressWarnings("all")
    void bloomFilter() {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(),
                500, 0.01);
        filter.put(1);
        filter.put(2);
        filter.put(3);

        assertThat(filter.mightContain(1)).isTrue();
        assertThat(filter.mightContain(2)).isTrue();
        assertThat(filter.mightContain(3)).isTrue();

        assertThat(filter.mightContain(100)).isFalse();
    }

    @Test
    void guavaString() {
        Joiner joiner = Joiner.on(";").skipNulls();
        String ret = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(ret);
        String ret1 = joiner.join(Arrays.asList(1, 5, 7));
        System.out.println(ret1);
    }

    @Test
    void guavaImmutableSet() {
        final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple");
    }

    @Test
    void mutableCollection() {
        final Set<String> names = Sets.newHashSet();
        final List<String> list = Lists.newLinkedList();

    }

    @Test
    void threadFactory() {
        ExecutorService executor = new ThreadPoolExecutor(
                8,
                8,
                0,
                TimeUnit.SECONDS,
                Queues.newArrayBlockingQueue(100),
                creatThreadFactory("medolia-demo"),
                new ThreadPoolExecutor.DiscardPolicy());

        AtomicInteger counter = new AtomicInteger(0);
        while (counter.getAndIncrement() <= 5) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }

    ThreadFactory creatThreadFactory(String namePrefix) {
        return new ThreadFactoryBuilder()
                .setNameFormat((StringUtils.hasText(namePrefix) ? namePrefix : "default") + "-%d")
                .build();
    }
}