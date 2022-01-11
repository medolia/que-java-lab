package com.medolia.demo.pattern.composite;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public abstract class BaseProduct implements Product {

    public BaseProduct() {
        this.subProducts = Lists.newArrayList();
    }

    private final List<Product> subProducts;

    public void addProduct(Product product) {
        if (isContainer()) {
            subProducts.add(product);
        } else {
            throw new UnsupportedOperationException("this is not a container!");
        }
    }

    /**
     * 单独价格
     *
     * @return 单独价
     */
    protected abstract BigDecimal standAlonePrice();

    @Override
    public BigDecimal calculatePrice() {
        BigDecimal subProductTotalPrice = isContainer() ?
                subProducts.stream()
                        .map(Product::calculatePrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add) :
                BigDecimal.ZERO;
        return standAlonePrice().add(subProductTotalPrice);
    }
}
