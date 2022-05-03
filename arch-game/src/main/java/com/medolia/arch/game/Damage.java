package com.medolia.arch.game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lbli
 * @date 2022/5/1
 */
public class Damage {

    private final BigDecimal orgValue, finalValue;
    private final List<Buff> dealerBuff, sufferBuff;

    public Damage(BigDecimal orgValue, List<Buff> dealerBuff, List<Buff> sufferBuff) {
        this.orgValue = orgValue.setScale(2, RoundingMode.UP);
        this.dealerBuff = dealerBuff;
        this.sufferBuff = sufferBuff;

        this.finalValue = Stream.concat(dealerBuff.stream(), sufferBuff.stream())
                .map(Buff::ratio)
                .reduce(orgValue, BigDecimal::multiply)
                .setScale(2, RoundingMode.UP);
    }

    public BigDecimal finalValue() {
        return finalValue;
    }

    public String detail() {
        StringBuilder result = new StringBuilder(orgValue.toPlainString());
        Stream.concat(dealerBuff.stream(), sufferBuff.stream()).forEach(e -> result.append(e.toString()));
        result.append("=").append(finalValue.toPlainString());
        return result.toString();
    }
}
