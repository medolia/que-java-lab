package com.medolia.demo.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli@trip.com
 * @date 2021/8/17
 */
class DateUtilsTest {

    @Test
    void addTime() {
        Date now = new Date();
        Date tmp = DateUtils.addTime(now, DateUtils.FieldType.Day, -20);
        assertEquals(now.getTime(), tmp.getTime() + 20 * DateUtils.MIL_PER_DAY);
    }

    @Test
    void parseDate() throws ParseException {
        Date date = DateUtils.parseDate("2007-06-18 19:33:10", DateUtils.YMDHHMMSSPattern);
        System.out.println(date);
    }

    @Test
    void parseDateWithLeniency() {
    }

    @Test
    void isSameDay() {

    }

    @Test
    void getInterval() {
    }
}