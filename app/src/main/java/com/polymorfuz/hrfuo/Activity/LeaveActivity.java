package com.polymorfuz.hrfuo.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.polymorfuz.hrfuo.Fragment.HolidayFragment;
import com.polymorfuz.hrfuo.Fragment.LeaveFragment;
import com.polymorfuz.hrfuo.R;

public class LeaveActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Leaves"));
        tabLayout.addTab(tabLayout.newTab().setText("Holidays"));
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class Pager extends FragmentStatePagerAdapter {

        int tabs;

        public Pager(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            this.tabs = behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new LeaveFragment();
                case 1:
                    return new HolidayFragment();
            }
            return null;

        }

        @Override
        public int getCount() {
            return tabs;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position) {
                case 0:
                    title = "Leaves";
                    return title;
                case 1:
                    title = "Holidays";
                    return title;
            }
            return null;
        }
    }
}

//        //integer to count number of tabs
//        int tabCount;
//
//        //Constructor to the class
//        public Pager(FragmentManager fm, int tabCount) {
//            super(fm);
//            //Initializing tab count
//            this.tabCount = tabCount;
//        }
//
//        //Overriding method getItem
//        @Override
//        public com.polymorfuz.hrfuo.Fragment getItem(int position) {
//            //Returning the current tabs
//            switch (position) {
//                case 0:
//                    CustomerTabFragment tab1 = new CustomerTabFragment();
//                    return tab1;
//                case 1:
//                    MembersTabFragment tab2 = new MembersTabFragment();
//                    return tab2;
//            }
//            return null;
//        }
//
//        //Overriden method getCount to get the number of tabs
//        @Override
//        public int getCount() {
//            return tabCount;
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            String title = null;
//            switch (position) {
//                case 0:
//                    title = "Customers";
//                    return title;
//                case 1:
//                    title = "Members";
//                    return title;
//            }
//            return null;
//        }
//    }

