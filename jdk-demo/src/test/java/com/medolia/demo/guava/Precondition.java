package com.medolia.demo.guava;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author lbli@trip.com
 * @date 2021/9/8
 */
public class Precondition {


    @Test
    void checkArgs() {
        boolean isIllegal = ThreadLocalRandom.current().nextBoolean();
        checkArgument(isIllegal, "不太幸运？");
    }
}
