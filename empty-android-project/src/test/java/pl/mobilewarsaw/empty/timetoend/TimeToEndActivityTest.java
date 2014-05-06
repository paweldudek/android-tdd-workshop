package pl.mobilewarsaw.empty.timetoend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class TimeToEndActivityTest {

    @Test
    public void shouldContainsValueTextView() {
        //given
        ActivityController activityController = Robolectric.buildActivity(TimeToEndActivity.class);
        TimeToEndActivity timeToEndActivity = (TimeToEndActivity) activityController.get();

        //when
        activityController.create();

        //then
        assertThat(timeToEndActivity.mValueTextView).isNotNull();
    }


    @Test
    public void shouldShowTimeToEndWhenOpenActivity() throws Exception {
        //given
        ActivityController activityController = Robolectric.buildActivity(TimeToEndActivity.class);
        TimeToEndActivity timeToEndActivity = (TimeToEndActivity) activityController.get();
        TimeToEndCalculator timeToEndCalculator = mock(TimeToEndCalculator.class);
        timeToEndActivity.mTimeToEndCalculator = timeToEndCalculator;

        Calendar calendar = prepareEndWorkshopTime();

        TimeToEnd timeToEnd = new TimeToEnd(1, 4, 6);
        when(timeToEndCalculator.getTimeToEnd(anyLong(), eq(calendar.getTimeInMillis()))).thenReturn(timeToEnd);

        //when
        activityController.create();

        //then
        assertThat(timeToEndActivity.mValueTextView.getText().toString())
                .isEqualTo("1 hour(s), 4 minute(s), 6 second(s)");
    }

    //2
    @Test
    public void shouldShowTimeToEndWhenClickOnButton() throws Exception {
        //given

        //when

        //then
    }

//    3
    @Test
    public void shouldShowErrorMessageWhenWorkshopAlreadyEnded() throws Exception {
        //given

        //when

        //then
    }

//    4
    @Test
    public void shouldShowDisableButtonWhenWorkshopAlreadyEnded() throws Exception {
        //given

        //when

        //then
    }

    private Calendar prepareEndWorkshopTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2014, 5, 10, 18, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
