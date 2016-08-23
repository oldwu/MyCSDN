package com.mycsdn.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wzy on 2016/3/14.
 */
public class TabFargmentAdpater extends FragmentPagerAdapter {


    public String[] title;

    public TabFargmentAdpater(FragmentManager fm, String[] title) {
        super(fm);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        MainFragment fragment = new MainFragment(position);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position % title.length];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
