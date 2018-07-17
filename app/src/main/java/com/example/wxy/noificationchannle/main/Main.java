package com.example.wxy.noificationchannle.main;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.fragmens.view.MakeAnAppointmentFragment;
import com.example.wxy.noificationchannle.fragmens.view.Myfragment;
import com.example.wxy.noificationchannle.fragmens.view.Zxingfragment;
import com.example.wxy.noificationchannle.main.adpter.MainFragmentAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Main extends AppCompatActivity implements View.OnClickListener {


    private Unbinder unbinder;

    @BindView(R.id.main_line)
    public LinearLayout line;
    private int[] imgsresource;
    private int[] imgsresource2;
    private float start = 0;
    private int widthPixels;
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> fragments;
    private volatile int fla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (Build.VERSION.SDK_INT >= 21) {
           getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//透明色
            getSupportActionBar().hide();
        }
        initData();
    }

    /**
     * 初始化
     */
    private void initData() {
        unbinder = ButterKnife.bind(this);
        ArrayList<ImageView> arr = new ArrayList<>();
        addfragments();
        addimage();
        setimage(1);
    }

    private void addfragments() {
        fragments = new ArrayList<>();
        fragments.add(new MakeAnAppointmentFragment());
        fragments.add(new Zxingfragment());
        fragments.add(new Myfragment());
        fragmentManager = getSupportFragmentManager();
        //隐藏
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_frame,fragments.get(0)).commit();
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.add(R.id.main_frame,fragments.get(1)).commit();
        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
        fragmentTransaction3.add(R.id.main_frame,fragments.get(2)).commit();

    }

    private void settaggel(int n) {
        for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
            Fragment fragment = fragmentManager.getFragments().get(i);
            if(i==n){
                 fla=i;
                fragmentManager.beginTransaction().show(fragment).commit();
            }else {
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(this, ev.getAction() + "按下", Toast.LENGTH_LONG).show();
                Log.e("按下", "x" + x + "--" + y);
                start=x;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                Toast.makeText(this, ev.getAction() + "离开", Toast.LENGTH_LONG).show();
                Log.e("移开", "x" + x + "--" + y);
                float flage = x - start;
                if(Math.abs(flage)>widthPixels){
                    if(flage>0){
                      if(fla==2){
                          settaggel(0);
                          setimage(0);
                      }
                    }else {
                        if(fla==0){
                            settaggel(2);
                            setimage(2);
                        }
                    }
                }
                start=0;
                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 添加图签
     */
    private void addimage() {
        widthPixels = getResources().getDisplayMetrics().widthPixels / 3;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthPixels, ViewGroup.LayoutParams.MATCH_PARENT);
        imgsresource = new int[]{R.mipmap.yuyuefalse, R.mipmap.zxing, R.mipmap.myfalse};
        imgsresource2 = new int[]{R.mipmap.yuyuetrue, R.mipmap.zxing, R.mipmap.mytrue};
        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(imgsresource2[i]);
            if(i!=1){
                layoutParams.height=100;
                layoutParams.gravity= Gravity.CENTER;
                layoutParams.topMargin=10;
                img.setLayoutParams(layoutParams);
            }else {
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(widthPixels, ViewGroup.LayoutParams.MATCH_PARENT);
                img.setLayoutParams(layoutParams2);
            }
            img.setTag(i);
            img.setOnClickListener(this);
            line.addView(img);
        }
    }

    //置空
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        setimage((Integer) v.getTag());
    }

    /**
     * 切换图标
     *
     * @param image
     */
    public void setimage(Integer image) {
        for (int i = 0; i < line.getChildCount(); i++) {
            ImageView childAt = (ImageView) line.getChildAt(i);
            if ((Integer) childAt.getTag() == image) {
                childAt.setImageResource(imgsresource[i]);
                settaggel(i);
            } else {
                childAt.setImageResource(imgsresource2[i]);
            }
        }
    }
}
