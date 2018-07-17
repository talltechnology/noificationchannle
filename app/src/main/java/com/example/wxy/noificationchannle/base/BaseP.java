package com.example.wxy.noificationchannle.base;

/**
 * Created by WXY on 2018/7/6.
 */

public class BaseP<M,V extends BaseVCallback> {
    public M moder;
    public V view;

    /**
     * 绑定
     */
    public void Attach(M moder,V view) {
        this.moder=moder;
        this.view=view;
    }

    /**
     * 解绑 置空对象，避免内存泄漏
     */
    public void Dettch(){
        this.view=null;
        this.moder=null;
    }

}
