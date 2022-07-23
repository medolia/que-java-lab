package com.medolia.arch.game.soullike.event;

import com.medolia.demo.util.DateUtils;

/**
 * @author lbli
 * @date 2022/5/1
 */
public abstract class BaseEvent implements Event{

    @Override
    public EventTypeEnum getType() {
        return EventTypeEnum.UNKNOWN;
    }

    @Override
    public String consoleFormat() {
        return String.format("[%s][%s] %s", DateUtils.now(), getType().name(), mainContent());
    }

    protected abstract String mainContent();
}
