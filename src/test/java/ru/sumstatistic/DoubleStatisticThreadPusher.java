package ru.sumstatistic;

import ru.numstatistic.DoubleStatistic;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * thread for offering numbers in multiple threads in {@link DoubleStatisticImplMultithreadingTests}
 */
public class DoubleStatisticThreadPusher implements Runnable {

    /**
     * MAX_WAIT_TIME in seconds
     */
    private static final long MAX_WAIT_TIME = 5;
    /**
     * values which will be offered from current thread
     */
    private List<Double> values;
    private DoubleStatistic minMaxAvService;
    private CountDownLatch countDownLatch;

    public DoubleStatisticThreadPusher(List<Double> values, DoubleStatistic service, CountDownLatch countDownLatch) {
        this.values = values;
        this.minMaxAvService = service;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
        try {
            countDownLatch.await(MAX_WAIT_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return;
        }
        values.stream().forEach(minMaxAvService::offer);
    }
}
