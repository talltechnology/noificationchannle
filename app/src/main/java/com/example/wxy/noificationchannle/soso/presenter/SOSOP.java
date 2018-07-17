package com.example.wxy.noificationchannle.soso.presenter;

import android.util.Log;

import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.base.BaseP;
import com.example.wxy.noificationchannle.soso.bean.SOSOBean;

import com.example.wxy.noificationchannle.soso.moder.SOSOMDER;
import com.example.wxy.noificationchannle.soso.view.SOSOCallBack;

/**
 * Created by WXY on 2018/7/13.
 */

public class SOSOP extends BaseP<SOSOMDER,SOSOCallBack> {
    public void requster(String name, int pager) {
        Log.e(""+name,"------------------------------------------------"+pager);
        moder.requsterData(name,pager, new BaseMCallback() {
            @Override
            public <T> void faliu(T t) {
                 view.faliu(t);
            }

            @Override
            public <T> void Seecc(T t) {
                if(t instanceof SOSOBean){
                    view.Seecc((SOSOBean) t);
                }
            }
        });
    }
}
