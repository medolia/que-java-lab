package com.medolia.demo.jdk.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author lbli@trip.com
 * @date 2021/8/13
 */
@Slf4j
public class IdenticalFieldNameTest {

    static class Parent {
        public int stock;

        public int getStock() {
            return stock;
        }
    }

    static class Child extends Parent {
        public int stock;

        public Child() {
            log.info("child constructor");
            this.stock = 15;
        }

    }

    /**
     * alibaba 11. 避免父子类完全相同的命名，即使这是合法的
     * 若保留子类中的同名字段声明，子类构造器阶段改变的字段值为父类处字段；
     * 而实际调用 getStock() 方法时返回的字段为子类字段值（未初始化，为 0）；
     */
    @Test
    void demo() {
        Parent pojo = new Child();
        log.info("stock: {}", pojo.getStock());
    }
}
