package com.medolia.spring.demo.spel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Objects;

/**
 * @author lbli
 * @date 2022/5/27
 */
@Slf4j
public class SPELDemo {

    @Test
    void demo() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.purchaseName");
        Order order = new Order();
        order.setPurchaseName("medolia");
        Object value = expression.getValue(order);
        log.info(Objects.requireNonNull(value).toString());
    }

    @Data
    private static class Order {

        private String purchaseName;

    }
}
