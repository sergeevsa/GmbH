package ru.sumstatistic;

import org.junit.Test;
import ru.numstatistic.DoubleStatisticImpl;

/**
 * tests for {@link java.math.RoundingMode} and {@link DoubleStatisticImpl#scale}
 */
public class DoubleStatisticRoundingTests extends DoubleStatisticImplBaseTest {

    @Test
    public void testRoundingModeUp() {
        minMaxAvService = new DoubleStatisticImpl(4);
        minMaxAvService.offer(1);
        for (int i = 0 ; i < 6 ; i++) {
            minMaxAvService.offer(0);
        }
        assertAll(1, 0, 0.1429);
    }

    @Test
    public void testRoundingModeDown() {
        minMaxAvService = new DoubleStatisticImpl(7);
        minMaxAvService.offer(1);
        for (int i = 0 ; i < 6 ; i++) {
            minMaxAvService.offer(0);
        }
        assertAll(1, 0, 0.1428571);
    }
}
