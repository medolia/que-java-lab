package com.medolia.demo.jvm.gamble;

import com.medolia.demo.util.JsonSerializer;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lbli
 * @since 0.0.1
 */
public class PairGuessTable {
    private static final BigDecimal EMPLOYER_RATIO = BigDecimal.valueOf(0.05);
    private static final BigDecimal AFTER_RATIO = BigDecimal.ONE.subtract(EMPLOYER_RATIO);
    private static final Logger logger = LoggerFactory.getLogger(PairGuessTable.class);
    private static final AtomicInteger EVENT_COUNTER = new AtomicInteger(0);
    private static final int STATUS_START = 1;
    private static final int STATUS_END = 2;

    private final Stack<PoolEvent> eventStack = new Stack<>();
    private final AtomicInteger ctl = new AtomicInteger(0);
    private final GambleConfig gambleConfig;
    private Pool left, right;
    private List<Gambler> gamblerList;

    public PairGuessTable(GambleConfig gambleConfig) {
        this.gambleConfig = gambleConfig;
    }

    public static PairGuessTable initWithConfig(GambleConfig gambleConfig) {

        PairGuessTable pairGuessTable = new PairGuessTable(gambleConfig);
        int gamblerCount = gambleConfig.getGamblerCount();

        List<Gambler> gamblers = Lists.newArrayList();
        int i = 0;
        while (i++ < gamblerCount) {
            gamblers.add(
                    new Gambler(
                            RandomStringUtils.randomAscii(gambleConfig.getGamblerNameLength()),
                            gambleConfig.getGamblerInitial()));
        }

        pairGuessTable.setLeft(new Pool(gambleConfig.getPoolInitial(), "left"));
        pairGuessTable.setRight(new Pool(gambleConfig.getPoolInitial(), "right"));
        pairGuessTable.setGamblerList(gamblers);

        return pairGuessTable;
    }

    public GambleConfig getGambleConfig() {
        return gambleConfig;
    }

    public List<Gambler> getGamblerList() {
        return gamblerList;
    }

    public void start() {

        if (this.ctl.get() == STATUS_START) {
            return;
        }

        this.ctl.set(STATUS_START);
        int gamblerCount = this.gambleConfig.getGamblerCount();
        ExecutorService executorService = Executors.newFixedThreadPool(gamblerCount);

        for (int i = 1; i < gamblerCount; i++) {
            executorService.submit(new RandomGamblerTask(this));
        }

        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(this.gambleConfig.getGambleTimeSecond());
                end(chooseRandom());
            } catch (InterruptedException e) {
                //
            }
        });

        executorService.shutdown();
    }

    public void setGamblerList(List<Gambler> gamblerList) {
        this.gamblerList = gamblerList;
    }

    public void setLeft(Pool left) {
        this.left = left;
    }

    public void setRight(Pool right) {
        this.right = right;
    }

    public Pool chooseRandom() {
        return RandomUtils.nextBoolean() ? left : right;
    }

    public Pool chooseRandomWithWeight(int leftWeight, int rightWeight) {

        int nextInt = RandomUtils.nextInt(0, leftWeight + rightWeight);

        if (nextInt >= 0 && nextInt < leftWeight) {
            return left;
        } else {
            return right;
        }
    }

    public void acceptEvent(PoolEvent poolEvent) {
        if (!isRunning()) {
            return;
        }

        EVENT_COUNTER.addAndGet(STATUS_START);
        // logger.debug("event accepted: {}", JsonSerializer.serialize(poolEvent));

        synchronized (this) {

            try {
                Gambler gambler = poolEvent.getGambler();
                gambler.gamble(poolEvent.getAddedAmount());

                eventStack.push(poolEvent);

                Pool pool = poolEvent.getPool();
                pool.addAmount(poolEvent.getAddedAmount());

                if (pool == left || pool == right) {
                    BigDecimal leftTotalAmount = left.getTotalAmount(),
                            rightTotalAmount = right.getTotalAmount();

                    BigDecimal leftCurrentRatio = rightTotalAmount
                            .multiply(AFTER_RATIO)
                            .divide(leftTotalAmount, RoundingMode.DOWN);
                    left.setCurrentWinRatio(leftCurrentRatio);

                    BigDecimal rightCurrentRatio = leftTotalAmount
                            .multiply(AFTER_RATIO)
                            .divide(rightTotalAmount, RoundingMode.DOWN);
                    right.setCurrentWinRatio(rightCurrentRatio);
                }

                logger.debug("event handled: {}", JsonSerializer.serialize(poolEvent));
            } catch (Exception e) {
                //
            }
        }
    }

    public void end(Pool winnerPool) {
        end(winnerPool, false);
    }

    public void end(Pool winnerPool, boolean dynamic) {
        ctl.set(STATUS_END);
        beforeFinishSummary(winnerPool);

        BigDecimal serverAmount = left.getTotalAmount().add(right.getTotalAmount());
        BigDecimal finalTotalAmount = serverAmount.add(BigDecimal.ZERO);
        while (!eventStack.isEmpty()) {
            PoolEvent event = eventStack.pop();
            if (event.getPool() == winnerPool) {
                BigDecimal bonus = dynamic ?
                        event.bonus() :
                        event.getAddedAmount().multiply(BigDecimal.ONE.add(winnerPool.getCurrentWinRatio()));
                BigDecimal newServerAmount = serverAmount.subtract(bonus);
                event.getGambler().receive(bonus);

                // logger.debug("serverAmount: {} -> {}", serverAmount, newServerAmount);
                serverAmount = newServerAmount;
            }

        }

        afterFinishSummary(finalTotalAmount, serverAmount);
    }

    private void afterFinishSummary(BigDecimal finalTotalAmount, BigDecimal serverAmount) {
        logger.info("scale: {} final income( expect: {} ): {}",
                finalTotalAmount,
                finalTotalAmount
                        .multiply(EMPLOYER_RATIO)
                        .subtract(gambleConfig.getPoolInitial().multiply(BigDecimal.valueOf(2))),
                serverAmount);
        printGamblersInfo();

        gamblerList.stream()
                .filter(e -> e.getUserAmount().compareTo(BigDecimal.ZERO) > 0)
                .max(Comparator.comparing(Gambler::getUserAmount)).ifPresent(gambler -> {
                    logger.info("biggest winner: {}", gambler);
                });
    }

    private void printGamblersInfo() {
        logger.info("--------------------- gamblers ---------------");
        gamblerList.forEach(gambler -> logger.info(gambler.toString()));
        BigDecimal totalGamblerIncome = gamblerList.stream()
                .map(Gambler::getUserAmount)
                .reduce(BigDecimal.ZERO
                                .subtract(gambleConfig.getGamblerInitial())
                                .multiply(BigDecimal.valueOf(gamblerList.size())),
                        BigDecimal::add);
        logger.info("gamblers got: {}", totalGamblerIncome);
    }


    private void beforeFinishSummary(Pool winnerPool) {
        logger.info("---------------- gamble finished ----------------");
        logger.info("winner: {} pool", winnerPool == left ? "left" : "right");
        logger.info("summary: left: {}, right: {}", left, right);
        logger.info("event count: {}, event stack size: {}", EVENT_COUNTER.get(), eventStack.size());

        printGamblersInfo();
    }

    public boolean isRunning() {
        return ctl.get() == STATUS_START;
    }

}
