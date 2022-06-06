package com.medolia.arch.game.buff;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * @author lbli
 * @date 2022/5/1
 */
public interface Buff {

    String name();

    BigDecimal ratio();

    default boolean isValid() {return true;}
}
