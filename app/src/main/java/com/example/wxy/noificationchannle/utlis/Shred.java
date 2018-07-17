package com.example.wxy.noificationchannle.utlis;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WXY on 2018/7/5.
 */

public class Shred {

    private final SharedPreferences sp;

    public Shred(Context context, String tablename) {
        sp = context.getSharedPreferences(tablename, Context.MODE_PRIVATE);
    }

    /**
     * boolean 取值
     */
    public boolean IsBoolen(String key){
       return sp.getBoolean(key,false);
    }
    /**
     * boolean 赋值
     */
    public boolean setBoolen(String key,boolean value){
        return sp.edit().putBoolean(key,value).commit();
    }
    /**
     * String 取值
     */
    public String getString(String key){
        return sp.getString(key,"");
    }
    /**
     * String 赋值
     */
    public boolean setString(String key,String value){
        return sp.edit().putString(key,value).commit();
    }
}
