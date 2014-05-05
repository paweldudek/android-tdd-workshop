package pl.mobilewarsaw.robolectric;

import android.content.Intent;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowTextView;

import static org.junit.Assert.assertEquals;


/**
 * Created by rudy on 05.05.14.
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void checkIfActivityButtonHasListener() {
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();

        ShadowTextView shadowButton = Robolectric.shadowOf(mainActivity.activityButton);

        assert shadowButton.getOnClickListener() != null;
    }

    @Test
    public void checkIfActivityListenerStartsSecondActivity() {
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();

        mainActivity.activityButton.performClick();

        ShadowActivity shadowActivity = Robolectric.shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);
        assertEquals(SecondActivity.class.getName(), shadowIntent.getComponent().getClassName());
    }

}
