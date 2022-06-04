package com.medolia.arch.game.damage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawDefence {

    private DamageType defenceType;
    private double value;

}
