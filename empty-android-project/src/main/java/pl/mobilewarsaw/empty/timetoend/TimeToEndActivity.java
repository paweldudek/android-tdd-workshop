package pl.mobilewarsaw.empty.timetoend;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import pl.mobilewarsaw.empty.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeToEndActivity extends Activity {

    public TextView mValueTextView;
    protected TimeToEndCalculator mTimeToEndCalculator = new TimeToEndCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_to_end);
        mValueTextView = (TextView) findViewById(R.id.valueTextView);

        long endTime = extractEndWorkshopTime();

        TimeToEnd timeToEnd = mTimeToEndCalculator.getTimeToEnd(System.currentTimeMillis(), endTime);
        String text = timeToEnd.getHours() + " hour(s), "
                + timeToEnd.getMinutes() + " minute(s), "
                + timeToEnd.getSeconds() + " second(s)";
        mValueTextView.setText(text);
    }

    protected long extractEndWorkshopTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2014, 5, 10, 18, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

}
