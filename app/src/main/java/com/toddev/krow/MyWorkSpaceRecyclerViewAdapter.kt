package com.toddev.krow

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.toddev.krow.databinding.WorkspaceListItemBinding
import android.databinding.DataBindingUtil

class MyWorkSpaceRecyclerViewAdapter(private val workplaces: List<Workplace>, private var context: Context) : RecyclerView.Adapter<MyWorkSpaceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: WorkspaceListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.workspace_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(workplaces[position])
        holder.mView.root.setOnClickListener {
            (context as MainActivity).setMarker(workplaces[position])
        }
    }

    override fun getItemCount(): Int {
        return workplaces.size
    }

    inner class ViewHolder(val mView: WorkspaceListItemBinding) : RecyclerView.ViewHolder(mView.root) {
        fun bind(workplace: Workplace){
            mView.setWorkplace(workplace)
            mView.executePendingBindings()
        }
    }
}
