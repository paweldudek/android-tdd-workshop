package pl.mobilewarsaw.empty.timetoend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TimeToEndActivityTest {

    @Test
    public void exampleTest() {
        Robolectric.buildActivity(TimeToEndActivity.class).create().get();
        assertThat(true).isTrue();
    }
}
