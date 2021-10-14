package com.medolia.demo.lang3;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lbli@trip.com
 * @date 2021/9/16
 */
public class TripleTest {
    private static Triple<String, String, String> triple;

    @BeforeAll
    public static void setUpTripleInstance() {
        triple = Triple.of("leftElement", "middleElement", "rightElement");
    }

    @Test
    public void whenCalledgetLeft_thenCorrect() {
        assertThat(triple.getLeft()).isEqualTo("leftElement");
    }

    @Test
    public void whenCalledgetMiddle_thenCorrect() {
        assertThat(triple.getMiddle()).isEqualTo("middleElement");
    }

    @Test
    public void whenCalledgetRight_thenCorrect() {
        assertThat(triple.getRight()).isEqualTo("rightElement");
    }
}
