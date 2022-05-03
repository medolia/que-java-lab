package com.medolia.arch.game.gamble;

import java.math.BigDecimal;

/**
 * @author lbli
 * @since 0.0.1
 */
public class GambleLauncherTest {

    public static void main(String[] args) {

        GambleConfig gambleConfig = new GambleConfig(
                BigDecimal.valueOf(100000), BigDecimal.valueOf(100),
                50, 7,
                20, 80,
                20, 10, 43,
                50, 20);

        PairGuessTable pairGuessTable = PairGuessTable.initWithConfig(gambleConfig);
        pairGuessTable.start();
    }

}
