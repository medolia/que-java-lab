package com.medolia.arch.game.damage;

import com.medolia.arch.game.ElementType;
import com.medolia.arch.game.event.DamageDetailEvent;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Optional;

/**
 * @author lbli
 * @date 2022/6/6
 */
public class Calculator {

    private DealerInfo dealerInfo;
    private SufferInfo sufferInfo;

    public Calculator(DealerInfo dealerInfo, SufferInfo sufferInfo) {
        this.dealerInfo = dealerInfo;
        this.sufferInfo = sufferInfo;
    }

    public List<DamageDetailEvent> cal() {

        List<DamageDetailEvent> res = Lists.newArrayList();

        dealerInfo.getAttack().forEach(dmg -> {
            ElementType ele = dmg.getElementType();
            double def = Optional.ofNullable(this.sufferInfo.getDefence().get(ele)).map(RawDefence::getValue).orElse(0.0);

            CalContext context = CalContext.builder()
                    .dealer(dealerInfo.getDealer())
                    .suffer(sufferInfo.getSuffer())
                    .atk(dmg.getValue())
                    .def(def)
                    .elementType(dmg.getElementType())
                    .allCalUnits(getAllDmgUnits())
                    .hpPercent(dealerInfo.getHpPercent())
                    .isVisceralATK(dealerInfo.isVisceralATK())
                    .foeIsBeast(sufferInfo.isBeast())
                    .foeIsKin(sufferInfo.isKin())
                    .foeWeakSerrated(sufferInfo.isSerratedTgt())
                    .foeWeakRighteous(sufferInfo.isRighteousTgt())
                    .foeStayOpen(sufferInfo.isOpen())
                    .build();

            res.add(context.exec());
        });

        return res;
    }

    private List<CalUnit> getAllDmgUnits() {
        List<CalUnit> result = Lists.newArrayList();
        result.addAll(dealerInfo.getAllCalUnits());
        result.addAll(sufferInfo.getAllCalUnits());

        return result;
    }

}
