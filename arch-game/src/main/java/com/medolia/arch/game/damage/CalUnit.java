package com.medolia.arch.game.damage;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import lombok.Data;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
public class CalUnit {

    private String name;
    private double value;
    private Condition condition;

    public CalUnit(String name, double value, Condition condition) {
        this.name = name;
        this.value = value;
        this.condition = condition;
    }

    public CalUnit(String name, double value) {
        this(name, value, Condition.INVALID);
    }

    @SuppressWarnings("all")
    public static CalUnit beasthood(int beasthoodValue) {
        ImmutableRangeMap<Integer, Double> rangeMap =
                ImmutableRangeMap.<Integer, Double>builder()
                        .put(Range.atMost(0), 1.0)
                        .put(Range.closed(1, 24), 1.2)
                        .put(Range.closed(25, 49), 1.3)
                        .put(Range.closed(50, 99), 1.4)
                        .put(Range.closed(100, 199), 1.5)
                        .put(Range.closed(200, 299), 1.6)
                        .put(Range.atLeast(300), 1.7)
                        .build();
        return new CalUnit("beasthood", rangeMap.get(beasthoodValue));
    }

    public static CalUnit defaultOpen() {
        return new CalUnit("open counter", 1.4);
    }

    public static CalUnit defence(double atk, double def) {
        double value = 1.0;
        double p1 = atk * 8,
                p2 = atk,
                p3 = atk * 0.4,
                p4 = atk * 0.125;
        if (def >= p1) {
            value = 0.1;
        } else if (def >= p2) {
            value = (19.2 / 49 * Math.sqrt((atk / def) - 0.125) + 0.1) * atk;
        } else if (def >= p3) {
            value = (-0.4 / 3 * Math.sqrt(atk / def - 2.5) + 0.7) * atk;
        } else if (def >= p4) {
            value = (-0.8 / 121 * Math.sqrt((atk / def - 8)) + 0.9) * atk;
        } else {
            value = 0.9;
        }

        return new CalUnit("defence", value);
    }

}
