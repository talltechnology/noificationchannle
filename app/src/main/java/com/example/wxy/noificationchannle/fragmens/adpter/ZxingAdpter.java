package com.example.wxy.noificationchannle.fragmens.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.fragmens.bean.ZxingBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;

import butterknife.ButterKnife;

/**
 * Created by WXY on 2018/7/16.
 */

public class ZxingAdpter extends XRecyclerView.Adapter {

    private Context context;
    private ZxingBean zxingBean;

    public ZxingAdpter(Context context, ZxingBean zxingBean) {
        this.context = context;
        this.zxingBean = zxingBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ZxingViewHoder(LayoutInflater.from(context).inflate(R.layout.zxing_item_banner_layout,parent,false));
            default:
                return new ZxingViewHoder2(LayoutInflater.from(context).inflate(R.layout.zxing_item_layout,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ZxingViewHoder extends RecyclerView.ViewHolder {
        public Banner banner;

        public ZxingViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    public static class ZxingViewHoder2 extends RecyclerView.ViewHolder {
        public Banner banner;

        public ZxingViewHoder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
