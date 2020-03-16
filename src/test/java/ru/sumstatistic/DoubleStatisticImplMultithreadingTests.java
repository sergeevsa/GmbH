package ru.sumstatistic;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * test for multithreading numbers offering
 */
public class DoubleStatisticImplMultithreadingTests extends DoubleStatisticImplBaseTest {

    @Test
    public void testThreadSafe() throws InterruptedException {
        final int threadsCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadsCount);
        List<Thread> threads = new LinkedList<>();
        //run threadsCount threads. Every thread will offer numbers to DoubleStatisticImpl
        for (int i = 0; i < threadsCount; i++) {
            List<Double> values = new LinkedList<>();
            //this for circle generating numbers which thread will be send to offer
            //every thread have unique numbers
            for (int j = 1; j < 1_001; j++) {
                values.add((double) (i * 1_000 + j));
            }
            Thread t = new Thread(new DoubleStatisticThreadPusher(values, minMaxAvService, countDownLatch));
            t.start();
            threads.add(t);
        }
        //wait every thread ends
        for (Thread thread : threads) {
            thread.join();
        }
        assertAll(10_000, 1, 5_000.5);
    }
}
