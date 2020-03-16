package ru.numstatistic;

/**
 * A interface for collecting statistics such as min, max and average.
 */

public interface DoubleStatistic {

    /**
     * add new num
     * @param number
     */
    void offer(double number);

    /**
     * return the smallest number it has encountered so far or null if no one number was offered
     * @return
     */
    Double getSmallest();

    /**
     * return the largest number it has encountered so far or null if no one number was offered
     * @return
     */
    Double getLargest();

    /**
     * return the average of all numbers it has encountered so far or null if no one number was offered
     * @return
     */
    Double getAverage();
}
