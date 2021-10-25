package com.medolia.demo.jvm.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lbli@trip.com
 * @date 2021/8/11
 */
@Slf4j
public enum OperationEnum {
    /**
     * 加法
     */
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    /**
     * 减法
     */
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    /**
     * 乘法
     */
    MULTI("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    /**
     * 除法
     */
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private String symbol;


    OperationEnum(String symbol) {
        this.symbol = symbol;
    }

    public abstract double apply(double x, double y);

    private static final Map<String, OperationEnum> stringToEnum = Stream.of(values()).collect(Collectors.toMap(
            Objects::toString, e -> e
    ));

    public static Optional<OperationEnum> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }
}
