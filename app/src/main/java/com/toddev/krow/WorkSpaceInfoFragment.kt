package com.toddev.krow

import android.databinding.DataBindingUtil
import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.toddev.krow.databinding.FragmentWorkspaceBinding

class WorkSpaceInfoFragment : Fragment() {
    private var workplace: Workplace = Workplace()
    //empty workplace just in case object isn't set. prevents null
    private var googleMap: GoogleMap? = null

    //WorkSpaceInfoFragment with Object creator moved to Tools.kt

    fun setObject(place: Workplace, map: GoogleMap) {
        this.workplace = place
        this.googleMap = map
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment'
        val binding: FragmentWorkspaceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_workspace, container, false)
        val rootView = binding.root
        binding.setWorkplace(workplace)

        val bottomSheet = container!!.findViewById<NestedScrollView>(R.id.bottom_sheet)
        val mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        val sheetTextDesc = rootView.findViewById<TextView>(R.id.desc)
        val infocard = rootView.findViewById<CardView>(R.id.infocard)
        infocard.setOnClickListener {
            if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                sheetTextDesc.maxLines = 500
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)

            } else {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }

        mBottomSheetBehavior.peekHeight = 375
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        mBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            internal var offset = 0.8
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    sheetTextDesc.maxLines = 2
                } else {
                    sheetTextDesc.maxLines = 500
                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset > offset) {
                    val projection = googleMap!!.projection
                    val markerPosition = LatLng(workplace.latitude!!, workplace.longitude!!)
                    val markerPoint = projection.toScreenLocation(markerPosition)
                    val targetPoint = Point(markerPoint.x, markerPoint.y + infocard.height - 50)
                    val targetPosition = projection.fromScreenLocation(targetPoint)
                    googleMap!!.animateCamera(CameraUpdateFactory.newLatLng(targetPosition), 150, null)
                    offset = 1.0
                } else if (slideOffset == 0f) {
                    offset = 0.8
                }
            }
        })

        return rootView
    }
}