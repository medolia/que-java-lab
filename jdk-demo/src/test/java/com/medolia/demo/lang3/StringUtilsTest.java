package com.medolia.demo.lang3;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author lbli@trip.com
 * @date 2021/9/16
 */
public class StringUtilsTest {
    @Test
    void testAppend() {
        List<String> stationNames = Lists.newArrayList("南京", "南京站");
        stationNames.forEach(e -> System.out.println(StringUtils.appendIfMissing(e, "站")));
    }
}
