package com.medolia.arch.game.gamble;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * @author lbli
 * @since 0.0.1
 */
@JsonIgnoreProperties(value = {"pool.totalAmount", "pool.currentWinRatio"})
public class PoolEvent {

    private final Pool pool;
    private final BigDecimal addedAmount;
    private final Gambler gambler;
    private final BigDecimal winRatioSnapshot;

    public PoolEvent(Pool pool, BigDecimal addedAmount, Gambler gambler, BigDecimal winRatioSnapshot) {
        this.pool = pool;
        this.addedAmount = addedAmount;
        this.gambler = gambler;
        this.winRatioSnapshot = winRatioSnapshot;
    }

    public Pool getPool() {
        return pool;
    }

    public BigDecimal getAddedAmount() {
        return addedAmount;
    }

    public Gambler getGambler() {
        return gambler;
    }

    public BigDecimal getWinRatioSnapshot() {
        return winRatioSnapshot;
    }

    public BigDecimal bonus() {
        return this.addedAmount.multiply(BigDecimal.ONE.add(this.winRatioSnapshot));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
