package com.example.wxy.noificationchannle.fragmens.intentactivty;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.fragmens.bean.BeanSpace;
import com.example.wxy.noificationchannle.fragmens.moder.SpaceM;
import com.example.wxy.noificationchannle.fragmens.presenter.SpacecpP;
import com.example.wxy.noificationchannle.utlis.Bround;
import com.sina.weibo.sdk.utils.ImageUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;

public class SpacecpActivty extends AppCompatActivity implements View.OnClickListener, SpacepCallback {

    private Unbinder unbinder;
    @BindView(R.id.my_image)
    public ImageView iamge;
    @BindView(R.id.my_image3)
    public ImageView iamge3;
    public final int TYPE_TAKE_PHOTO = 1;//获取相机
    public final int CODE_TAKE_PHOTO = 2;//获取相册
    private DisplayMetrics displayMetrics;
    private Button btn_up;
    private Button btn_chmare;
    private Button btn_cancel;
    private PopupWindow popupWindow;
    private File file2;
    @BindView(R.id.btn_name)
    public Button btn_name;
    private SpacecpP sapce;
    @BindView(R.id.space_itent)
    public TextView itent;
    private String realPathFromUriAboveApi19;
    @BindView(R.id.Space_tv)
    public TextView Space_tv;
    @BindView(R.id.Space_phone)
    public EditText Space_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spacecp_activty);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//透明色
            getSupportActionBar().hide();
        }

        unbinder = ButterKnife.bind(this);
        iamge.setImageBitmap(Bround.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.mipmap.mytox2), 200));
        iamge3.setImageBitmap(Bround.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.mipmap.phone3), 200));
        iamge3.setOnClickListener(this);
        iamge.setOnClickListener(this);
        //
        btn_name.setOnClickListener(this);
        //
        displayMetrics = getResources().getDisplayMetrics();
        sapce = new SpacecpP();
        sapce.Attach(new SpaceM(), this);
        itent.setOnClickListener(this);
        //
        Space_tv.setOnClickListener(this);
        Space_phone.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        sapce.Dettch();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_image:
                popupwindo();
                break;
            case R.id.btn_up://相册
                photoAlbum();
                break;
            case R.id.btn_cancel://关闭
                popupWindow.dismiss();
                setBackgroundAlpha(1);
                break;
            case R.id.btn_chamra://相机

                break;
            case R.id.btn_name:
                sapce.requst("71", file2);
                break;
            case R.id.space_itent:
                finish();
                break;
            case R.id.Space_tv:
                Space_tv.setVisibility(View.GONE);
                Space_phone.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 隐士打开相册
     */
    private void photoAlbum() {
        Intent photo = new Intent(Intent.ACTION_GET_CONTENT);
        photo.setType("image/*");
        startActivityForResult(photo, TYPE_TAKE_PHOTO);
    }

    //弹框
    private void popupwindo() {
        View inflate = getLayoutInflater().inflate(R.layout.popupwindow, null, false);
        popupWindow = new PopupWindow(inflate, displayMetrics.widthPixels, displayMetrics.heightPixels);
        setBackgroundAlpha(0.2f);//设置屏幕透明度
        popupWindow.showAsDropDown(iamge, displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2);
        btn_up = inflate.findViewById(R.id.btn_up);//调取相册
        btn_chmare = inflate.findViewById(R.id.btn_chamra);//相册
        btn_cancel = inflate.findViewById(R.id.btn_cancel);//取消
        //添加监听
        btn_up.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_chmare.setOnClickListener(this);
    }

    /**
     * @param backgroundAlpha
     */
    public void setBackgroundAlpha(float backgroundAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = backgroundAlpha;
        getWindow().setAttributes(lp);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TYPE_TAKE_PHOTO) {
            setBackgroundAlpha(1);
            Uri file = data.getData();
            realPathFromUriAboveApi19 = getRealPathFromUriAboveApi19(this, file);
            Bitmap bitmap1 = Bround.toRoundCorner(BitmapFactory.decodeFile(realPathFromUriAboveApi19), 10);
            iamge.setImageBitmap(bitmap1);
            popupWindow.dismiss();
        }

    }

    private void startImageZoom(Uri uri) {
        //构建隐式Intent来启动裁剪程序
        Intent intent = new Intent("com.android.camera.action.CROP");
        //设置数据uri和类型为图片类型
        intent.setDataAndType(uri, "image/*");
        //显示View为可裁剪的
        intent.putExtra("crop", true);
        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //输出图片的宽高均为150
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //裁剪之后的数据是通过Intent返回
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 0);
    }

    /**
     * 解析Intent.getdata()得到的uri为String型的filePath * * @param contentUri * @return
     */

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public <T> void faliu(T t) {
        Toast.makeText(this, "" + t, Toast.LENGTH_LONG).show();
    }

    @Override
    public void Seecc(BeanSpace bean) {
        if ("0".equals(bean.getCode())) {

        } else {

        }
    }

    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     *
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
