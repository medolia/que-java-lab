package com.medolia.arch.game;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.medolia.arch.game.buff.BaseBuff;
import com.medolia.arch.game.buff.Buff;
import com.medolia.arch.game.buff.BuffFactory;
import com.medolia.arch.game.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author lbli
 * @date 2022/5/2
 */
@Slf4j
class EventDriveTest {

    List<Buff> dealerBuff = ImmutableList.of(
            new BaseBuff("crack", 1.2),
            new BaseBuff("smash", 1.35),
            new BaseBuff("eternal bless", 1.5));

    List<Buff> sufferBuff = ImmutableList.of(
            new BaseBuff("shield", 0.85),
            new BaseBuff("base armor", 0.75));

    @Test
    void testDamage() {

    }

    @Test
    void testBuff() {

    }
}