package pl.mobilewarsaw.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class HabitsAdapterTest {

    private Context context = Robolectric.application.getApplicationContext();

    @Test
    public void shouldProvideNewViewWithTextView() throws Exception {
        // given
        HabitsAdapter adapter = new HabitsAdapter(context, null);

        // when
        View view = adapter.newView(context, null, null);

        // then
        assertThat(view).isInstanceOf(TextView.class);
    }

    @Test
    public void shouldBindViewWithTextFromCursor() throws Exception {
        // given
        Cursor cursor = mock(Cursor.class);
        given(cursor.getString(anyInt())).willReturn("habit");
        HabitsAdapter adapter = new HabitsAdapter(context, null);

        // when
        TextView view = (TextView) adapter.newView(context, null, null);
        adapter.bindView(view, context, cursor);

        // then
        assertThat(view.getText()).isEqualTo("habit");
    }
}