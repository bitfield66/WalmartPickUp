package com.sample.walmartpickup;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Anil.Jain on 4/30/2016.
 */
public class MapActivity extends WalmartPickupBaseActivity implements LocationListener {
    private LinearLayout llMap;
    private TextView tvDirections;
    private GoogleMap googleMap;
    private LocationManager locationManager;

    @Override
    void onCreateSubView() {
        llMap = (LinearLayout) layoutInflater.inflate(R.layout.activity_map, null);
        llContent.addView(llMap, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        initializeViews();
        loadMap();
        initalizeLocation();
    }

    private void initializeViews() {

        tvDirections = (TextView) llMap.findViewById(R.id.tvDirections);
        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fgMaps)).getMap();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void loadMap() {

        if (googleMap != null) {
            googleMap.clear();
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            UiSettings mUiSettings;
            mUiSettings = googleMap.getUiSettings();
            mUiSettings.setCompassEnabled(true);
            mUiSettings.setMyLocationButtonEnabled(true);
            mUiSettings.setZoomControlsEnabled(true);
        } else
            Toast.makeText(MapActivity.this, "Your device doesn't support Google map v2. "
                    + "Kindly download the Google play services/Google Settings for google Play.", Toast.LENGTH_SHORT).show();


    }

    private void initalizeLocation() {


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        double latitude,longitude;
        Location location = null;
        // getting GPS status
        boolean isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        boolean isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        } else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            100,
                            1, this);
                    Log.d("activity", "LOC Network Enabled");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            Log.d("activity", "LOC by Network");
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                1000,
                                1, this);
                        Log.d("activity", "RLOC: GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                Log.d("activity", "RLOC: loc by GPS");
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }

                        }
                    }
                }
            }
        }
    }
    @Override
    public void onLocationChanged(Location location) {

        if (location != null)
        {
            googleMap.clear();
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    latLng, 15));
            Marker pos_Marker =  googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            pos_Marker.showInfoWindow();
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15),2000, null);

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
