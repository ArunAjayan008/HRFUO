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
        PagerView adapter = new PagerView(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class PagerView extends FragmentStatePagerAdapter {

        int tabs;

        public PagerView(@NonNull FragmentManager fm, int behavior) {
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
