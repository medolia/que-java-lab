package com.medolia.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 工具类
 *
 * @author lbli@trip.com
 * @date 2021/8/17
 */
public class DateUtils {
    public static final String YMDHHMMSSPattern = "yyyy-MM-dd HH:mm:ss";
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");


    public static final long MIL_PER_SECOND = 1000;
    public static final long MIL_PER_MINUTE = MIL_PER_SECOND * 60;
    public static final long MIL_PER_HOUR = MIL_PER_MINUTE * 60;
    public static final long MIL_PER_DAY = MIL_PER_HOUR * 24;



    /**
     * 获取时间点后退一段后的时间
     */
    @SuppressWarnings("unchecked")
    public static <T extends Date> T addTime(T origin, FieldType type, int value) {
        assert origin != null;
        long milToAdd = type.getMillSec(value);
        long newTimeMil = origin.getTime() + milToAdd;
        T result = (T) new Date(newTimeMil);
        return result;
    }

    /**
     * 根据模型解析字符串形式的时间
     */
    public static Date parseDate(String timeStr, String pattern) throws ParseException {
        return parseDateWithLeniency(timeStr, pattern, true);
    }

    public static Date parseDateWithLeniency(String str, String pattern, boolean lenient) throws ParseException {
        if (str == null || pattern == null) {
            throw new IllegalArgumentException("Date string and pattern must not be null");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(lenient);
        return sdf.parse(str);
    }

    /**
     * 两个时间是否在同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * 两个时间是否在同一天
     */
    private static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 两个时间点相隔多久
     * @param start 开始时间点
     * @param end 结束时间点
     * @return 相隔多久，单位毫秒
     */
    public static long getInterval(Date start, Date end) {
        return end.getTime() - start.getTime();
    }



    public enum FieldType {
        /**
         * 天
         */
        Day {
            @Override
            long getMillSec(int value) {
                return MIL_PER_DAY * value;
            }
        },
        /**
         * 小时
         */
        Hour {
            @Override
            long getMillSec(int value) {
                return MIL_PER_HOUR * value;
            }
        },
        /**
         * 分钟
         */
        Minute {
            @Override
            long getMillSec(int value) {
                return MIL_PER_MINUTE * value;
            }
        },
        /**
         * 秒
         */
        Second {
            @Override
            long getMillSec(int value) {
                return MIL_PER_SECOND * value;
            }
        };

        abstract long getMillSec(int value);
    }

    private DateUtils() {
    }
}
