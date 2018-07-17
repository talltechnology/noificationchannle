package com.example.wxy.noificationchannle.fragmens.presenter;

import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.base.BaseP;
import com.example.wxy.noificationchannle.fragmens.bean.TabDataBean;
import com.example.wxy.noificationchannle.fragmens.moder.TabM;
import com.example.wxy.noificationchannle.fragmens.view.TabCallback;

/**
 * Created by WXY on 2018/7/16.
 */

public class TabP extends BaseP<TabM,TabCallback>{
    public void requst(int page) {
        moder.Reqsuter(page, new BaseMCallback() {
            @Override
            public <T> void faliu(T t) {
                 view.faliu(t);
            }

            @Override
            public <T> void Seecc(T t) {
             view.Seecc((TabDataBean) t);
            }
        });
    }
}
