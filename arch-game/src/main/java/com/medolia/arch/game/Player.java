package com.medolia.arch.game;

import com.google.common.collect.Lists;
import com.medolia.arch.game.event.DamageEvent;
import com.medolia.arch.game.event.Event;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lbli
 * @date 2022/5/2
 */
public class Player implements Character {

    private final String name;
    private final List<Buff> buffList;

    public Player() {
        this("player-" + RandomStringUtils.randomAlphanumeric(8));
    }

    public Player(String name) {
        this(name, Lists.newArrayList());
    }

    public Player(String name, List<Buff> buffList) {
        this.name = name;
        this.buffList = buffList;
    }

    public Player addBuff(Buff buff) {
        this.buffList.add(buff);
        return this;
    }

    public Player addBuffs(List<Buff> buffList) {
        this.buffList.addAll(buffList);
        return this;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Event dealDamage(BigDecimal orgValue, List<Character> suffers) {
        return new DamageEvent(this, suffers, orgValue);
    }

    @Override
    public List<Buff> buff() {
        return this.buffList;
    }
}
