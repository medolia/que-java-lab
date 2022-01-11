package com.medolia.demo.pattern.bridge;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public interface Device {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}
