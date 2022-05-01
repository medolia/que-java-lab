package com.medolia.demo.pattern.state;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class Demo {

    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }

}
