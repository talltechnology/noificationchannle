package com.example.wxy.noificationchannle.login;

import android.text.TextUtils;

import com.example.wxy.noificationchannle.base.BaseMCallback;
import com.example.wxy.noificationchannle.base.BaseP;
import com.example.wxy.noificationchannle.login.base.LoginBean;

/**
 * Created by WXY on 2018/7/6.
 */

public class LoginP extends BaseP<LoginM,LoginVCallBack>{
    public void loginRequst(String phone, String pwd) {
        if(!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(pwd)){
            //调取逻辑
            moder.LoginRequst(phone,pwd,new BaseMCallback() {
                @Override
                public <T> void faliu(T t) {
                    view.faliu(t);
                }

                @Override
                public <T> void Seecc(T t) {
                    if(t instanceof LoginBean){
                        view.seecc((LoginBean) t);
                    }
                }
            });
        }

    }
}
