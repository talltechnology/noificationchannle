package com.example.wxy.noificationchannle.gundance;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wxy.noificationchannle.MainActivity;
import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.gundance.adpter.ViewPager_Guidance_Adpter;
import com.example.wxy.noificationchannle.utlis.Shred;

import java.util.ArrayList;

public class GuidanceActivity extends AppCompatActivity {
    private ViewPager pager;
    private Shred shred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        isIntent();//是否跳转
        initView();
        initData();
    }
    public void isIntent() {
        //储存
        shred = new Shred(this,"Guidance");
        if(shred.IsBoolen("Guidancekey")){
            intent();
        }
    }

    /**
     * 跳转
     */
    public void intent(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    /**
     * 初始化试图
     */
    private void initView() {
        pager = findViewById(R.id.view_pager_guidance);
    }

    /**
     * 初始化数据
     */
    private void initData() {

        int[] arr = { R.mipmap.guidance1, R.mipmap.guidance2, R.mipmap.guidance3};
        final ArrayList<View> list = new ArrayList<>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < arr.length; i++) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(layoutParams);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setImageResource(arr[i]);
            list.add(img);
        }
        pager.setAdapter(new ViewPager_Guidance_Adpter(list));//适配
        pager.setCurrentItem(0);//设置当前
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//改变监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==pager.getChildCount()){
                   intent();//跳转
                    shred.setBoolen("Guidancekey",true);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Toast
     *
     * @param t
     * @param <T>
     */
    public <T> void ShowToast(T t) {
        Toast.makeText(this, "" + t, Toast.LENGTH_LONG).show();
    }


}
