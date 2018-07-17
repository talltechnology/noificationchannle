package com.example.wxy.noificationchannle.api;

import com.example.wxy.noificationchannle.fragmens.bean.MakeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by WXY on 2018/7/11.
 */

public interface MakeApi {
    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<MakeBean> requstloder(@Field("uid") String uid, @Field("token") String token);
}
