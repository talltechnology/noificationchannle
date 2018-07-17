package com.example.wxy.noificationchannle.api;

import com.example.wxy.noificationchannle.base.BeanM;
import com.mob.tools.network.MultiPart;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by WXY on 2018/7/10.
 */

public interface UserApi {
    @POST("file/upload")
    Observable<BeanM> uploder(@Query("pid")String  path, @Part MultipartBody.Part list);

}
