package com.medolia.demo.guava;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * @author lbli
 * @date 2021/10/2
 */
public class CostlySupplier {
    public static BigInteger generateBigNumber() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
        return new BigInteger("12345");
    }


}
