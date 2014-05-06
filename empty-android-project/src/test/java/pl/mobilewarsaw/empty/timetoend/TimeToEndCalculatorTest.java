package pl.mobilewarsaw.empty.timetoend;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;


public class TimeToEndCalculatorTest {

    @Test
    public void shouldNameReturnTimeToEndInThePastWhenWorkshopAlreadyEnded() throws Exception {
        //given
        TimeToEndCalculator timeToEndCalculator = new TimeToEndCalculator();
        long start = System.currentTimeMillis();
        long end = start - 1000l;

        //when
        TimeToEnd timeToEnd = timeToEndCalculator.getTimeToEnd(start, end);

        //then
        assertThat(timeToEnd.isInThePast()).isTrue();
    }

    @Test
    public void shouldNameReturnTimeToEndNotInThePastWhenWorkshopNotEndedYet() throws Exception {
        //given
        TimeToEndCalculator timeToEndCalculator = new TimeToEndCalculator();
        long start = System.currentTimeMillis();
        long end = start + 60 * 1000l;

        //when
        TimeToEnd timeToEnd = timeToEndCalculator.getTimeToEnd(start, end);

        //then
        assertThat(timeToEnd.isInThePast()).isFalse();
    }

    @Test
    public void shouldReturnValidTimeToEnd() throws Exception {
        //given
        TimeToEndCalculator timeToEndCalculator = new TimeToEndCalculator();
        long start = System.currentTimeMillis();
        int hours = 2;
        int minutes = 15;
        int seconds = 37;
        long end = start
                + hours * TimeToEndCalculator.MILIS_IN_HOUR
                + minutes * TimeToEndCalculator.MILIS_IN_MINUTE
                + seconds * TimeToEndCalculator.MILIS_IN_SECOND;

        //when
        TimeToEnd timeToEnd = timeToEndCalculator.getTimeToEnd(start, end);

        //then
        assertThat(timeToEnd.getHours()).isEqualTo(hours);
        assertThat(timeToEnd.getMinutes()).isEqualTo(minutes);
        assertThat(timeToEnd.getSeconds()).isEqualTo(seconds);
    }
}
