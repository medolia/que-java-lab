package com.medolia.arch.game.soullike.damage;

import com.medolia.arch.game.soullike.Character;
import com.medolia.arch.game.soullike.ElementType;
import com.medolia.arch.game.soullike.event.DamageDetailEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CalContext {
    private Character dealer, suffer;
    private double atk, def;
    private ElementType elementType;

    private List<CalUnit> allCalUnits;

    private double hpPercent;
    private boolean isVisceralATK;
    private boolean foeIsBeast;
    private boolean foeIsKin;
    private boolean foeWeakSerrated;
    private boolean foeWeakRighteous;
    private boolean foeStayOpen;

    public DamageDetailEvent exec() {
        allCalUnits.add(CalUnit.defence(this.atk, this.def));
        List<CalUnit> validCalUnits = allCalUnits.stream()
                .filter(unit -> unit.getCondition().match(this))
                .collect(Collectors.toList());
        log.debug("all valid dmg units: {}", validCalUnits);

        double dmg = validCalUnits.stream().map(CalUnit::getValue).reduce(atk, (v1, v2) -> v1 * v2);

        return DamageDetailEvent.builder()
                .dealer(dealer)
                .suffer(suffer)
                .damage(new RawDamage(elementType, dmg))
                .build();
    }

}
