package com.medolia.arch.game.damage;

import com.medolia.arch.game.Character;
import com.medolia.arch.game.ElementType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
@Builder
public class SufferInfo {

    private Character suffer;
    private Map<ElementType, RawDefence> defence;
    private boolean isOpen;
    private boolean isBeast;
    private boolean isKin;
    private boolean isSerratedTgt;
    private boolean isRighteousTgt;
    private List<CalUnit> allCalUnits;

}
