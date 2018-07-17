package com.example.wxy.noificationchannle.api;

import com.example.wxy.noificationchannle.fragmens.bean.TabDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WXY on 2018/7/16.
 */

public interface TabApi {
    @GET("product/getProductCatagory")
    Observable<TabDataBean> requster(@Query("cid") String cid);
}
