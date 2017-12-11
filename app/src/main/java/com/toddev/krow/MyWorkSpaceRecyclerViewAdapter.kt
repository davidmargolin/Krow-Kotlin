package com.toddev.krow

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class MyWorkSpaceRecyclerViewAdapter(private val workplaces: List<Workplace>, private var context: Context) : RecyclerView.Adapter<MyWorkSpaceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.workspace_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mNameView.text = workplaces[position].name
        holder.mDescView.text = workplaces[position].description
        holder.sheetAddress.text = workplaces[position].address
        if (workplaces[position].numrated!! > 0) {
            holder.mRatingCountView.text = "${workplaces[position].numrated} Ratings"
            holder.mRatingView.text = "Rating: ${workplaces[position].rating}"
        } else {
            holder.mRatingView.text = "Not Yet Rated"
        }
        holder.mView.setOnClickListener {
            (context as MainActivity).setMarker(workplaces[position])
        }
    }

    override fun getItemCount(): Int {
        return workplaces.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val mRatingView: TextView = mView.findViewById<TextView>(R.id.rating)
        val mNameView: TextView = mView.findViewById<TextView>(R.id.name)
        val mDescView: TextView = mView.findViewById<TextView>(R.id.desc)
        val mRatingCountView: TextView = mView.findViewById<TextView>(R.id.numrated)
        val sheetAddress: TextView = mView.findViewById<TextView>(R.id.address)

    }
}
