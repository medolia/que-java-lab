package com.medolia.lab.util.function;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * @author lbli
 * @date 2021/10/3
 */
public class CustomSupplier<T> implements Supplier<T> {

    volatile Supplier<T> delegate;
    volatile boolean initialized;
    T value;

    public CustomSupplier(Supplier<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T get() {
        // A 2-field variant of Double Checked Locking.
        if (!initialized) {
            synchronized (this) {
                if (!initialized) {
                    T t = delegate.get();
                    value = t;
                    initialized = true;
                    // Release the delegate to GC.
                    delegate = null;
                    // System.out.println("get after init");
                    return t;
                }
            }
        }
        System.out.println("get from cache");
        return value;
    }
}
