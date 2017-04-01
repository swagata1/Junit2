package com.mindtree.fsmmindtree.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.data.location.LocationObject;

import java.util.ArrayList;

public class LocationActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LocationActivity";
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap googleMap;
    private ArrayList<LocationObject> mLocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        setContentView(R.layout.activity_map);

        mLocationList = getIntent().getParcelableArrayListExtra("locationlist");

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        googleMap = fm.getMap();
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        addTestMarker();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    private void addTestMarker() {
        for (LocationObject locationObject : mLocationList) {
            MarkerOptions options = new MarkerOptions();

            // following four lines requires 'Google Maps Android API Utility Library'
            // https://developers.google.com/maps/documentation/android/utility/
            // I have used this to display the time as title for location markers
            // you can safely comment the following four lines but for this info
            IconGenerator iconFactory = new IconGenerator(this);
            iconFactory.setStyle(IconGenerator.STYLE_RED);
            iconFactory.setTextAppearance(R.style.iconGenText);
            options.icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(locationObject.getMarkerText())));
            options.anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

            LatLng currentLatLng = new LatLng(locationObject.getLatitude(), locationObject.getLongitude());
            options.position(currentLatLng);

            Marker mapMarker = googleMap.addMarker(options);

            mapMarker.setTitle(locationObject.getMarkerText());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
                    14));
        }
    }
}
