package com.example.wxy.noificationchannle.fragmens.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.fragmens.bean.TabDataBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by WXY on 2018/7/16.
 */

public class TabAdpter extends XRecyclerView.Adapter<TabAdpter.TabViewHoder> {

    private Context context;
    private ArrayList<TabDataBean.DataBean.ListBean> list;

    public TabAdpter(Context context, ArrayList<TabDataBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TabViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TabViewHoder(LayoutInflater.from(context).inflate(R.layout.tab_flayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TabViewHoder holder, int position) {
        TabDataBean.DataBean.ListBean listBean = list.get(position);
        String icon = listBean.getIcon();
        holder.simpleDraweeView.setImageURI(icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TabViewHoder extends XRecyclerView.ViewHolder {
        public SimpleDraweeView simpleDraweeView;
        public TabViewHoder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.tabsimpe);
        }
    }
}
