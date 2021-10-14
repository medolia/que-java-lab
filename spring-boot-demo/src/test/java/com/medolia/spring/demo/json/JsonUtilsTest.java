package com.medolia.spring.demo.json;

import com.medolia.spring.demo.util.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lbli@trip.com
 * @date 2021/8/27
 */
class JsonUtilsTest {

    @Test
    void testDeserialize() {
    }

    @Test
    void serialize() throws Throwable {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        String json1 = JsonUtils.serialize(calendar);
        System.out.println(json1);
        String json2 = JsonUtils.serialize(now);
        System.out.println(json2);
        assertEquals(json1, json2);
    }

    @Test
    void dataSerialize() throws Throwable {
        Map<Date, String> map = new HashMap<>();
        map.put(new Date(), "now");
        System.out.println(JsonUtils.serialize(map));
    }


}