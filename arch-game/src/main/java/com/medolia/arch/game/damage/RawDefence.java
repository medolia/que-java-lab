package com.medolia.arch.game.damage;

import com.medolia.arch.game.ElementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawDefence {

    private ElementType defenceType;
    private double value;

}
