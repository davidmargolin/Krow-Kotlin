package com.toddev.krow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    public GoogleMap mMap;
    MapView mapView;
    View rootView;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference workref = db.collection("Workspaces");
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
        // get the mapview from layout based on id map
        mapView = rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        //set it to respond to onMapReady below
        mapView.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.getActivity(), R.raw.style_json));
        workref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (Workplace place : task.getResult().toObjects(Workplace.class)) {
                        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(place.getLatitude(),place.getLongitude())).title(place.getName()));
                        marker.setTag(place);
                    }
                } else {
                    Log.d("Error", "Error getting documents: ", task.getException());
                }
            }
        });
        //create a location for nyc, set map location to make nyc the center and zoom to city level
        final LatLng nyc = new LatLng(40.7128, -74.006);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nyc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

        //when a marker is clicked:
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {
                getFragmentManager().popBackStackImmediate();
                Fragment newFragment = new WorkSpaceInfoFragment().WorkSpaceWithInfo((Workplace)marker.getTag(), googleMap);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.bottom_sheet, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
                return true;
            }
        });



        SearchView searchbar =getActivity().findViewById(R.id.search);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    workref.whereEqualTo("name_lower", query.toLowerCase()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                getFragmentManager().popBackStackImmediate();
                                mMap.clear();
                                for (Workplace place : task.getResult().toObjects(Workplace.class)) {
                                    Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(place.getLatitude(), place.getLongitude())).title(place.getName()));
                                    marker.setTag(place);
                                }
                                getFragmentManager().popBackStackImmediate();
                                Fragment newFragment = new WorkSpaceListFragment().withList(task.getResult().toObjects(Workplace.class));
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.bottom_sheet, newFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(nyc));
                                mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

                            } else {
                                Log.d("Error", "Error getting documents: ", task.getException());
                            }
                        }
                    });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void setMarker(Workplace workplace){
        GoogleMap googleMap = mMap;
        if (mMap !=null){
            getFragmentManager().popBackStackImmediate();
            Fragment newFragment = new WorkSpaceInfoFragment().WorkSpaceWithInfo(workplace, googleMap);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.bottom_sheet, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(workplace.getLatitude(), workplace.getLongitude())));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        }
        else{
            Toast.makeText(getActivity(), "Map not ready", Toast.LENGTH_LONG).show();
        }
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