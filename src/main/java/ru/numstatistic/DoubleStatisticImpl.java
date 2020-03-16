package ru.numstatistic;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A state object for collecting statistics such as min, max and average.
 * Sum of all numbers saves in {@link BigDecimal}, so there will be no overflow
 */
public class DoubleStatisticImpl implements DoubleStatistic {

    private volatile BigDecimal sum;
    private volatile long count = 0;
    private volatile Double smallest;
    private volatile Double largest;
    private final int scale;

    /**
     * @param scale - a number of simbols after comma, {@link RoundingMode#HALF_UP}
     * for calculating average
     */
    public DoubleStatisticImpl(int scale) {
        this.scale = scale;
    }

    @Override
    public synchronized void offer(double number) {
        if (isAnyElementsOffered()) {
            sum = sum.add(BigDecimal.valueOf(number));
            smallest = Math.min(smallest, number);
            largest = Math.max(largest, number);
        } else {
            sum = BigDecimal.valueOf(number);
            smallest = number;
            largest = number;
        }
        ++count;
    }

    @Override
    public Double getSmallest() {
        return smallest;
    }

    @Override
    public Double getLargest() {
        return largest;
    }

    @Override
    public synchronized Double getAverage() {
        return isAnyElementsOffered() ? sum.divide(BigDecimal.valueOf(count), scale, RoundingMode.HALF_UP).doubleValue() : null;
    }

    private boolean isAnyElementsOffered() {
        return count != 0;
    }
}
