package com.example.wxy.noificationchannle.fragmens.presenter;

import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.base.BaseP;
import com.example.wxy.noificationchannle.fragmens.bean.MakeBean;
import com.example.wxy.noificationchannle.fragmens.moder.MakeM;
import com.example.wxy.noificationchannle.fragmens.view.MakeVCallback;

/**
 * Created by WXY on 2018/7/9.
 */

public class MakeP extends BaseP<MakeM,MakeVCallback> {

    public void requstloder() {
        moder.requstloder(new BaseMCallback() {
            @Override
            public <T> void faliu(T t) {
               view.faliu(t);
            }

            @Override
            public <T> void Seecc(T t) {
              view.seecc((MakeBean) t);
            }
        });
    }
}
