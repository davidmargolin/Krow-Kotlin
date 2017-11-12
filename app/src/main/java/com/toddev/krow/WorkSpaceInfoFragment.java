package com.toddev.krow;


import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;

public class WorkSpaceInfoFragment extends Fragment {

    Workplace workplace;
    TextView sheetTextDesc;
    private GoogleMap googleMap;
    private BottomSheetBehavior mBottomSheetBehavior;

    public WorkSpaceInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public WorkSpaceInfoFragment WorkSpaceWithInfo(Workplace place, GoogleMap map){
        WorkSpaceInfoFragment fragment = new WorkSpaceInfoFragment();
        fragment.setObject(place, map);
        return fragment;
    }

    public void setObject(Workplace place, GoogleMap map){
        workplace = place;
        googleMap = map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment'
        View rootView = inflater.inflate(R.layout.fragment_workspace, container, false);
        TextView sheetTextName;
        TextView sheetTextRating;
        TextView sheetNumRated;
        TextView sheetAddress;


        Button wifiB = rootView.findViewById(R.id.wifi);
        Button bathroomB = rootView.findViewById(R.id.bathroom);
        Button foodB = rootView.findViewById(R.id.food);
        Button noiseB = rootView.findViewById(R.id.volume);
        Button seatingB = rootView.findViewById(R.id.seats);
        Button powerB = rootView.findViewById(R.id.outlets);

        wifiB.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(getActivity().getApplicationContext(),Workplace.getWorkResource(workplace.getAmenities().getWifi())),null,null);
        wifiB.setText(workplace.getAmenities().getWifi());

        bathroomB.setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(getActivity().getApplicationContext(),Workplace.getWorkResource(workplace.getAmenities().getBathrooms())),null,null);
        bathroomB.setText(workplace.getAmenities().getBathrooms());

        foodB.setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(getActivity().getApplicationContext(),Workplace.getWorkResource(workplace.getAmenities().getFood())),null,null);
        foodB.setText(workplace.getAmenities().getFood());

        noiseB.setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(getActivity().getApplicationContext(),Workplace.getWorkResource(workplace.getAmenities().getNoise())),null,null);
        noiseB.setText(workplace.getAmenities().getNoise());

        seatingB.setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(getActivity().getApplicationContext(),Workplace.getWorkResource(workplace.getAmenities().getSpace())),null,null);
        seatingB.setText(workplace.getAmenities().getSpace());

        powerB.setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(getActivity().getApplicationContext(),Workplace.getWorkResource(workplace.getAmenities().getOutlets())),null,null);
        powerB.setText(workplace.getAmenities().getOutlets());

        sheetTextName = rootView.findViewById(R.id.name);
        sheetTextRating = rootView.findViewById(R.id.rating);

        sheetTextDesc = rootView.findViewById(R.id.desc);
        sheetNumRated = rootView.findViewById(R.id.numrated);
        sheetAddress = rootView.findViewById(R.id.address);
        sheetAddress.setText(workplace.getAddress());
        sheetTextDesc.setText(workplace.getDescription());
        if (workplace.getNumrated()>0) {
            sheetNumRated.setText(Integer.toString(workplace.getNumrated()) + " Reviews");
            sheetTextRating.setText("Rating: "+workplace.getRating());
        }else {
            sheetTextRating.setText("Not Yet Rated");
        }
        sheetTextName.setText(workplace.getName());


        View bottomSheet = container.findViewById( R.id.bottom_sheet );
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(375);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        final CardView infocard = rootView.findViewById(R.id.infocard);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            double offset = 0.8;
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED){
                    sheetTextDesc.setMaxLines(2);
                }else{
                    sheetTextDesc.setMaxLines(500);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset>offset){
                    Projection projection = googleMap.getProjection();
                    LatLng markerPosition = new LatLng(workplace.getLatitude(), workplace.getLongitude());
                    Point markerPoint = projection.toScreenLocation(markerPosition);
                    Point targetPoint = new Point(markerPoint.x, markerPoint.y + infocard.getHeight()-50);
                    LatLng targetPosition = projection.fromScreenLocation(targetPoint);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(targetPosition), 150, null);

                    offset = 1;
                }else if (slideOffset == 0){
                    offset = 0.8;
                }
            }
        });


        infocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                   sheetTextDesc.setMaxLines(500);
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                }else{
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        return rootView;


    }

}
