package com.medolia.arch.game.soullike;

import lombok.Data;

import java.util.Map;

/**
 * @author lbli
 * @date 2022/5/2
 */
@Data
public class Player extends BaseCharacter {

    private int
            level, bloodEchoes,
            insight,
            vitality, endurance, strength, skill, bloodTinge, arcane;

    private int discovery;

    private Weapon left1Weapon, left2Weapon, right1Weapon, right2Weapon, curLWeapon, curRWeapon;

    private Rune rune1, rune2, rune3, contractRune;

    @Data
    private static class Weapon {

        private int weaponId;
        private int refineLevel;
        private Map<ElementType, Integer> elementATKMap;
        private int bulletConsume;
        private int endurance;

    }

    @Data
    private static class Rune {
        private int runeId;
    }
}
