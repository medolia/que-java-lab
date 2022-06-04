package com.medolia.arch.game.damage;

import lombok.Data;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
public class RawDamage {

    private DamageType damageType;
    private double atk;
    private boolean isFixed;

    public RawDamage(DamageType damageType, double atk) {
        this(damageType, atk, false);
    }

    public RawDamage(DamageType damageType, double atk, boolean isFixed) {
        this.damageType = damageType;
        this.atk = atk;
        this.isFixed = isFixed;
    }
}
