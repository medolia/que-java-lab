package com.medolia.arch.game;

import com.google.common.collect.ImmutableList;
import com.medolia.arch.game.buff.BaseBuff;
import com.medolia.arch.game.buff.Buff;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

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