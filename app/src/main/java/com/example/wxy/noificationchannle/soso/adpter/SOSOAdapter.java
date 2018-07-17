package com.example.wxy.noificationchannle.soso.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.fragmens.bean.MakeBean;
import com.example.wxy.noificationchannle.soso.bean.SOSOBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WXY on 2018/7/13.
 */

public class SOSOAdapter extends XRecyclerView.Adapter {

    private Context context;
    private SOSOBean sosoBean;
    private View.OnClickListener onclick;
    public SOSOAdapter(Context context, SOSOBean sosoBean, View.OnClickListener onclick) {
        this.context = context;
        this.sosoBean = sosoBean;
        this.onclick=onclick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new SOSOViewHoder1(LayoutInflater.from(context).inflate(R.layout.soso_item_layout, parent, false));
        } else {
            return new SOSOViewHoder2(LayoutInflater.from(context).inflate(R.layout.make_item3_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         if(holder instanceof SOSOViewHoder1){
             setitem((SOSOViewHoder1)holder);
         }else if(holder instanceof SOSOViewHoder2){
             setitem((SOSOViewHoder2)holder,position);
         }
    }

    private void setitem(SOSOViewHoder2 holder,int postion) {
        SOSOBean.DataBean dataBean = sosoBean.getData().get(postion);

        String images = dataBean.getImages();
        String[] icon = images.split("\\|");
        holder.image.setImageURI(Uri.parse(icon[0]));
        holder.name.setText("订单"+dataBean.getPid()+"号");
    }

    @Override
    public int getItemCount() {
        if (sosoBean == null||sosoBean.getData().size()==0) {
            return 1;
        }
        return sosoBean.getData().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     *
     * @param item
     */
    public void setitem( final SOSOViewHoder1 item) {


        TextView sosotext = item.sosotext;
        sosotext.setTag(R.id.soso_id,item.qrester);
              sosotext.setOnClickListener(onclick);
              //
        item.soso_ht.setOnClickListener(onclick);

    }

    public void setSeeSoso(SOSOBean seeSoso) {
        this.sosoBean = seeSoso;
    }


    /**
     * 单条目
     */
    public static class SOSOViewHoder2 extends XRecyclerView.ViewHolder {
        public SimpleDraweeView image;
        public TextView name;
        public SOSOViewHoder2(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.make_simp);
            name=itemView.findViewById(R.id.make_title);
        }
    }

    /*
     单条目
   */
    public static class SOSOViewHoder1 extends XRecyclerView.ViewHolder {
        @BindView(R.id.quertname)
        public EditText qrester;
        @BindView(R.id.quertsoso)
        public ImageView soso;
        @BindView(R.id.soso_soso_text)
        public TextView sosotext;
        @BindView(R.id.soso_ht)
        public ImageView soso_ht;
        public SOSOViewHoder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
