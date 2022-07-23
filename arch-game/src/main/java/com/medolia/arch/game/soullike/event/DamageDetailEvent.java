package com.medolia.arch.game.soullike.event;

import com.medolia.arch.game.soullike.Character;
import com.medolia.arch.game.soullike.damage.RawDamage;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lbli
 * @date 2022/5/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DamageDetailEvent extends BaseEvent {

    private Character dealer, suffer;
    private RawDamage damage;

    public DamageDetailEvent(Character dealer, Character suffer, RawDamage damage) {
        this.dealer = dealer;
        this.suffer = suffer;
        this.damage = damage;
    }

    @Override
    public EventTypeEnum getType() {
        return EventTypeEnum.DAMAGE_DEAL;
    }

    @Override
    protected String mainContent() {
        return String.format("<%s> deal %s(%s) damage to <%s>",
                dealer.toString(),
                damage.getValue(),
                damage.getElementType().name(),
                suffer.toString());
    }
}
