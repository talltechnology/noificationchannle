package com.example.wxy.noificationchannle.login;

import com.example.wxy.noificationchannle.conmmon.Constants;
import com.example.wxy.noificationchannle.login.base.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WXY on 2018/7/6.
 */

public interface LoginApi {
    @GET("user/login")
    Observable<LoginBean> requst(@Query("mobile")String phone, @Query("password")String pwd);
}
