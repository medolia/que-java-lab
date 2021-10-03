package com.medolia.lab.util.function;


import java.util.function.Supplier;

/**
 * @author lbli
 * @date 2021/10/3
 */
public class CustomSuppliers {
    public static <T> Supplier<T> memoize(Supplier<T> delegate) {
        if (delegate instanceof CustomSupplier) {
            return delegate;
        }
        return new CustomSupplier<T>(delegate);
    }
}
