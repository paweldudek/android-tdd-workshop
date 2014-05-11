package pl.mobilewarsaw.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class HabitsAdapter extends CursorAdapter {

    public static final String COLUMN_TEXT = "text";

    public HabitsAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return new TextView(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view;
        textView.setText(cursor.getString(cursor.getColumnIndex(COLUMN_TEXT)));
    }
}
