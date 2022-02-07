package com.medolia.demo.jvm;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class MapTest {

    @Test
    void testMerge() {
        Map<String, List<String>> map = new HashMap<>();

        map.put("key1", Lists.newArrayList("value1"));
        map.put("key2", Lists.newArrayList());
        map.put("key3", Lists.newArrayList("value31"));

        map.merge("key3",
                Lists.newArrayList("value3"),
                (v1, v2) -> {
                    v1.addAll(v2);
                    return v1;
                });

        System.out.println(map);

    }
}
