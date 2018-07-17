package com.example.wxy.noificationchannle.soso.moder;

import android.util.Log;

import com.example.wxy.noificationchannle.api.SOSOAPI;
import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.conmmon.Constants;
import com.example.wxy.noificationchannle.soso.bean.SOSOBean;
import com.example.wxy.noificationchannle.utlis.NetWorKObserver;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/7/13.
 */

public class SOSOMDER {
    public void requsterData(String name, int pager, final BaseMCallback baseMCallback) {
        Log.e(""+name,"--------------------------------------------M----"+pager);
        NetWorKObserver.initCher(SOSOAPI.class, Constants.URL)
                .qserter(name,""+pager)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SOSOBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SOSOBean sosoBean) {
                          baseMCallback.Seecc(sosoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        baseMCallback.faliu(e);
                        Log.e("tag","--------------------------"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
