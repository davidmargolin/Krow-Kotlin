package com.toddev.krow;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private GoogleMap mMap;
    MapView mapView;
    ArrayList<Workplace> workarray = new ArrayList<Workplace>();
    View rootView;

    public MapsFragment() {
    }


    public static MapsFragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString("somedata", "something");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //open res -> layout -> activity maps
        rootView = inflater.inflate(R.layout.activity_maps, container, false);

        //create 2 workplace objects
        Workplace Work1 = new Workplace("Round K", new LatLng(40.718896,-73.990929));
        Workplace Work2 = new Workplace("Round X", new LatLng(40.668896,-73.940929));

        //add them to an array
        workarray.add(Work1);
        workarray.add(Work2);

        // get the mapview from layout based on id map
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        //set it to respond to onMapReady below
        mapView.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //create markers for items in our array
        for (Workplace place: workarray) {
            mMap.addMarker(new MarkerOptions().position(place.getLocation()).title(place.getName()));
        }

        //create a location for nyc, set map location to make nyc the center and zoom to city level
        LatLng nyc = new LatLng(40.7128, -74.006);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nyc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

        //when a marker is clicked:
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {
                //create a snackbar displaying the name
                Snackbar.make(rootView.findViewById(R.id.linlay), marker.getTitle(), Snackbar.LENGTH_LONG).show();
                //move the camera to the marker location and zoom to street level
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
                return true;
            }
        });



    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}