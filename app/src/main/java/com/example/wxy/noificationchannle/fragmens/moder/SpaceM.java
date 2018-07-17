package com.example.wxy.noificationchannle.fragmens.moder;

import android.media.MediaPlayer;

import com.example.wxy.noificationchannle.api.UserApi;
import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.base.BaseP;
import com.example.wxy.noificationchannle.base.BeanM;
import com.example.wxy.noificationchannle.conmmon.Constants;
import com.example.wxy.noificationchannle.utlis.NetWorKObserver;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by WXY on 2018/7/10.
 */

public class SpaceM{
    public void requst(String s, File file2, final BaseMCallback baseMCallback) {
        try {
            RequestBody requestBody=RequestBody.create(MediaType.parse("application/octet-stream"),file2);
            MultipartBody.Part part =MultipartBody.Part.createFormData("file",file2.getName(),requestBody);
            UserApi userApi = NetWorKObserver.initCher(UserApi.class, Constants.URL);
            Observable<BeanM> uploder = userApi.uploder(s, part);
            uploder.subscribe(new Observer<BeanM>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BeanM beanM) {
                       baseMCallback.Seecc(beanM);
                }

                @Override
                public void onError(Throwable e) {
                       baseMCallback.faliu(e);
                }

                @Override
                public void onComplete() {

                }
            });
        }catch (Exception e){

        }finally {

        }
    }
}
