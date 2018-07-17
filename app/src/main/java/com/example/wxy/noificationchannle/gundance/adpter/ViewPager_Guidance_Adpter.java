package com.example.wxy.noificationchannle.gundance.adpter;

import android.support.annotation.NonNull;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by WXY on 2018/7/5.
 */

public class ViewPager_Guidance_Adpter extends PagerAdapter {

    public ArrayList<View> list;

    public ViewPager_Guidance_Adpter(ArrayList<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = list.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
