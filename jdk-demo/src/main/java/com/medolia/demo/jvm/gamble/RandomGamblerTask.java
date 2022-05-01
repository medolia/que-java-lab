package com.medolia.demo.jvm.gamble;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lbli
 * @since 0.0.1
 */
public record RandomGamblerTask(PairGuessTable pairGuessTable) implements Runnable {

    @Override
    public void run() {

        while (true) {
            try {
                if (!pairGuessTable.isRunning()) {
                    break;
                }

                List<Gambler> gamblers = pairGuessTable.getGamblerList();
                GambleConfig config = pairGuessTable.getGambleConfig();
                int randomTimeout = config.nextRandomTimeout();
                TimeUnit.MILLISECONDS.sleep(randomTimeout);

                Pool randomPool = pairGuessTable.chooseRandomWithWeight(config.getLeftPoolWeight(), config.getRightPoolWeight());
                BigDecimal randomAmount = config.nextRandomAmount();
                Gambler randomGambler = gamblers.get(RandomUtils.nextInt(0, gamblers.size()));
                BigDecimal currentWinRatio = randomPool.getCurrentWinRatio();

                pairGuessTable.acceptEvent(
                        new PoolEvent(randomPool, randomAmount, randomGambler, currentWinRatio));

            } catch (InterruptedException e) {
                //
            }
        }
    }
}
