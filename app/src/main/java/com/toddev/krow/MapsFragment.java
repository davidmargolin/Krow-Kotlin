package com.toddev.krow;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private GoogleMap mMap;
    MapView mapView;
    ArrayList<Workplace> workarray = new ArrayList<Workplace>();
    View rootView;
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView sheetTextName;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference workref = database.getReference("workspaces");
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
        sheetTextName = rootView.findViewById(R.id.name);
        // get the mapview from layout based on id map
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        //set it to respond to onMapReady below
        mapView.getMapAsync(this);
        View bottomSheet = rootView.findViewById( R.id.bottom_sheet );
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(300);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.e(workref.getDatabase().toString(),"");
        workref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("child was", "added");
                Workplace place = dataSnapshot.getValue(Workplace.class);
                mMap.addMarker(new MarkerOptions().position(place.getLocation()).title(place.getName()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
                sheetTextName.setText(marker.getTitle());
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