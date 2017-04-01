package com.mindtree.fsmmindtree.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.location.LocationObject;
import com.mindtree.fsmmindtree.data.task.Task;
import com.mindtree.fsmmindtree.data.task.TaskResponseObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class TaskMapFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LocationActivity";
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap googleMap;
    private ArrayList<LocationObject> mLocationList;

    private EventBus bus = EventBus.getDefault();
    private ArrayList<Task> mTaskList;

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onEvent(TaskResponseObject taskResponseObject) {
        if (taskResponseObject != null) {
            mLocationList = new ArrayList<LocationObject>();

            showTasks((ArrayList<Task>) taskResponseObject.getTasks());
        }
    }

    private void showTasks(ArrayList<Task> taskList) {
        if (taskList != null) {
            //  get location data
            for (Task task : taskList) {
                LocationObject locationObject = new LocationObject(task.getLatitude(), task.getLongitude(), task.getCustomername());
                mLocationList.add(locationObject);
            }
        }

        googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.getUiSettings().setZoomControlsEnabled(true);


        addMarker();
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

    public void setLocationList(ArrayList<LocationObject> locationList) {
        mLocationList = locationList;
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

    private void addMarker() {
        final HashMap<String, Integer> mMarkers = new HashMap<String, Integer>();

        int i = 0;
        for (LocationObject locationObject : mLocationList) {
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

            final Marker mapMarker = googleMap.addMarker(options);
            mMarkers.put(mapMarker.getId(), i);

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    RuntimeData.sSelectedTask = RuntimeData.sTaskList.get(mMarkers.get(marker.getId()));

                    googleMap.setInfoWindowAdapter(new MapInfoWindowAdapater());

//                    StringBuffer buffer = new StringBuffer();
//                    buffer.append(RuntimeData.sSelectedTask.getTitle());
//                    buffer.append("\n");
//                    buffer.append(RuntimeData.sSelectedTask.getDescription());
//                    buffer.append("\n");
//
//                    Integer p = RuntimeData.sSelectedTask.getPriority();
//                    String priority = "MEDIUM";
//
//                    if (p != null) {
//                        switch (p) {
//                            case 1:
//                                priority = "HIGH";
//                                break;
//
//                            case 2:
//                                priority = "MEDIUM";
//                                break;
//
//                            case 3:
//                                priority = "NORMAL";
//                                break;
//
//                            default:
//                                priority = "MEDIUM";
//                                break;
//                        }
//                    } else {
//                        priority = "MEDIUM";
//                    }
//                    buffer.append(priority);
//
//                    mapMarker.setSnippet(buffer.toString());
                    mapMarker.showInfoWindow();

                    return true;
                }
            });

            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
                    startActivityForResult(intent, 0);
                }
            });

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

    private class MapInfoWindowAdapater implements InfoWindowAdapter {
        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View v = getActivity().getLayoutInflater().inflate(R.layout.map_info_window_layout, null);

            TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            TextView txtDesc = (TextView) v.findViewById(R.id.txtDesc);
            TextView txtPriority = (TextView) v.findViewById(R.id.txtPriority);

            txtTitle.setTypeface(AppUtils.getCustomFont(getActivity(), AppConstants.Fonts.FONT_BOLD));
            txtDesc.setTypeface(AppUtils.getCustomFont(getActivity(), AppConstants.Fonts.FONT_NORMAL));
            txtPriority.setTypeface(AppUtils.getCustomFont(getActivity(), AppConstants.Fonts.FONT_BOLD));

            txtTitle.setText(RuntimeData.sSelectedTask.getTitle());
            txtDesc.setText(RuntimeData.sSelectedTask.getDescription());

            Integer p = RuntimeData.sSelectedTask.getPriority();
            String priority = "MEDIUM";

            if (p != null) {
                switch (p) {
                    case 1:
                        priority = "HIGH";
                        txtPriority.setTextColor(getActivity().getResources().getColor(R.color.color_priority));
                        break;

                    case 2:
                        priority = "MEDIUM";
                        txtPriority.setTextColor(getActivity().getResources().getColor(R.color.orange));
                        break;

                    case 3:
                        priority = "NORMAL";
                        txtPriority.setTextColor(getActivity().getResources().getColor(R.color.color_dark_grey));
                        break;

                    default:
                        priority = "MEDIUM";
                        txtPriority.setTextColor(getActivity().getResources().getColor(R.color.orange));
                        break;
                }
            } else {
                priority = "MEDIUM";
            }

            txtPriority.setText(priority);

            // Returning the view containing InfoWindow contents
            return v;
        }
    }
}
