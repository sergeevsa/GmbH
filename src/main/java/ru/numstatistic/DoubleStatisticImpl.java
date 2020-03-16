package ru.numstatistic;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A object for collecting statistics such as min, max and average.
 * This class do same things like {@link java.util.DoubleSummaryStatistics}, but it's threadsafe and
 * return NULL instead of 0 when no one number was offered. Also this class saves the sum of all numbers
 * in {@link BigDecimal}, so there will be no overflow
 */
public class DoubleStatisticImpl implements DoubleStatistic {

    private volatile BigDecimal sum;
    private volatile long count = 0;
    private volatile Double smallest;
    private volatile Double largest;
    private final int scale;

    /**
     * @param scale - a number of simbols after comma, {@link RoundingMode#HALF_UP}
     */
    public DoubleStatisticImpl(int scale) {
        this.scale = scale;
    }

    @Override
    public synchronized void offer(double number) {
        if (isAnyElementsOffered()) {
            sum = sum.add(BigDecimal.valueOf(number).setScale(scale));
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
        return isAnyElementsOffered() ? sum.divide(BigDecimal.valueOf(count, scale), scale, RoundingMode.HALF_UP).doubleValue() : null;
    }

    private boolean isAnyElementsOffered() {
        return count != 0;
    }
}
