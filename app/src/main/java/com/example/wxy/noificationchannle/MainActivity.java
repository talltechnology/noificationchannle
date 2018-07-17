package com.example.wxy.noificationchannle;


import android.Manifest;
import android.app.NotificationManager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wxy.noificationchannle.login.LoginActivity;
import com.facebook.drawee.backends.pipeline.Fresco;

import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends AppCompatActivity {
    public NotificationManager manager;
    private SimpleDraweeView welcome_simple;
    private final int WRITE_EXTERNAL_STORAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         initView();
         initData();
    }

    private void initData() {
        Uri parse = Uri.parse("android.resource://com.example.wxy.noificationchannle/" + R.mipmap.splash_screen);

//        ImageRequest imgrequst= ImageRequestBuilder
//                .newBuilderWithSource(parse)
//                .setProgressiveRenderingEnabled(true)
//                .build();

        DraweeController controll = Fresco.newDraweeControllerBuilder()
                .setUri(parse)
//                .setImageRequest(imgrequst)
                .setAutoPlayAnimations(true)
                .build();

         welcome_simple.setController(controll);

    }

    private void setpremistion() {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){

                }else {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE);
                }
            }
        }
    }

    /**
     * 初始化渲染控件C:\Program Files\Java\jre-9.0.1\bin
     */
    private void initView() {
        welcome_simple = findViewById(R.id.welcome_simple);
    }

    /**
     *图签按钮单击跳转
     * @param view
     */
    public void intentLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
