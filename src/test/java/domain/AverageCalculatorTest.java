package domain;

import org.junit.Test;

import static domain.AverageCalculator.roundedAverage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AverageCalculatorTest {
    
    private static final double A_NUMBER = 5.0;
    private static final double CURRENT_AVERAGE = 4.5;
    private static final int NUMBER_OF_ITEMS = 2;

    @Test
    public void average_is_rounded_to_closest_half() {
        Double newAverage = roundedAverage(A_NUMBER, CURRENT_AVERAGE, NUMBER_OF_ITEMS);

        assertThat(newAverage, is(4.5));
    }

}