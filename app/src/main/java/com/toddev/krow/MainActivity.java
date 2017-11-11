package com.toddev.krow;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Boolean isList=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view to res -> layout -> activity main.xml (in layouts)
        setContentView(R.layout.activity_main);

        //get toolbar from layout based on id toolber
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //get viewpager from layout from id container
        mViewPager = (ViewPager) findViewById(R.id.container);

        //set controller to SectionsPagerAdapter class(below)
        mViewPager.setAdapter(mSectionsPagerAdapter);

        if (savedInstanceState==null){
            mViewPager.setCurrentItem(1, false);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //looks for menu items in res -> menu -> menu main
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //based on id of item selected, do stuff (nothing right now)
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //do things based on position
            switch (position){
                case (0):
                    return ProfileFragment.newInstance();
                //if position is 1
                case(1):
                    //show a mapsfragment
                    return MapsFragment.newInstance();

                case(2):
                    return WorkSpaceFragment.newInstance(1);

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // 3 total pages.
            return 3;
        }
    }
}
