package com.toddev.krow;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyWorkSpaceRecyclerViewAdapter extends RecyclerView.Adapter<MyWorkSpaceRecyclerViewAdapter.ViewHolder> {

    private List<Workplace> workplaces;
    Context context;
    public MyWorkSpaceRecyclerViewAdapter(List<Workplace> places, Context context) {
        workplaces = places;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workspace_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mNameView.setText(workplaces.get(position).getName());
        holder.mDescView.setText(workplaces.get(position).getDescription());
        holder.sheetAddress.setText(workplaces.get(position).getAddress());
        if (workplaces.get(position).getNumrated()>0) {
            holder.mRatingCountView.setText(workplaces.get(position).getNumrated()+ " Ratings");
            holder.mRatingView.setText("Rating: "+workplaces.get(position).getRating());

        }else {
            holder.mRatingView.setText("Not Yet Rated");
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).setMarker(workplaces.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return workplaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mDescView;
        public final TextView mRatingView;
        public final TextView mRatingCountView;
        public final TextView sheetAddress;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRatingView = (TextView) view.findViewById(R.id.rating);
            mNameView = (TextView) view.findViewById(R.id.name);
            mDescView = (TextView) view.findViewById(R.id.desc);
            mRatingCountView = (TextView) view.findViewById(R.id.numrated);
            sheetAddress = (TextView) view.findViewById(R.id.address);


        }
    }
}
