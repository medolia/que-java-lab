package com.medolia.arch.game.event;

import com.medolia.arch.game.Character;
import com.medolia.arch.game.Damage;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author lbli
 * @date 2022/5/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DamageDetailEvent extends BaseEvent{

    private Character dealer, suffer;
    private BigDecimal orgValue;
    private Damage damage;

    public DamageDetailEvent(Character dealer, Character suffer, BigDecimal orgValue) {
        this.dealer = dealer;
        this.suffer = suffer;
        this.orgValue = orgValue;
        this.damage = new Damage(orgValue, dealer.buff(), suffer.buff());
    }

    @Override
    public EventTypeEnum getType() {
        return EventTypeEnum.DAMAGE_DEAL;
    }

    @Override
    protected String mainContent() {
        return String.format("<%s> deal %s damage to <%s> (%s)%n",
                dealer.name(),
                damage.finalValue(),
                suffer.name(),
                damage.detail());
    }
}
