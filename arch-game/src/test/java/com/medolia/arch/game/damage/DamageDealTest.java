package com.medolia.arch.game.damage;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.medolia.arch.game.soullike.Character;
import com.medolia.arch.game.soullike.ElementType;
import com.medolia.arch.game.soullike.damage.*;
import com.medolia.arch.game.soullike.event.DamageDetailEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.medolia.arch.game.soullike.damage.CalCondition.*;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Slf4j
class DamageDealTest {

    @Test
    void testDeal() {
        DealerInfo dealerInfo = generateDealerInfo();
        SufferInfo sufferInfo = generateSufferInfo();
        List<DamageDetailEvent> events = new Calculator(dealerInfo, sufferInfo).cal();
        log.info("events: {}", events);
    }

    private SufferInfo generateSufferInfo() {

        return SufferInfo.builder()
                .suffer(Character.testOne())
                .defence(ImmutableMap.of(
                        ElementType.PHYSICAL_NORMAL, new RawDefence(ElementType.PHYSICAL_NORMAL, 200),
                        ElementType.FIRE, new RawDefence(ElementType.FIRE, 10)))
                .isOpen(false)
                .isBeast(true)
                .isKin(false)
                .isSerratedTgt(true)
                .isRighteousTgt(false)
                .allCalUnits(ImmutableList.of(CalUnit.weakPart()))
                .build();
    }

    private DealerInfo generateDealerInfo() {

        DealerInfo res = new DealerInfo();
        RawDamage phyNormal = new RawDamage(ElementType.PHYSICAL_NORMAL, 360);
        RawDamage firePaper = new RawDamage(ElementType.FIRE, 80, true);
        res.setAttack(ImmutableList.of(phyNormal, firePaper));

        CalUnit fineGem1 = new CalUnit("fine-gem-1", 1.272, PHYSICAL_ALL);
        CalUnit fineGem2 = new CalUnit("fine-gem-2", 1.272, PHYSICAL_ALL);
        CalUnit fineGem3 = new CalUnit("fine-gem-3", 1.272, PHYSICAL_ALL);
        CalUnit open = CalUnit.defaultOpen();
        CalUnit serrated = new CalUnit("serrated", 1.2, SERRATED);
        CalUnit kin = new CalUnit("kin-target", 0.891, KIN);
        CalUnit rune1 = new CalUnit("rune-1", 1.1, VISCERAL_ATK);
        CalUnit rune2 = new CalUnit("rune-2", 1.2, VISCERAL_ATK);
        CalUnit rune3 = new CalUnit("rune-3", 1.3, VISCERAL_ATK);
        List<CalUnit> calUnits = new ImmutableList.Builder<CalUnit>()
                .add(fineGem1)
                .add(fineGem2)
                .add(fineGem3)
                .add(open)
                .add(serrated)
                .add(kin)
                .add(rune1)
                .add(rune2)
                .add(rune3)
                .build();
        res.setAllCalUnits(calUnits);
        res.setVisceralATK(false);
        res.setHpPercent(100);
        res.setDealer(Character.testOne());

        return res;
    }

}