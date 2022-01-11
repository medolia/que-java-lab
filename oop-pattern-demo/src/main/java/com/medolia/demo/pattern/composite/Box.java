package com.medolia.demo.pattern.composite;

import java.math.BigDecimal;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class Box extends BaseProduct{

    @Override
    protected BigDecimal standAlonePrice() {
        return BigDecimal.ONE;
    }

    @Override
    public boolean isContainer() {
        return true;
    }
}
