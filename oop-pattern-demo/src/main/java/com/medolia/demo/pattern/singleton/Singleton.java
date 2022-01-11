package com.medolia.demo.pattern.singleton;

import java.util.Objects;

/**
 * 双重检查模式单例
 *
 * @author lbli@trip.com
 * @date 2021/9/10
 */
public class Singleton {

    private volatile FieldType field;

    public FieldType getField() {
        // 使用局部变量读取一次，可提升性能
        FieldType result = field;
        if (Objects.isNull(result)) {
            synchronized (this) {
                if (Objects.isNull(field)) {
                    field = result = new FieldType();
                }
            }
        }

        return result;
    }

    static class FieldType {
    }
}
