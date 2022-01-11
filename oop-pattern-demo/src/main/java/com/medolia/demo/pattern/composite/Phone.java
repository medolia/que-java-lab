package com.medolia.demo.pattern.composite;

import java.math.BigDecimal;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class Phone extends BaseProduct {

    @Override
    protected BigDecimal standAlonePrice() {
        return BigDecimal.TEN;
    }
}
