package com.medolia.arch.game.gamble;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lbli
 * @since 0.0.1
 */
public record GambleConfig(BigDecimal gamblerInitial, BigDecimal poolInitial,
                           int gamblerCount, int gamblerNameLength,
                           int leftPoolWeight, int rightPoolWeight,
                           int gambleIntervalMills, int gambleIntervalOffsetMills, int gambleTimeSecond,
                           double gambleAmount, double gambleAmountOffset) {

    public BigDecimal nextRandomAmount() {
        return BigDecimal.valueOf(
                        RandomUtils.nextDouble(
                                gambleAmount - gambleAmountOffset,
                                gambleAmount + gambleAmountOffset))
                .setScale(2, RoundingMode.DOWN);

    }

    public int nextRandomTimeout() {
        return RandomUtils.nextInt(
                gambleIntervalMills - gambleIntervalOffsetMills,
                gambleIntervalMills + gambleIntervalOffsetMills);
    }

    public int getGambleTimeSecond() {
        return gambleTimeSecond;
    }

    public int getGamblerNameLength() {
        return gamblerNameLength;
    }

    public BigDecimal getGamblerInitial() {
        return gamblerInitial;
    }

    public BigDecimal getPoolInitial() {
        return poolInitial;
    }

    public int getGamblerCount() {
        return gamblerCount;
    }

    public int getLeftPoolWeight() {
        return leftPoolWeight;
    }

    public int getRightPoolWeight() {
        return rightPoolWeight;
    }
}
