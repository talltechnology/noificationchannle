package com.example.wxy.noificationchannle.fragmens.moder;

import com.example.wxy.noificationchannle.api.ZxingApi;
import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.conmmon.Constants;
import com.example.wxy.noificationchannle.fragmens.bean.TabBean;
import com.example.wxy.noificationchannle.fragmens.bean.ZxingBean;
import com.example.wxy.noificationchannle.utlis.NetWorKObserver;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/7/16.
 */

public class ZxingM {

    public void requst(final BaseMCallback baseMCallback) {
        NetWorKObserver.initCher(ZxingApi.class, Constants.URL)
                .qrester()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ZxingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZxingBean zxingBean) {
                        baseMCallback.Seecc(zxingBean);
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

    public void requst2(final BaseMCallback baseMCallback) {
        NetWorKObserver.initCher(ZxingApi.class, Constants.URL)
                .qrester2()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                         baseMCallback.Seecc(tabBean);
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
