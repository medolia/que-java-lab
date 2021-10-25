package com.medolia.demo.jvm;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author lbli@trip.com
 * @date 2021/9/9
 */
public class ScaleTest {
    @Test
    void subtract() {
        System.out.println(1.03 - 0.42);
        System.out.println(1.00 - 9 *0.10);

        BigDecimal var1 = new BigDecimal("0.00000000000000024153");
        BigDecimal var2 = BigDecimal.valueOf(0.42);
        System.out.println(var1.subtract(var2));
    }
}
