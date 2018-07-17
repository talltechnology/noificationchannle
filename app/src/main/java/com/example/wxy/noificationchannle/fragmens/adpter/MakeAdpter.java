package com.example.wxy.noificationchannle.fragmens.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.fragmens.bean.MakeBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WXY on 2018/7/11.
 */

public class MakeAdpter extends XRecyclerView.Adapter{

    private Context context;
    private MakeBean makeBean;
    private View.OnClickListener make;

    public MakeAdpter(Context context,View.OnClickListener make) {
        this.context = context;
        this.make=make;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new MakeViewHoder1(LayoutInflater.from(context).inflate(R.layout.make_item1_layout, parent, false));
            case 1:
                return new MakeViewHoder2(LayoutInflater.from(context).inflate(R.layout.make_item2_layout, parent, false));
            default:
                return new MakeViewHoder3(LayoutInflater.from(context).inflate(R.layout.make_item3_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
              if(holder instanceof MakeViewHoder3){
                  setitemMake((MakeViewHoder3)holder,position-2);
              }else if(holder instanceof MakeViewHoder1){
                  MakeViewHoder1 file2 = (MakeViewHoder1) holder;
                  ImageView iamge = file2.iamge;
                  iamge.setOnClickListener(make);
                  LinearLayout linearLayout = file2.linearLayout;
                  linearLayout.setOnClickListener(make);
              }
    }               


    public void setitemMake(MakeViewHoder3 makeViewHoder3,int postion){
        MakeBean.DataBean dataBean = makeBean.getData().get(postion);
        MakeBean.DataBean.ListBean listBean = dataBean.getList().get(0);
        String images = listBean.getImages();
        String[] icon = images.split("\\|");
        makeViewHoder3.image.setImageURI(Uri.parse(icon[0]));
        makeViewHoder3.name.setText("订单"+listBean.getPid()+"号");
    }

    @Override
    public int getItemCount() {
        return 2+makeBean.getData().size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setMakebenan(MakeBean makebenan) {
        this.makeBean = makebenan;
    }



    /**
     *
     */
    private static class MakeViewHoder1 extends XRecyclerView.ViewHolder{
        public ImageView iamge;
        public LinearLayout linearLayout;
        public MakeViewHoder1(View itemView) {
            super(itemView);
            iamge=itemView.findViewById(R.id.nake_address);
            linearLayout=itemView.findViewById(R.id.layoutrselecter);
        }
    }

    /**
     *
     */
    private static class MakeViewHoder2 extends XRecyclerView.ViewHolder{

        public MakeViewHoder2(View itemView) {
            super(itemView);

        }
    }

    /**
     *
     */
    private static class MakeViewHoder3 extends XRecyclerView.ViewHolder{

        public SimpleDraweeView image;
        public TextView name;
        public MakeViewHoder3(View itemView) {
            super(itemView);
             image=itemView.findViewById(R.id.make_simp);
             name=itemView.findViewById(R.id.make_title);
        }
    }



}
