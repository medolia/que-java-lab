package com.medolia.lab.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author lbli
 * @date 2021/9/14
 */
@Slf4j
class CasualTest {

    @Test
    void testDecimal() {
        String result = new BigDecimal("100")
                .subtract(new BigDecimal("15.6900002350"))
                .divide(BigDecimal.TEN, 1, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();

        System.out.println(result);
    }

    @Test
    void random() {
        List<String> randomStr = Lists.newArrayList();
        IntStream.range(0, 20).forEach(e -> randomStr.add(RandomStringUtils.randomAlphanumeric(20)));
        randomStr.forEach(System.out::println);
    }

    @Test
    void testTuple() {
        Date now = new Date();
        ImmutableTriple<Date, Date, Date> triple = ImmutableTriple.of(
                DateUtils.addDays(now, -1),
                now,
                DateUtils.addDays(now, 1));
        String pattern = "yyyy-MM-dd-HHmmss";
        log.info("\nyesterday: {}\n now: {}\n tomorrow:{}",
                DateFormatUtils.format(triple.left, pattern),
                DateFormatUtils.format(triple.middle, pattern),
                DateFormatUtils.format(triple.right, ""));
    }

    @Test
    void main() {
        Date now = new Date();
        Date fiveMinLater = DateUtils.addMinutes(now, 5);
        Date twoDaysBefore = DateUtils.addDays(now, -2);
        String pattern = "yyyy-MM-dd-HHmmss";
        log.info("now: {}\n, 5 mins later: {}\n, 2 days before: {} ",
                DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(now),
                DateFormatUtils.format(fiveMinLater, pattern),
                DateFormatUtils.format(twoDaysBefore, pattern));
    }

    /**
     * Stream 使用 collectAndThen 自定义去重
     */
    @Test
    void collectAndThen() {

        @Data
        class TempDTO {
            private Integer personId;
            private String name;

            public TempDTO(Integer personId, String name) {
                this.name = name;
                this.personId = personId;
            }
        }

        List<TempDTO> temperatureList = ImmutableList.of(
                new TempDTO(1, "haha"),
                new TempDTO(2, "haha"),
                new TempDTO(3, "haha"),
                new TempDTO(4, "haha"),
                new TempDTO(1, "hahaasdas"),
                new TempDTO(2, "hahaasdas")
        );

        Comparator<TempDTO> customizeComparator = comparingInt(TempDTO::getPersonId);
        List<TempDTO> result = temperatureList.stream().collect(
                collectingAndThen(
                        // 1. 将所有元素置入含有比较器的去重集合中，一般是 TreeSet
                        toCollection(() -> new TreeSet<>(customizeComparator)),
                        // 2. 去重后的元素置入新的容器里
                        Lists::newArrayList));

        result.forEach(System.out::println);
    }

}
