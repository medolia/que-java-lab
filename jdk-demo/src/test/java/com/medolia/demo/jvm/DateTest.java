package com.medolia.demo.jvm;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lbli@trip.com
 * @date 2021/8/13
 */
public class DateTest {
    @Test
    void jdk8NewDateClassDemo() {
        Instant instant = Instant.now();
        System.out.println(instant);
    }

    @Test
    void demo() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        System.out.println(Calendar.getInstance().getTime());
    }

    /**
     * yyyy（小写）指的是当天所在的年，YYYY（大写，since JDK 7）指的是当天所在的周属于的年份，区别为本周跨年时后者会返回下一年；
     * MM（大写）指月份，mm（小写）指分钟；
     * HH（大写）、hh（小写）分别代指 24 小时制、12 小时制的小时；
     */
    @Test
    void formatDemo() {
        final String yMdHmsPattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yMdHmsPattern);
        String now = simpleDateFormat.format(new Date());
        System.out.println(now);

    }

    /**
     * 使用大写 Y pattern 会引发错误
     */
    @Test
    void YUpperCaseDemo() {
        final String pattern = "YYYY-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateStr = "2021-12-30 00:00:00";
        try {
            Date date = simpleDateFormat.parse(dateStr);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
