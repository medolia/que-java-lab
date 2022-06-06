package com.medolia.arch.game.damage;

import com.medolia.arch.game.ElementType;
import lombok.Data;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
public class RawDamage {

    private ElementType elementType;
    private double value;
    private boolean isFixed;

    public RawDamage(ElementType elementType, double value) {
        this(elementType, value, false);
    }

    public RawDamage(ElementType elementType, double value, boolean isFixed) {
        this.elementType = elementType;
        this.value = value;
        this.isFixed = isFixed;
    }
}
