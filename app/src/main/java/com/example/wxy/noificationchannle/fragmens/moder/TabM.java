package com.example.wxy.noificationchannle.fragmens.moder;

import android.util.Log;

import com.example.wxy.noificationchannle.api.MakeApi;
import com.example.wxy.noificationchannle.api.TabApi;
import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.fragmens.bean.MakeBean;
import com.example.wxy.noificationchannle.fragmens.bean.TabDataBean;
import com.example.wxy.noificationchannle.utlis.NetWorKObserver;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/7/16.
 */

public class TabM {
    public void Reqsuter(int page, final BaseMCallback baseMCallback) {
        NetWorKObserver.initCher(TabApi.class,"https://www.zhaoapi.cn/")
                .requster(""+page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabDataBean dataBean) {
                         baseMCallback.Seecc(dataBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                         baseMCallback.faliu(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
