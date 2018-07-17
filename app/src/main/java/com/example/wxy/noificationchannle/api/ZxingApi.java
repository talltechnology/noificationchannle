package com.example.wxy.noificationchannle.api;

import com.example.wxy.noificationchannle.fragmens.bean.TabBean;
import com.example.wxy.noificationchannle.fragmens.bean.ZxingBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by WXY on 2018/7/16.
 */

public interface ZxingApi {
    @GET("ad/getAd")
    Observable<ZxingBean> qrester();
    @GET("product/getCatagory")
    Observable<TabBean> qrester2();
}
