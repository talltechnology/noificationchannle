package com.example.wxy.noificationchannle.login;


import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.login.base.LoginBean;
import com.example.wxy.noificationchannle.main.Main;
import com.example.wxy.noificationchannle.utlis.Shred;
import com.mob.MobSDK;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginVCallBack {

    public boolean flag;
    @BindView(R.id.user_code_img)
    ImageView icon;
    @BindView(R.id.login_sum)
    public Button login_submite;
    @BindView(R.id.box_check)
    public CheckBox box;

    @BindView(R.id.input_phone)
    public EditText phone;

    @BindView(R.id.input_auth_code)
    public EditText code_pwd;
    @BindView(R.id.input_password)
    public EditText input_password;

    @BindView(R.id.btn_code)
    public Button login_auth_code;

    @BindView(R.id.login_toggles)
    public TextView toggle;

    @BindView(R.id.qq)
    public ImageView qq;

    @BindView(R.id.wx)
    public ImageView wx;


    private Unbinder unbinder;
    private EventHandler eh;
    private Shred shred;
    private LoginP loginP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        initDtqx();
        intData();

    }

    /**
     * 动态权限6.0
     */
    private void initDtqx() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    /**
     * 初始化对象
     */
    private void intData() {
        shred = new Shred(this, "user");
        loginP = new LoginP();
        loginP.Attach(new LoginM(),this);
        initEnvenHandler();
        EditSetValue();
        //添加监听
        listnener();
    }

    private void listnener() {
        //设置监听
        login_auth_code.setOnClickListener(this);
        login_submite.setOnClickListener(this);
        toggle.setOnClickListener(this);
        qq.setOnClickListener(this);
        //配置存取

        //改变监听
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    input_password.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                } else {
                    input_password.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    private void EditSetValue() {
        String name = shred.getString("name");
        String pwd = shred.getString("pwd");
        phone.setText(name);
        input_password.setText(pwd);
    }

    /**
     * 校验验证码回调
     */
    private void initEnvenHandler() {
        // TODO: 2018/7/6  验证监听
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                result = 1;
                Log.i("yyy", "yyy:" + event + "    result:" + result + "    data:" + data.toString());
                switch (event) {
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE://短信验证是否成功的回调
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Log.i("yyy", "验证成功");//为了简洁就直接打印log了
                            startintent();
                        } else {
                            Log.i("yyy", "验证失败");//为了简洁就直接打印log了
                        }
                        return;
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE://短信是否向手机发送成功的回调
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Log.i("yyy", "获取验证码成功");//为了简洁就直接打印log了
                            //默认的智能验证是开启的,我已经在后台关闭
                        } else {
                            Log.i("yyy", "获取验证码失败");//为了简洁就直接打印log了
                        }
                        return;
                }
            }
        };
        SMSSDK.registerEventHandler(eh);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_code://获取验证码
                SMSSDK.getVerificationCode("86", phone.getText().toString());
                break;
            case R.id.login_sum://登录
                if (!flag) {
                    Toast.makeText(LoginActivity.this, "验证码登录" , Toast.LENGTH_LONG).show();
                    SMSSDK.submitVerificationCode("86", phone.getText().toString(),code_pwd.getText().toString() );
                } else {
                    Toast.makeText(LoginActivity.this, "密码登录"+ input_password.getText().toString(), Toast.LENGTH_LONG).show();
                  loginP.loginRequst(phone.getText().toString(),input_password.getText().toString());
                }
                break;
            case R.id.login_toggles://登录切换
                taggle();
                break;
            case R.id.qq://QQ登录
                Toast.makeText(LoginActivity.this, "QQ登录" ,Toast.LENGTH_LONG).show();
                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }

    /**
     * 切换
     */
    private void taggle() {
        flag = !flag;
        if (flag) {
            login_auth_code.setVisibility(View.GONE);
            icon.setImageResource(R.mipmap.pass_icon);
            code_pwd.setVisibility(View.GONE);
            box.setVisibility(View.VISIBLE);
            input_password.setVisibility(View.VISIBLE);
        } else {
            login_auth_code.setVisibility(View.VISIBLE);
            icon.setImageResource(R.mipmap.yzm);
            box.setVisibility(View.GONE);
            code_pwd.setVisibility(View.VISIBLE);
            input_password.setVisibility(View.GONE);
        }
    }

    /**
     * 跳转主页
     */
    public void startintent() {
        startActivity(new Intent(this, Main.class));
        finish();
    }

    //登录监听
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
          startintent();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    //QQ新浪
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }



    /**
     * 请求失败监听
     *
     * @param t
     * @param <T>
     */
    @Override
    public <T> void faliu(T t) {
        Log.e("LoginActivty", "" + t);
    }

    /**
     * 登录成功回调
     *
     * @param bean
     */
    @Override
    public void seecc(LoginBean bean) {
        String code = bean.getCode();
        Toast.makeText(this,""+code,Toast.LENGTH_LONG).show();
        if ("0".equals(code)) {
            shred.setString("phone", phone.getText().toString());
            shred.setString("pwd", input_password.getText().toString());
            LoginBean.DataBean data = bean.getData();
            if (data != null) {
                String token = data.getToken();
                shred.setString("token", token);
                startintent();
            }
        }
    }
    /**
     * 关闭资源
     */
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
        eh.onUnregister();
        loginP.Dettch();
    }
}
