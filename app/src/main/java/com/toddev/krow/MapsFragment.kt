package com.toddev.krow

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore

class MapsFragment : Fragment(), OnMapReadyCallback {

    var mMap: GoogleMap? = null
    var mapView: MapView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.activity_maps, container, false)
        mapView = rootView.findViewById<MapView>(R.id.map)
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.activity!!, R.raw.style_json))

        //create a location for nyc, set map location to make nyc the center and zoom to city level
        val nyc = LatLng(40.7128, -74.006)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(nyc))
        mMap!!.moveCamera(CameraUpdateFactory.zoomTo(10f))

        //set all markers
        val db = FirebaseFirestore.getInstance()
        val workref = db.collection("Workspaces")
        workref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // 'Workplace::class.java' <-- weird
                for (place in task.result.toObjects(Workplace::class.java)) {
                    val marker = mMap!!.addMarker(MarkerOptions().position(LatLng(place.latitude!!, place.longitude!!)).title(place.name))
                    marker.tag = place
                }
            } else {
                Log.d("Error", "Error getting documents: ", task.exception)
            }
        }


        //when a marker is clicked:
        mMap!!.setOnMarkerClickListener { marker ->
            fragmentManager!!.popBackStackImmediate()
            val newFragment = workSpaceWithInfo((marker.tag as Workplace?)!!, googleMap)
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.bottom_sheet, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()

            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(marker.position))
            mMap!!.moveCamera(CameraUpdateFactory.zoomTo(15f))
            true
        }

        //search
        val searchbar = activity!!.findViewById<SearchView>(R.id.search)
        searchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                workref.whereEqualTo("name_lower", query.toLowerCase()).get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        fragmentManager!!.popBackStackImmediate()

                        //create list frag
                        val newFragment = listFragWithList(task.result.toObjects(Workplace::class.java))
                        val transaction = fragmentManager!!.beginTransaction()
                        transaction.replace(R.id.bottom_sheet, newFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()

                        //reset map and pins
                        mMap!!.clear()
                        for (place in task.result.toObjects(Workplace::class.java)) {
                            val marker = mMap!!.addMarker(MarkerOptions().position(LatLng(place.latitude!!, place.longitude!!)).title(place.name))
                            marker.tag = place
                        }
                        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(nyc))
                        mMap!!.moveCamera(CameraUpdateFactory.zoomTo(10f))

                    } else {
                        Log.d("Error", "Error getting documents: ", task.exception)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    fun setMarker(workplace: Workplace) {
        val googleMap = mMap
        if (mMap != null) {
            fragmentManager!!.popBackStackImmediate()
            val newFragment = workSpaceWithInfo(workplace, googleMap!!)
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.bottom_sheet, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(workplace.latitude!!, workplace.longitude!!)))
            mMap!!.moveCamera(CameraUpdateFactory.zoomTo(15f))
        }
    }

    override fun onResume() {
        mapView!!.onResume()
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

}