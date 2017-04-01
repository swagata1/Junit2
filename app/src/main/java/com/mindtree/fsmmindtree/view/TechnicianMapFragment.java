package com.mindtree.fsmmindtree.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.location.LocationObject;
import com.mindtree.fsmmindtree.data.technician.Result;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class TechnicianMapFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LocationActivity";
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap googleMap;
    private ArrayList<LocationObject> mLocationList;

    private EventBus bus = EventBus.getDefault();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            getActivity().finish();
        }

        View v = inflater.inflate(R.layout.tab_map, container,
                false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Register as a subscriber
        bus.register(this);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void onEvent(ArrayList <Result> list) {
        if(list != null) {
            mLocationList = new ArrayList<LocationObject>();

            for(Result location: list) {
                LocationObject l = new LocationObject(location.getLatitude(), location.getLongitude(), location.getUsername());

                mLocationList.add(l);
            }

            googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            addTestMarker();
        }
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
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, getActivity(), 0).show();
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
        final HashMap<String, Integer> mMarkers = new HashMap<String, Integer>();

        int i = 0;
        for (final LocationObject locationObject : mLocationList) {
            MarkerOptions options = new MarkerOptions();

            // following four lines requires 'Google Maps Android API Utility Library'
            // https://developers.google.com/maps/documentation/android/utility/
            // I have used this to display the time as title for location markers
            // you can safely comment the following four lines but for this info
            IconGenerator iconFactory = new IconGenerator(getActivity());
            iconFactory.setStyle(IconGenerator.STYLE_RED);
            iconFactory.setTextAppearance(R.style.iconGenText);
            options.icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(locationObject.getMarkerText())));
            options.anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

            LatLng currentLatLng = new LatLng(locationObject.getLatitude(), locationObject.getLongitude());
            options.position(currentLatLng);

            Marker mapMarker = googleMap.addMarker(options);
            mMarkers.put(mapMarker.getId(), i);

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    RuntimeData.sSelectedTechician = RuntimeData.sTechicianList.get(mMarkers.get(marker.getId()));

                    getActivity().setResult(getActivity().RESULT_OK);
                    getActivity().finish();

                    return true;
                }
            });

//            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//                @Override
//                public void onInfoWindowClick(Marker marker) {
//                    RuntimeData.sSelectedTechician = RuntimeData.sTechicianList.get(locationObject.g);

//                    getActivity().setResult(getActivity().RESULT_OK);
//                    getActivity().finish();
//                }
//            });

            mapMarker.setTitle(locationObject.getMarkerText());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
                    14));
            i++;
        }
    }

    @Override
    public void onDestroyView() {
        // Unregister
        bus.unregister(this);
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
