package ru.sumstatistic;

import org.junit.Assert;
import org.junit.Test;

public class MinMaxAvServiceImplTests extends MinMaxAvServiceImplBaseTest {

    @Test
    public void testNothingOffered() {
        Assert.assertNull(minMaxAvService.getAverage());
        Assert.assertNull(minMaxAvService.getLargest());
        Assert.assertNull(minMaxAvService.getSmallest());
    }

    @Test
    public void testWhiteScenario() {
        minMaxAvService.offer(1);
        assertAll(1, 1, 1);
        minMaxAvService.offer(3);
        assertAll(3, 1, 2);
        minMaxAvService.offer(100);
        assertAll(100, 1, 34.6666666667);
    }

    @Test
    public void testBigValues() {
        for (int i = 0; i < 10; i++) {
            minMaxAvService.offer(Double.MAX_VALUE);
            assertAll(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }

    @Test
    public void testBigAndSmallValue() {
        minMaxAvService.offer(Double.MAX_VALUE);
        assertAll(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        minMaxAvService.offer(0);
        assertAll(Double.MAX_VALUE, 0, Double.MAX_VALUE / 2);
        minMaxAvService.offer(-Double.MAX_VALUE);
        assertAll(Double.MAX_VALUE, -Double.MAX_VALUE, 0);
    }

    @Test
    public void testZero() {
        for (int i = 0; i < 5; i++) {
            minMaxAvService.offer(0);
            assertAll(0, 0, 0);
        }
        minMaxAvService.offer(Double.MAX_VALUE);
        assertAll(Double.MAX_VALUE, 0, 2.9961552247705263E307);
    }
}
