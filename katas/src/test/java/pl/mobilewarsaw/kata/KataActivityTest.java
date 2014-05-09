package pl.mobilewarsaw.kata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class KataActivityTest {

    @Test
    public void exampleTest() {
        Robolectric.buildActivity(KataActivity.class).create().get();
        assertThat(true).isTrue();
    }
}
