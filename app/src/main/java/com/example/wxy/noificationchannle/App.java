package com.example.wxy.noificationchannle;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by WXY on 2018/7/5.
 */

public class App extends Application{
    {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //图片加载框架
        Fresco.initialize(this);
        //Debagle
        CrashReport.initCrashReport(getApplicationContext(), "c094decdb6", true);
        //短信验证
        MobSDK.init(this);
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

    }
}
