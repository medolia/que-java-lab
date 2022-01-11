package com.medolia.demo.pattern.misc;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import javax.swing.event.ChangeEvent;

/**
 * @author lb@li@trip.com
 * @date 2021/7/19
 */
public class EventBusChangeRecorder {
    @Subscribe
    public void record(ChangeEvent event) {
        System.out.println(event.getSource());
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new EventBusChangeRecorder());

    }
}
