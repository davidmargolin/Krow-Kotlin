package com.toddev.krow

import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class WorkSpaceInfoFragment : Fragment() {
    private var workplace: Workplace = Workplace()
    //empty workplace just in case object isn't set. prevents null
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //WorkSpaceInfoFragment with Object creator moved to Tools.kt

    fun setObject(place: Workplace, map: GoogleMap) {
        this.workplace = place
        this.googleMap = map
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment'
        val rootView = inflater.inflate(R.layout.fragment_workspace, container, false)

        val wifiB = rootView.findViewById<Button>(R.id.wifi)
        val bathroomB = rootView.findViewById<Button>(R.id.bathroom)
        val foodB = rootView.findViewById<Button>(R.id.food)
        val noiseB = rootView.findViewById<Button>(R.id.volume)
        val seatingB = rootView.findViewById<Button>(R.id.seats)
        val powerB = rootView.findViewById<Button>(R.id.outlets)

        val sheetTextName = rootView.findViewById<TextView>(R.id.name)
        val sheetTextRating = rootView.findViewById<TextView>(R.id.rating)
        val sheetTextDesc = rootView.findViewById<TextView>(R.id.desc)
        val sheetNumRated = rootView.findViewById<TextView>(R.id.numrated)
        val sheetAddress = rootView.findViewById<TextView>(R.id.address)

        wifiB.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(activity!!.applicationContext, getWorkResource(workplace.amenities!!.wifi!!)), null, null)
        wifiB.text = workplace.amenities!!.wifi

        bathroomB.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(activity!!.applicationContext, getWorkResource(workplace.amenities!!.bathrooms!!)), null, null)
        bathroomB.text = workplace.amenities!!.bathrooms

        foodB.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(activity!!.applicationContext, getWorkResource(workplace.amenities!!.food!!)), null, null)
        foodB.text = workplace.amenities!!.food

        noiseB.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(activity!!.applicationContext, getWorkResource(workplace.amenities!!.noise!!)), null, null)
        noiseB.text = workplace.amenities!!.noise

        seatingB.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(activity!!.applicationContext, getWorkResource(workplace.amenities!!.space!!)), null, null)
        seatingB.text = workplace.amenities!!.space

        powerB.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(activity!!.applicationContext, getWorkResource(workplace.amenities!!.outlets!!)), null, null)
        powerB.text = workplace.amenities!!.outlets

        sheetTextName.text = workplace.name!!
        sheetAddress.text = workplace.address
        sheetTextDesc.text = workplace.description
        if (workplace.numrated!! > 0) {
            sheetNumRated.text = "${Integer.toString(workplace.numrated!!)} Reviews"
            sheetTextRating.text = "Rating: ${workplace.rating!!}"
        } else {
            sheetTextRating.text = "Not Yet Rated"
        }


        val bottomSheet = container!!.findViewById<NestedScrollView>(R.id.bottom_sheet)
        val mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        mBottomSheetBehavior.peekHeight = 375
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        val infocard = rootView.findViewById<CardView>(R.id.infocard)
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

        infocard.setOnClickListener {
            if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                sheetTextDesc.maxLines = 500
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)

            } else {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }

        return rootView
    }
}