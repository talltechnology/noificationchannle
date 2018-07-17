package com.example.wxy.noificationchannle.fragmens.presenter;

import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.base.BaseP;
import com.example.wxy.noificationchannle.fragmens.bean.BeanSpace;
import com.example.wxy.noificationchannle.fragmens.intentactivty.SpacepCallback;
import com.example.wxy.noificationchannle.fragmens.moder.SpaceM;

import java.io.File;

/**
 * Created by WXY on 2018/7/10.
 */

public class SpacecpP extends BaseP<SpaceM,SpacepCallback> {

    public void requst(String s, File file2) {
        moder.requst(s,file2, new BaseMCallback() {
            @Override
            public <T> void faliu(T t) {
                  view.faliu(t);
            }

            @Override
            public <T> void Seecc(T t) {
                 view.Seecc((BeanSpace) t);
            }
        });
    }
}
