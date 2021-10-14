package com.medolia.spring.demo.refactor;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */
@Data
public class Order {
    /**
     * 订单来源
     */
    private String source;
    /**
     * 支付方式
     */
    private String payMethod;
    /**
     * 订单编号
     */
    private String code;
    /**
     * 订单金额
     */
    private BigDecimal amount;
    // ...其他的一些字段
}
