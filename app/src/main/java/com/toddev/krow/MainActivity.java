package com.toddev.krow;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

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

        //get tablayout from layout based on id tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        //control viewpager with the tablayout
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //control tablayout with viewpager (they need to talk to each other both ways)
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //looks for menu items in res -> menu -> menu main
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                //if position is 1
                case(1):
                    //show a mapsfragment
                    return MapsFragment.newInstance();
                //else
                default:
                    //show a blankfragment
                    return BlankFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // 3 total pages.
            return 3;
        }
    }
}
