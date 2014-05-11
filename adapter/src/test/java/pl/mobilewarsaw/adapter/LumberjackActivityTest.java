package pl.mobilewarsaw.adapter;

import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import pl.mobilewarsaw.adapter.shadow.MyShadowCursorAdapter;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {MyShadowCursorAdapter.class})
public class LumberjackActivityTest {
    private ActivityController<LumberjackActivity> activityController;
    private LumberjackActivity habitsActivity;
    private ListView listView;

    /* Requirements
    1. Show a list of lumberjack habits.
     */

    @Before
    public void setUp() throws Exception {
        activityController = Robolectric.buildActivity(LumberjackActivity.class)
                .create()
                .visible();
        habitsActivity = activityController.get();
        listView = habitsActivity.getListView();
    }

    @Test
    public void shouldContainAList() throws Exception {
        // then
        assertThat(listView).isInstanceOf(ListView.class);
    }

    @Test
    public void shouldUseHabitsAdapter() throws Exception {
        // then
        assertThat(listView.getAdapter()).isInstanceOf(HabitsAdapter.class);
    }

    @Test
    public void shouldContainLumberjackHabitsOnList() throws Exception {
        // given
        String[] expectedHabits = new String[]{"He's OK", "Sleeps all night", "Works all day"};

        // then
        assertThat(listView.getChildCount()).isEqualTo(expectedHabits.length);
        for (int index = 0; index < listView.getChildCount(); ++index) {
            TextView textView = (TextView) listView.getChildAt(index);
            assertThat(textView.getText()).isEqualTo(expectedHabits[index]);
        }
    }

}
