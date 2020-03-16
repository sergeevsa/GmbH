package ru.numstatistic;

/**
 * A interface for collecting statistics such as min, max and average.
 */

public interface MinMaxAvService {

    void offer(double number);

    Double getSmallest();

    Double getLargest();

    Double getAverage();
}
