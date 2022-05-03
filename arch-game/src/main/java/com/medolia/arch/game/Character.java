package com.medolia.arch.game;

import com.medolia.arch.game.event.Event;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lbli
 * @date 2022/5/1
 */
public interface Character {
    /**
     * name
     * @return
     */
    String name();

    /**
     * deal damage
     * @param suffers
     * @return
     */
    Event dealDamage(BigDecimal orgValue, List<Character> suffers);

    /**
     * suffer damage
     * @param source
     * @return
     */
    //Event sufferDamage(Character source);

    /**
     * current buff
     * @return
     */
    List<Buff> buff();

}
