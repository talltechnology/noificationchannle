package com.example.wxy.noificationchannle.utlis;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WXY on 2018/7/6.
 */

public class NetWorKObserver {
    static {
        okcient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
    private static OkHttpClient okcient;
    private static Object t;


    public static <T>T initCher(Class<T> cal,String url){
         if(t==null||t.getClass()!=cal){
             Log.e("tag2",""+(okcient==null)+"");
             t = new Retrofit.Builder()
                     .baseUrl(url)
                     .client(okcient)
                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                     .addConverterFactory(GsonConverterFactory.create())
                     .build()
                     .create(cal);
         }
        return (T) t;
    }


}
