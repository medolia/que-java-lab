package com.medolia.demo.pattern.misc.streamSimu;

import java.util.function.Consumer;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public interface Sink<T> extends Consumer<T> {

    default void begin(long size) {

    }

    default void end() {

    }

    abstract class ChainedReference<T, OUT> implements Sink<T> {

        protected final Sink<OUT> downstream;

        public ChainedReference(Sink<OUT> downstream) {
            this.downstream = downstream;
        }
    }
}