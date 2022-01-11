package com.medolia.demo.pattern.bridge;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}
