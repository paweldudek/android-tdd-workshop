package pl.mobilewarsaw.adapter;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;

public class LumberjackActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new HabitsAdapter(this, prepareCursor()));
    }

    private Cursor prepareCursor() {
        String[] names = new String[]{BaseColumns._ID, HabitsAdapter.COLUMN_TEXT};
        MatrixCursor cursor = new MatrixCursor(names);
        cursor.addRow(new Object[]{1, "He's OK"});
        cursor.addRow(new Object[]{2, "Sleeps all night"});
        cursor.addRow(new Object[]{3, "Works all day"});
        return cursor;
    }

}
