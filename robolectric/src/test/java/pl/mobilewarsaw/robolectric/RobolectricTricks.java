package pl.mobilewarsaw.robolectric;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Debug;
import android.telephony.SmsManager;
import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLocationManager;
import org.robolectric.shadows.ShadowSmsManager;
import pl.mobilewarsaw.robolectric.customshadows.ShadowDebug;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowDebug.class})
public class RobolectricTricks {

    // ---------------------------------------
    // Custom Shadow trick
    @Test
    public void testShadowDebug() {
        assert !Debug.isDebuggerConnected();
    }


    // ---------------------------------------
    // Hidden Shared preferences
    @Before
    public void before() {
        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences("you_custom_pref_name", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("testId", "12345").commit();
    }

    @Test
    public void testSharedPreferences() {
        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences("you_custom_pref_name", Context.MODE_PRIVATE);

        assertEquals(sharedPreferences.getString("testId", ""), "12345");
    }


    // ---------------------------------------
    // Location
    @Test
    public void shouldReturnTheLatestLocation() {
        // given
        // setup location manager
        LocationManager locationManager = (LocationManager)
                Robolectric.application.getSystemService(Context.LOCATION_SERVICE);
        ShadowLocationManager shadowLocationManager = Robolectric.shadowOf(locationManager);
        Location expectedLocation = location(LocationManager.NETWORK_PROVIDER, 12.0, 20.0);


        // and create the activity
        SecondActivity secondActivity = Robolectric.buildActivity(SecondActivity.class).create().get();

        // when
        shadowLocationManager.simulateLocation(expectedLocation);

        // then
        Location actualLocation = secondActivity.latestLocation;

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testLatestLocationTextView() {
        // given
        // setup location manager
        LocationManager locationManager = (LocationManager)
                Robolectric.application.getSystemService(Context.LOCATION_SERVICE);
        ShadowLocationManager shadowLocationManager = Robolectric.shadowOf(locationManager);
        Location expectedLocation = location(LocationManager.NETWORK_PROVIDER, 12.0, 20.0);


        // and create the activity
        SecondActivity secondActivity = Robolectric.buildActivity(SecondActivity.class).create().get();

        // when
        shadowLocationManager.simulateLocation(expectedLocation);

        // then
        assertEquals("lat: 12.000000\nlon: 20.000000", secondActivity.locationText.getText());
    }

    private Location location(String provider, double latitude, double longitude) {
        Location location = new Location(provider);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setTime(System.currentTimeMillis());
        return location;
    }


    // ---------------------------------------
    // Sending location via SMS
    @Test
    public void shouldShowSendSMSButtonOnLocationChange() {
        // given
        // setup location manager
        LocationManager locationManager = (LocationManager)
                Robolectric.application.getSystemService(Context.LOCATION_SERVICE);
        ShadowLocationManager shadowLocationManager = Robolectric.shadowOf(locationManager);
        Location expectedLocation = location(LocationManager.NETWORK_PROVIDER, 12.0, 20.0);


        // and create the activity
        SecondActivity secondActivity = Robolectric.buildActivity(SecondActivity.class).create().get();

        // check if button is not visible
        assert secondActivity.shareLocationBtn.getVisibility() == View.GONE;

        // when
        shadowLocationManager.simulateLocation(expectedLocation);

        // then
        assertEquals(secondActivity.shareLocationBtn.getVisibility(), View.VISIBLE);
    }

    @Test
    public void shouldSendSMSWithLocation() {
        // given
        // setup location manager
        LocationManager locationManager = (LocationManager)
                Robolectric.application.getSystemService(Context.LOCATION_SERVICE);
        ShadowLocationManager shadowLocationManager = Robolectric.shadowOf(locationManager);
        Location expectedLocation = location(LocationManager.NETWORK_PROVIDER, 12.0, 20.0);


        // and create the activity
        SecondActivity secondActivity = Robolectric.buildActivity(SecondActivity.class).create().get();

        // and
        shadowLocationManager.simulateLocation(expectedLocation);

        // when
        secondActivity.shareLocationBtn.performClick();

        // then
        ShadowSmsManager shadowSmsManager = Robolectric.shadowOf(SmsManager.getDefault());
        ShadowSmsManager.TextSmsParams lastSentTextMessageParams = shadowSmsManager.getLastSentTextMessageParams();

        assertEquals(SecondActivity.YOUR_PHONE_NUMBER, lastSentTextMessageParams.getDestinationAddress());
        assertEquals("lat: 12.000000\nlon: 20.000000", lastSentTextMessageParams.getText());
    }
}
