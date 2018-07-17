package com.example.wxy.noificationchannle.main.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by WXY on 2018/7/8.
 */

public class MainFragmentAdpter extends FragmentPagerAdapter {

    public ArrayList<Fragment> list;


    public MainFragmentAdpter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
