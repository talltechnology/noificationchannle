package com.example.wxy.noificationchannle.fragmens.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.base.basefragment.BaseFragment;
import com.example.wxy.noificationchannle.fragmens.bean.TabBean;
import com.example.wxy.noificationchannle.fragmens.bean.ZxingBean;
import com.example.wxy.noificationchannle.fragmens.moder.ZxingM;
import com.example.wxy.noificationchannle.fragmens.presenter.ZxingP;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by WXY on 2018/7/7.
 */

public class Zxingfragment extends BaseFragment implements ZCallback {

    public TabLayout tabLayout;
    public ViewPager viewpage;
    private ZxingP zxingP;
    private TabBean tab;
    private ZxingBean zxingBean1;
    private ArrayList<Fragment> fragments;
    private Bundle bundle;
    private boolean flage;
    private boolean s1 = false;
    private boolean s2 = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int arg1 = msg.arg1;
            if (arg1 == 1) {
                s1 = true;
            } else {
                s2 = true;
            }
            if (s1 && s2) {
                setTables();
            }
        }
    };

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        tabLayout = view.findViewById(R.id.zxing_tab);
        viewpage = view.findViewById(R.id.zxing_viewpage);
        zxingP = new ZxingP();
        zxingP.Attach(new ZxingM(), this);
        zxingP.requst2();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zxingP.requst();
    }


    @Override
    protected int findlayout() {
        return R.layout.zxingfragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        zxingP.Dettch();
    }

    @Override
    public <T> void faliu(T t) {

    }

    @Override
    public void Seecc(ZxingBean zxingBean) {
        zxingBean1 = zxingBean;
        Message message = handler.obtainMessage();
        message.arg1 = 1;
        handler.sendMessage(message);
    }

    @Override
    public void Seecc2(TabBean tab) {
        this.tab = tab;
        Message message = handler.obtainMessage();
        message.arg1 = 2;
        handler.sendMessage(message);
    }

    private void setTables() {

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 30);
        for (int i = 0; i < tab.getData().size(); i++) {
            TabFragment tabFragment = new TabFragment();
            int cid = tab.getData().get(i).getCid();
            bundle = new Bundle();
            bundle.putInt("key", cid);
            tabFragment.Bunder(bundle);
            fragments.add(tabFragment);
            tabLayout.addTab(tabLayout.newTab());
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
                    tabAt.setTag(i);
                    tabAt.setText("" + tab.getData().get(i).getName());
        }
        viewpage.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return tab.getData().size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tab.getData().get(position).getName();
            }
        });
        tabLayout.setupWithViewPager(viewpage);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpage.setCurrentItem(2);


    }

}
