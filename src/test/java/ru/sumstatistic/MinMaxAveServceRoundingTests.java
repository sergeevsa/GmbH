package ru.sumstatistic;

import org.junit.Test;
import ru.numstatistic.MinMaxAvServiceImpl;

/**
 * tests for {@link java.math.RoundingMode} and {@link MinMaxAvServiceImpl#scale}
 */
public class MinMaxAveServceRoundingTests extends MinMaxAvServiceImplBaseTest {

    @Test
    public void testRoundingModeUp() {
        minMaxAvService = new MinMaxAvServiceImpl(4);
        minMaxAvService.offer(1);
        for (int i = 0 ; i < 6 ; i++) {
            minMaxAvService.offer(0);
        }
        assertAll(1, 0, 0.1429);
    }

    @Test
    public void testRoundingModeDown() {
        minMaxAvService = new MinMaxAvServiceImpl(7);
        minMaxAvService.offer(1);
        for (int i = 0 ; i < 6 ; i++) {
            minMaxAvService.offer(0);
        }
        assertAll(1, 0, 0.1428571);
    }
}
