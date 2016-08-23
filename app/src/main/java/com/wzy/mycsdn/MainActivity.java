package com.wzy.mycsdn;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;


public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FragmentPagerAdapter mAdapter;

    public static final String[] TITLES = new String[]{"业界", "移动", "研发", "程序员", "云计算"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.id_pager);
        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mAdapter = new TabFargmentAdpater(getSupportFragmentManager(), TITLES);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
