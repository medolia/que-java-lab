package com.medolia.arch.game.gamble;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author lbli
 * @since 0.0.1
 */
public class Gambler {

    private static final Logger logger = LoggerFactory.getLogger(Gambler.class);

    private final String name;

    private BigDecimal userAmount;

    public Gambler() {
        name = RandomStringUtils.randomAscii(7);
        userAmount = BigDecimal.valueOf(100000);
    }

    public Gambler(String name, BigDecimal userAmount) {
        this.name = name;
        this.userAmount = userAmount;
    }

    public void gamble(BigDecimal amount) {
        synchronized (this) {
            if (this.userAmount.compareTo(BigDecimal.ZERO) < 0) {
                logger.debug("haha, gambler {} seems to be desperate~", this.name);
                throw new RuntimeException(this.name + " has had no chance to try anymore");
            }
            this.userAmount = this.userAmount.subtract(amount);
        }
    }

    public void receive(BigDecimal amount) {
        assert amount.compareTo(BigDecimal.ZERO) >= 0;
        synchronized (this) {
            this.userAmount = this.userAmount.add(amount);
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUserAmount() {
        return userAmount;
    }

    @Override
    public String toString() {
        return String.format("gambler[name: %s, cash: %s, net income: %s]",
                this.name,
                this.userAmount,
                this.userAmount.subtract(BigDecimal.valueOf(100000)));
    }
}
