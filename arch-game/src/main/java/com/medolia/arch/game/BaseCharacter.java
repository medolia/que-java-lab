package com.medolia.arch.game;

import java.util.Map;

/**
 * @author lbli
 * @date 2022/6/6
 */
public abstract class BaseCharacter implements Character{

    private int HP, stamina;

    private int physicalDEF, slowPoisonRES, rapidPoisonRES, frenzyRES, beasthood;

    private Map<ElementType, Integer> elementResMap;

}
