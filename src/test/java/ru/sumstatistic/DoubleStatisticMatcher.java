package ru.sumstatistic;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import ru.numstatistic.DoubleStatistic;

public class MinMaxAveServiceMatcher extends TypeSafeMatcher<DoubleStatistic> {

    private double largest;
    private double smallest;
    private double average;

    public MinMaxAveServiceMatcher(double largest, double smallest, double average) {
        this.largest = largest;
        this.smallest = smallest;
        this.average = average;
    }

    @Override
    protected boolean matchesSafely(DoubleStatistic item) {
        return largest == item.getLargest() &&
                smallest == item.getSmallest() &&
                average == item.getAverage();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format("largest: %s, smallest: %s, average: %s", largest, smallest, average));
    }

    @Override
    protected void describeMismatchSafely(DoubleStatistic item, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("largest: %s, smallest: %s, average: %s", item.getLargest(),
                item.getSmallest(), item.getAverage()));
    }
}
