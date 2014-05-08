package pl.mobilewarsaw.robolectric;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rudy on 05.05.14.
 */
public class SecondActivity extends Activity implements LocationListener {

    // replace it to your real number to get the location :)
    static final String YOUR_PHONE_NUMBER = "555-334455";

    Location latestLocation;

    TextView locationText;

    Button shareLocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        locationText = (TextView) findViewById(R.id.location_text);

        shareLocationBtn = (Button) findViewById(R.id.send_sms_with_location);
        shareLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(YOUR_PHONE_NUMBER, null, String.format("lat: %f\nlon: %f", latestLocation.getLatitude(), latestLocation.getLongitude()), null, null);
            }
        });

        initLocationListener();
    }

    private void initLocationListener() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        List<String> allProviders = locationManager.getAllProviders();
        for (String provider : allProviders) {
            locationManager.requestLocationUpdates(provider, 0, 0, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        shareLocationBtn.setVisibility(View.VISIBLE);

        latestLocation = location;

        locationText.setText(String.format("lat: %f\nlon: %f", location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }
}
