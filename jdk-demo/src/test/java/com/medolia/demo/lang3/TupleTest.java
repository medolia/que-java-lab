package com.medolia.demo.lang3;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author lbli@trip.com
 * @date 2021/9/16
 */
public class TupleTest {
    private static ImmutablePair<String, String> immutablePair;

    @BeforeAll
    public static void setUpTripleInstance() {
        immutablePair = new ImmutablePair<>("leftElement", "rightElement");
    }

    @Test
    public void whenCalledgetLeft_thenCorrect() {
        assertThat(immutablePair.getLeft()).isEqualTo("leftElement");
    }

    @Test
    public void whenCalledgetRight_thenCorrect() {
        assertThat(immutablePair.getRight()).isEqualTo("rightElement");
    }

    @Test
    public void whenCalledof_thenCorrect() {
        assertThat(ImmutablePair.of("leftElement", "rightElement"))
                .isInstanceOf(ImmutablePair.class);
    }

    @Test
    public void whenCalledSetValue_thenThrowUnsupportedOperationException() {
        try {
            immutablePair.setValue("newValue");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

}
