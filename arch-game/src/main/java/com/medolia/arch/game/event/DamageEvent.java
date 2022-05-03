package com.medolia.arch.game.event;

import com.medolia.arch.game.Character;
import com.medolia.arch.game.Damage;
import com.medolia.arch.game.exception.DamageCalException;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lbli
 * @date 2022/5/1
 */
public class DamageEvent extends BaseEvent {

    private final Character dealer;
    private final List<Character> sufferList;
    private final List<DamageDetailEvent> damageDetailEventList = Lists.newArrayList();

    public DamageEvent(Character dealer, List<Character> sufferList, BigDecimal orgValue) {
        this.dealer = dealer;
        this.sufferList = sufferList;

        sufferList.forEach(suffer ->
        {
            DamageDetailEvent damageDetailEvent = new DamageDetailEvent(dealer, suffer, orgValue);
            damageDetailEventList.add(damageDetailEvent);
        });
    }

    @Override
    public EventTypeEnum getType() {
        return EventTypeEnum.DAMAGE_DEAL;
    }

    @Override
    protected String mainContent() {
        StringBuilder result = new StringBuilder();

        String mainSentence = String.format("<%s> deal %s damage in total to %s%n",
                dealer.name(),
                damageDetailEventList.stream()
                        .map(DamageDetailEvent::getDamage)
                        .map(Damage::finalValue)
                        .reduce(BigDecimal::add)
                        .orElseThrow(DamageCalException::new),
                sufferList.stream()
                        .map(Character::name)
                        .collect(Collectors.joining(",", "<", ">")));
        result.append(mainSentence);

        result.append("detail: \n");
        damageDetailEventList.forEach(e -> result.append(e.mainContent()));

        return result.toString();
    }
}
