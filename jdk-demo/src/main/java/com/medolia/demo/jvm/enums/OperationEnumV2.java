package com.medolia.demo.jvm.enums;

import java.util.function.DoubleBinaryOperator;

/**
 * @author lbli@trip.com
 * @date 2021/9/2
 */
public enum OperationEnumV2 {
    /**
     * 加法
     */
    PLUS("+", Double::sum),
    /**
     * 减法
     */
    MINUS("-", (x, y) -> x - y) ,
    /**
     * 乘法
     */
    MULTI("*",(x, y) -> x * y) ,
    /**
     * 除法
     */
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    OperationEnumV2(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }
}
