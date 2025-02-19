package com.example.laba5_3;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoActivity extends Activity {

    TextView tvOut;
    TextView tvLon;
    TextView tvLat;
    TextView position;
    Geocoder geocoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
        geocoder = new Geocoder(this, Locale.getDefault());

        tvOut = (TextView)findViewById(R.id.textView1);
        tvLon = (TextView)findViewById(R.id.longitude);
        tvLat = (TextView)findViewById(R.id.latitude);
        position = (TextView)findViewById(R.id.position);
        //Получаем сервис
        LocationManager mlocManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new LocationListener(){
            public void onLocationChanged(Location location) {

                //Called when a new location is found by the network location provider.
                tvLat.setText(" "+location.getLatitude());
                tvLon.setText(" "+location.getLongitude());

                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    Address address = addressList.get(0);
                    position.setText("" + address.getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}

        };

        //Подписываемся на изменения в показаниях датчика


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    0
            );

        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                mlocListener);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    0
            );

        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                mlocListener);
        //Если gps включен, то ... , иначе вывести "GPS is not turned on..."
        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut.setText("GPS is turned on...");

        } else {
            tvOut.setText("GPS is not turned on...");
        }
    }
}
