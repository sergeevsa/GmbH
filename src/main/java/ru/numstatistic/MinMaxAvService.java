package ru.numstatistic;

/**
 * A interface for collecting statistics such as min, max and average.
 */

public interface MinMaxAvService {

    /**
     * add new num
     * @param number
     */
    void offer(double number);

    /**
     * return the smallest number it has encountered so far
     * @return
     */
    Double getSmallest();

    /**
     * return the largest number it has encountered so far
     * @return
     */
    Double getLargest();

    /**
     * the average of all numbers it has encountered so far
     * @return
     */
    Double getAverage();
}
