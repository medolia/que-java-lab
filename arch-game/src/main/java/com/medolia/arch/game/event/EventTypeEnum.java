package com.medolia.arch.game.event;

/**
 * @author lbli
 * @date 2022/5/1
 */
public enum EventTypeEnum {
    /**
     * unknown
     */
    UNKNOWN(-1),
    /**
     * deal damage
     */
    DAMAGE_DEAL(10),
    ;

    private final Integer priority;

    EventTypeEnum(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }
}
