package com.example.wxy.noificationchannle.fragmens.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.base.basefragment.BaseFragment;
import com.example.wxy.noificationchannle.fragmens.intentactivty.SpacecpActivty;
import com.example.wxy.noificationchannle.utlis.Bround;

import butterknife.BindView;

/**
 * Created by WXY on 2018/7/7.
 */

public class Myfragment extends BaseFragment implements MyVcallback, View.OnClickListener {

    @BindView(R.id.my_imge)
    public ImageView img;
    @BindView(R.id.my_liner1)
    public LinearLayout line1;
    @BindView(R.id.my_liner2)
    public LinearLayout line2;
    @BindView(R.id.my_liner3)
    public LinearLayout line3;
    @BindView(R.id.my_liner4)
    public LinearLayout line4;


    @Override
    protected void initData() {
        img.setImageBitmap(Bround.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.mipmap.mytox), 100));
        //设置监听
        line1.setOnClickListener(this);
        line2.setOnClickListener(this);
        line3.setOnClickListener(this);
        line4.setOnClickListener(this);
    }

    @Override
    protected int findlayout() {
        return R.layout.my_fragment;
    }

    @Override
    public <T> void faliu(T t) {
        ShowToas(t);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_liner1:
                startActivityForResult(new Intent(getContext(), SpacecpActivty.class),10);
                break;
            case R.id.my_liner2:

                break;
            case R.id.my_liner3:

                break;
            case R.id.my_liner4:

                break;
        }
    }
}
