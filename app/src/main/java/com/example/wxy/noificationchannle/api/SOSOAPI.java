package com.example.wxy.noificationchannle.api;

import com.example.wxy.noificationchannle.soso.bean.SOSOBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WXY on 2018/7/13.
 */

public interface SOSOAPI {
    @GET("product/searchProducts")
    Observable<SOSOBean> qserter(@Query("keywords") String keywords,@Query("page") String page);
}
