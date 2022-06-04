package com.medolia.arch.game.damage;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lbli
 * @date 2022/6/4
 */
@Data
@Builder
public class DamageSufferInfo {

    private List<RawDefence> defence;
    private boolean isOpen;
    private boolean isBeast;
    private boolean isKin;
    private boolean isSerratedTgt;
    private boolean isRighteousTgt;

}
