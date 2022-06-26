package com.medolia.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 工具类
 *
 * @author lbli
 * @date 2021/8/17
 */
public class DateUtils {
    public static final String yMdHmsPattern = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
    }

    public static String now() {
        return now(yMdHmsPattern);
    }

    public static String now(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();

        return now.format(formatter);
    }
}
