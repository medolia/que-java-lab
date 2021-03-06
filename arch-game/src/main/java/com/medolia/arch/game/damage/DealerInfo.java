package com.medolia.arch.game.damage;

import com.medolia.arch.game.Character;
import lombok.Data;

import java.util.List;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
public class DealerInfo {

    private Character dealer;
    private List<RawDamage> attack;
    private List<CalUnit> allCalUnits;
    private boolean isVisceralATK;
    private double hpPercent;

}
