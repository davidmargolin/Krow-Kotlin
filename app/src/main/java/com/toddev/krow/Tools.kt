package com.toddev.krow

import android.support.v4.app.Fragment
import com.google.android.gms.maps.GoogleMap

fun workSpaceWithInfo(place: Workplace, map: GoogleMap): WorkSpaceInfoFragment {
    val fragment = WorkSpaceInfoFragment()
    fragment.setObject(place, map)
    return fragment
}

fun listFragWithList(workplaces: List<Workplace>): Fragment {
    val fragment = WorkSpaceListFragment()
    fragment.setList(workplaces)
    return fragment
}