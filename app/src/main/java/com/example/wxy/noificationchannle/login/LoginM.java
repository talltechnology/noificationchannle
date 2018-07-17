package com.example.wxy.noificationchannle.login;

import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.conmmon.Constants;
import com.example.wxy.noificationchannle.login.base.LoginBean;
import com.example.wxy.noificationchannle.utlis.NetWorKObserver;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/7/6.
 */

public class LoginM {
    /**
     * @param phone
     * @param pwd
     * @param baseMCallback
     */
    public void LoginRequst(String phone, String pwd,final BaseMCallback baseMCallback) {
        NetWorKObserver
                .initCher(LoginApi.class, Constants.URL)
                .requst(phone,pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                               baseMCallback.Seecc(loginBean);
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
