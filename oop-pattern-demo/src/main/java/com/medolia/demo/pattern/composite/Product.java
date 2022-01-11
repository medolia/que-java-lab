package com.medolia.demo.pattern.composite;

import java.math.BigDecimal;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public interface Product {

    /**
     * 计算总价
     * @return 总价
     */
    BigDecimal calculatePrice();

    /**
     * 是否可包含其他产品
     * @return 是否
     */
    default boolean isContainer() {
        return false;
    }

}
