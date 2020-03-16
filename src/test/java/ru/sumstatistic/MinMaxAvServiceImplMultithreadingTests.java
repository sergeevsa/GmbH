package ru.sumstatistic;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MinMaxAvServiceImplMultithreadingTests extends MinMaxAvServiceImplBaseTest {

    @Test
    public void testThreadSafe() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            List<Double> values = new LinkedList<>();
            for (int j = 1; j < 11; j++) {
                values.add((double) (i * 10 + j));
            }
            Thread t = new Thread(new MinMaxAvServiceThreadPusher(values, minMaxAvService, countDownLatch));
            t.start();
            threads.add(t);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        assertAll(100, 1, 50.5);
    }
}
