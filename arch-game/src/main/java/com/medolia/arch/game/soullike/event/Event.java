package com.medolia.arch.game.soullike.event;

/**
 * @author lbli
 * @date 2022/5/1
 */
public interface Event {

    /**
     * event type
     * @return
     */
    EventTypeEnum getType();

    /**
     * easy to read format
     * @return
     */
    String consoleFormat();

}
