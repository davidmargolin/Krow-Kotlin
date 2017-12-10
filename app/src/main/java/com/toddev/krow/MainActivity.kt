package com.toddev.krow

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    private var mapsFragment: MapsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseAnalytics.getInstance(this)

        //type assumed from findViewById<Toolbar> so no need to do 'var toolbar: Toolbar'
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        val mViewPager = findViewById<ViewPager>(R.id.container)
        mViewPager.adapter = mSectionsPagerAdapter
        mViewPager.setCurrentItem(1, false)
    }


    fun setMarker(workplace: Workplace) {
        if (mapsFragment != null) {
            // !! operator throws an exception if the item is null.
            mapsFragment!!.setMarker(workplace)
        }
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            //create frag based on position
            when (position) {
                0 -> return ProfileFragment.newInstance()
                1 -> {
                    mapsFragment = MapsFragment.newInstance()
                    return mapsFragment
                }
                else -> return null
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
