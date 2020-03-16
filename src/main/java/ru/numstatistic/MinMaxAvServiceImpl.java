package ru.numstatistic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MinMaxAvServiceImpl implements MinMaxAvService {

    private BigDecimal sum;
    private BigDecimal count;
    private Double smallest;
    private Double largest;
    private int scale;

    public MinMaxAvServiceImpl(int scale) {
        this.scale = scale;
    }

    @Override
    public synchronized void offer(double number) {
        if (isAnyElementsOffered()) {
            sum = sum.add(BigDecimal.valueOf(number));
            smallest = Math.min(smallest, number);
            largest = Math.max(largest, number);
            count = count.add(BigDecimal.ONE);
        } else {
            sum = BigDecimal.valueOf(number);
            smallest = number;
            largest = number;
            count = BigDecimal.ONE;
        }
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
        return isAnyElementsOffered() ? sum.divide(count, scale, RoundingMode.HALF_UP).doubleValue() : null;
    }

    private boolean isAnyElementsOffered() {
        return count != null;
    }
}
