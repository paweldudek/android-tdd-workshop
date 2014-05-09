package pl.mobilewarsaw.kata;

import android.app.Activity;
import android.os.Bundle;

import pl.mobilewarsaw.kata.R;

public class KataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata);
    }
}
