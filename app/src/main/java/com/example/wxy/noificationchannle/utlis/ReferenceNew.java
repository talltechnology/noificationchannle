package com.example.wxy.noificationchannle.utlis;

import com.sina.weibo.sdk.utils.MD5;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by WXY on 2018/7/12.
 */

public class ReferenceNew {

    private static HashMap map;

    /**
     * 获取
     * @param t
     * @param tag
     */
    public  <T>void voidSoftReferenceTer(T t, String tag) {
        String hexdigest = MD5.hexdigest(tag);

        if(map==null){
            map=new HashMap<String, SoftReference<T>>();
        }
        SoftReference<T> softReference = new SoftReference<>(t);
        map.put(hexdigest,softReference);
        
    }



}
