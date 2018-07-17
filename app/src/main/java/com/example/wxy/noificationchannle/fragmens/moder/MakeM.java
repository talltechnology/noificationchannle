package com.example.wxy.noificationchannle.fragmens.moder;

import android.util.Log;

import com.example.wxy.noificationchannle.api.MakeApi;
import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.conmmon.Constants;
import com.example.wxy.noificationchannle.fragmens.bean.MakeBean;
import com.example.wxy.noificationchannle.utlis.NetWorKObserver;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/7/9.
 */

public class MakeM {
//16195&token=885FD3D307D0457B96286A4CB99C5836

    public void requstloder(final BaseMCallback baseMCallback) {
        String uid="16195";
        final String token="885FD3D307D0457B96286A4CB99C5836";
        NetWorKObserver.initCher(MakeApi.class,"https://www.zhaoapi.cn/")
                .requstloder(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MakeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MakeBean makeBean) {
                        Log.e("-------1",makeBean.getMsg());
                        Log.e("-------2",makeBean.getCode());
                        baseMCallback.Seecc(makeBean);
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
