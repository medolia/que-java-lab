package com.medolia.arch.game.buff;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author lbli
 * @date 2022/5/2
 */
@EqualsAndHashCode
public class BaseBuff implements Buff{

    protected String name;
    protected BigDecimal ratio;
    protected Duration duration;
    protected LocalDateTime endTime;

    public BaseBuff(String name, BigDecimal ratio, Duration duration) {
        this.name = name;
        this.ratio = ratio;
        this.duration = duration;
        this.endTime = LocalDateTime.now().plus(duration);
    }

    public BaseBuff(String name, BigDecimal ratio) {
        this(name, ratio, Duration.ZERO);
    }

    public BaseBuff(String name, Double ratio) {
        this(name, BigDecimal.valueOf(ratio));
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public BigDecimal ratio() {
        return this.ratio;
    }

    @Override
    public boolean isValid() {
        return LocalDateTime.now().isBefore(this.endTime);
    }

    @Override
    public String toString() {
        return String.format("*%s(%s)", this.ratio, this.name);
    }
}
