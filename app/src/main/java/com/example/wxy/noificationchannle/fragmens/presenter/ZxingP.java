package com.example.wxy.noificationchannle.fragmens.presenter;

import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.base.BaseP;
import com.example.wxy.noificationchannle.fragmens.bean.TabBean;
import com.example.wxy.noificationchannle.fragmens.bean.ZxingBean;
import com.example.wxy.noificationchannle.fragmens.moder.ZxingM;
import com.example.wxy.noificationchannle.fragmens.view.ZCallback;

/**
 * Created by WXY on 2018/7/16.
 */

public class ZxingP extends BaseP<ZxingM,ZCallback> {
    public void requst() {
        moder.requst(new BaseMCallback() {
            @Override
            public <T> void faliu(T t) {
                view.faliu(t);
            }

            @Override
            public <T> void Seecc(T t) {
                if(t instanceof ZxingBean){
                    view.Seecc((ZxingBean) t);
                }
            }
        });
    }

    public void requst2() {
        moder.requst2(new BaseMCallback() {
            @Override
            public <T> void faliu(T t) {
                view.faliu(t);
            }

            @Override
            public <T> void Seecc(T t) {
                if(t instanceof TabBean){
                    view.Seecc2((TabBean) t);
                }
            }
        });
    }
}
