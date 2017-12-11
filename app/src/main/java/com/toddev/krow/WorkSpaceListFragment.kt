package com.toddev.krow

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WorkSpaceListFragment : Fragment() {
    private var workplaces: List<Workplace>? = null

    //Fragment with list moved to Tools

    fun setList(workplaces: List<Workplace>) {
        this.workplaces = workplaces
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_workspace_list, container, false) as RecyclerView
        val context = view.context
        view.layoutManager = LinearLayoutManager(context)
        view.adapter = MyWorkSpaceRecyclerViewAdapter(workplaces!!, activity!!)

        val bottomSheet = container!!.findViewById<NestedScrollView>(R.id.bottom_sheet)
        val mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        mBottomSheetBehavior.peekHeight = 800
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        return view
    }

}
