package com.medolia.arch.game.damage;

import lombok.Data;

import java.util.List;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
public class DamageDealerInfo {

    private List<RawDamage> attack;
    private List<CalUnit> allCalUnits;

}
