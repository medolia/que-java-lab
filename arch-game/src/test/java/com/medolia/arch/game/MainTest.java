package com.medolia.arch.game;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.medolia.arch.game.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author lbli
 * @date 2022/5/2
 */
@Slf4j
class MainTest {

    List<Buff> dealerBuff = ImmutableList.of(
            new BaseBuff("crack", 1.2),
            new BaseBuff("smash", 1.35),
            new BaseBuff("eternal bless", 1.5));

    List<Buff> sufferBuff = ImmutableList.of(
            new BaseBuff("shield", 0.85),
            new BaseBuff("base armor", 0.75));

    @Test
    void testDamage() {
        Character dealer = new Player("dealer").addBuffs(dealerBuff);

        List<Character> suffers = Lists.newArrayList();
        IntStream.range(1, 10).forEach(e -> {
            suffers.add(new Player().addBuffs(sufferBuff));
        });

        Event event = dealer.dealDamage(BigDecimal.valueOf(99999), suffers);
        log.info("event: {}", event.consoleFormat());
    }

    @Test
    void testBuff() {
        BigDecimal orgValue = BigDecimal.valueOf(5739.5);
        Damage damage = new Damage(orgValue, dealerBuff, sufferBuff);
        BigDecimal finalValue = damage.finalValue();
        log.info("final value: {}, detail: {}", finalValue, damage.detail());
    }
}