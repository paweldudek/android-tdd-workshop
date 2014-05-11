package pl.mobilewarsaw.adapter.shadow;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowCursorAdapter;

@Implements(CursorAdapter.class)
public class MyShadowCursorAdapter extends ShadowCursorAdapter {
    @RealObject
    CursorAdapter realCursorAdapter;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (!mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }
        View view;
        if (convertView == null) {
            view = realCursorAdapter.newView(mContext, mCursor, parent);
        } else {
            view = convertView;
        }
        realCursorAdapter.bindView(view, mContext, mCursor);
        return view;
    }
}
