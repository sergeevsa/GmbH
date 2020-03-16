package ru.sumstatistic;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import ru.numstatistic.DoubleStatistic;
import ru.numstatistic.DoubleStatisticImpl;

/**
 * base class for tests
 * contains common code
 */
public class DoubleStatisticImplBaseTest {

    protected DoubleStatistic minMaxAvService;

    @Before
    public void beforeMinMaxAvServiceImplBaseTest() {
        minMaxAvService = new DoubleStatisticImpl(10);
    }

    protected void assertAll(double largest, double smallest, double average) {
        MatcherAssert.assertThat(minMaxAvService, new DoubleStatisticMatcher(largest, smallest, average));
    }
}
