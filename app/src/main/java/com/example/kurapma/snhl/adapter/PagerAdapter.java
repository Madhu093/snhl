package com.example.kurapma.snhl.adapter;

/**
 * Created by kurapma on 1/11/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kurapma.snhl.fragment.DonorFragment;
import com.example.kurapma.snhl.fragment.NewsFragment;
import com.example.kurapma.snhl.fragment.StoryFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NewsFragment tab1 = new NewsFragment();
                return tab1;
            case 1:
                StoryFragment tab2 = new StoryFragment();
                return tab2;
            case 2:
                DonorFragment tab3 = new DonorFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}