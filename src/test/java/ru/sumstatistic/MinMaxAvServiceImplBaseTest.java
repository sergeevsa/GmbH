package ru.sumstatistic;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import ru.numstatistic.MinMaxAvService;
import ru.numstatistic.MinMaxAvServiceImpl;

public class MinMaxAvServiceImplBaseTest {

    protected MinMaxAvService minMaxAvService;

    @Before
    public void beforeMinMaxAvServiceImplBaseTest() {
        minMaxAvService = new MinMaxAvServiceImpl(10);
    }

    protected void assertAll(double largest, double smallest, double average) {
        MatcherAssert.assertThat(minMaxAvService, new MinMaxAveServiceMatcher(largest, smallest, average));
    }
}
