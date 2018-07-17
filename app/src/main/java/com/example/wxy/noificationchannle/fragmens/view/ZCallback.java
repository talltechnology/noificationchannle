package com.example.wxy.noificationchannle.fragmens.view;

import com.example.wxy.noificationchannle.base.BaseVCallback;
import com.example.wxy.noificationchannle.fragmens.bean.TabBean;
import com.example.wxy.noificationchannle.fragmens.bean.ZxingBean;

/**
 * Created by WXY on 2018/7/16.
 */

public interface ZCallback extends BaseVCallback {
    void Seecc(ZxingBean zxingBean);
    void Seecc2(TabBean tab);
}
