package com.medolia.arch.game.damage;

import lombok.Builder;
import lombok.Data;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
@Builder
public class ConditionContext {

    private double hpPercent;
    private DamageType damageType;
    private boolean isVisceralATK;
    private boolean foeIsBeast;
    private boolean foeIsKin;
    private boolean foeWeakSerrated;
    private boolean foeWeakRighteous;
    private boolean foeStayOpen;

}
