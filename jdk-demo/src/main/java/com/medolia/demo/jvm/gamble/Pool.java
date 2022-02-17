package com.medolia.demo.jvm.gamble;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medolia.demo.util.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author lbli
 * @since 0.0.1
 */
public class Pool {
    private static final Logger logger = LoggerFactory.getLogger(Pool.class);

    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal currentWinRatio = BigDecimal.ZERO;
    private final String name;

    public Pool(BigDecimal totalAmount, String name) {
        this.totalAmount = totalAmount;
        this.name = name;
    }

    public void addAmount(BigDecimal amount) {
        // no turning back
        assert amount.compareTo(BigDecimal.ZERO) > 0;
        synchronized (this) {
            final BigDecimal addResult = this.totalAmount.add(amount);
            this.totalAmount = addResult;
        }
    }

    public BigDecimal getCurrentWinRatio() {
        return currentWinRatio;
    }

    public void setCurrentWinRatio(BigDecimal newWinRatio) {
        logger.debug("win ratio changed: {} -> {}", this.currentWinRatio, newWinRatio);
        this.currentWinRatio = newWinRatio;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return JsonSerializer.serialize(this);
    }
}
