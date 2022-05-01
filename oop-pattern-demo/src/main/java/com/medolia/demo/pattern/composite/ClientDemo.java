package com.medolia.demo.pattern.composite;

import java.math.BigDecimal;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class ClientDemo {
    public static void main(String[] args) {
        Box box1 = new Box(), box2 = new Box(), box3 = new Box();
        box3.addProduct(new Phone());
        box3.addProduct(new Phone());
        box3.addProduct(new Charger());

        box2.addProduct(new Charger());
        box2.addProduct(box3);

        box1.addProduct(new Phone());
        box1.addProduct(new Phone());
        box1.addProduct(box2);

        BigDecimal totalPrice = box1.calculatePrice();
        BigDecimal expectTotal = BigDecimal.ONE.multiply(BigDecimal.valueOf(3))
                .add(BigDecimal.TEN.multiply(BigDecimal.valueOf(4)))
                .add(BigDecimal.valueOf(5).multiply(BigDecimal.valueOf(2)));
        System.out.println("expect: " + expectTotal.toPlainString());
        System.out.println("total: " + totalPrice.toPlainString());
    }
}
