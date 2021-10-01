package com.medolia.lab.sequence;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lbli
 * @date 2021/9/14
 */
@Data
public class SequenceGenerator {
    private String prefix;
    private String suffix;
    private Integer initial;
    private final AtomicInteger counter = new AtomicInteger();
}
