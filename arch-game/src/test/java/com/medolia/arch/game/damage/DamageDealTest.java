package com.medolia.arch.game.damage;

import com.google.common.collect.ImmutableList;
import com.medolia.arch.game.Character;
import com.medolia.arch.game.Player;
import com.medolia.arch.game.event.BaseEvent;
import com.medolia.arch.game.event.EventTypeEnum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.medolia.arch.game.damage.Condition.*;

/**
 * @author lbli
 * @date 2022/6/4
 */
class DamageDealTest {

    @Test
    void testDeal() {
        DamageDealerInfo dealerInfo = generateDealerInfo();
        DamageSufferInfo sufferInfo = generateSufferInfo();


    }

    private DamageSufferInfo generateSufferInfo() {
        DamageSufferInfo res = DamageSufferInfo.builder()
                .defence(ImmutableList.of(
                        new RawDefence(DamageType.PHYSICAL_NORMAL, 200),
                        new RawDefence(DamageType.FIRE, 10)))
                .isBeast(true)
                .isKin(false)
                .isOpen(false)
                .isRighteousTgt(false)
                .isSerratedTgt(true)
                .build();

        return res;
    }

    private DamageDealerInfo generateDealerInfo() {

        DamageDealerInfo res = new DamageDealerInfo();
        RawDamage phyNormal = new RawDamage(DamageType.PHYSICAL_NORMAL, 360);
        RawDamage firePaper = new RawDamage(DamageType.FIRE, 80, true);
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

        return res;
    }

    static class SimpleDamageEvent extends BaseEvent {

        private Character dealer, suffer;
        private double dmgValue;

        public SimpleDamageEvent(double dmgValue) {
            this.dealer = new Player("test-player");
            this.suffer = new Player("test-blood-beast");
            this.dmgValue = dmgValue;
        }

        @Override
        public EventTypeEnum getType() {
            return EventTypeEnum.DAMAGE_DEAL;
        }

        @Override
        protected String mainContent() {
            return String.format("%s -> %s: %s", dealer, suffer, dmgValue);
        }
    }

}