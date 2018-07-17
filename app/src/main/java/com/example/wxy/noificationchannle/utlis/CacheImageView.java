package com.example.wxy.noificationchannle.utlis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by WXY on 2018/7/12.
 */

public class CacheImageView {

    /**
     * 压缩  宽高
     * @param filePath
     * @param destWidth
     * @param destHeight
     * @return
     */
    public static Bitmap getBitmap(String filePath, int destWidth, int destHeight) {
         //第一次采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        //该属性设置为true只会加载图片的边框进来，并不会加载图片具体的像素点
        options.inJustDecodeBounds = true;
        //第一次加载图片，这时只会加载图片的边框进来，并不会加载图片中的像素点
        BitmapFactory.decodeFile(filePath, options);
        //获得原图的宽和高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //定义缩放比例
        int sampleSize = 1;
        while (outHeight / sampleSize > destHeight || outWidth / sampleSize > destWidth) {
            //如果宽高的任意一方的缩放比例没有达到要求，都继续增大缩放比例
            //sampleSize应该为2的n次幂，如果给sampleSize设置的数字不是2的n次幂，那么系统会就近取值
            sampleSize *= 2;
        }
       //至此，第一次采样已经结束，我们已经成功的计算出了sampleSize的大小
       //二次采样开始
       //二次采样时我需要将图片加载出来显示，不能只加载图片的框架，因此inJustDecodeBounds属性要设置为false
        options.inJustDecodeBounds = false;
       //设置缩放比例
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
       //加载图片并返回
        return BitmapFactory.decodeFile(filePath, options);
    }

    //质量压缩
    public static Bitmap setImage3(InputStream inputStream,int options) {
        Bitmap bitmap1=null;
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream bos =new ByteArrayOutputStream();
            // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到bos中
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
            //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            while ( bos.toByteArray().length / 1024>100) {
                //重置bos即清空bos
                bos.reset();
                //这里压缩options%，把压缩后的数据存放到baos中
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, bos);
                //每次都减少10
                options -= 10;
            }
            //把压缩后的数据bos存放到ByteArrayInputStream中
            ByteArrayInputStream isBm = new ByteArrayInputStream(bos.toByteArray());
            //把ByteArrayInputStream数据生成图片
            bitmap1 = BitmapFactory.decodeStream(isBm, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap1;
    }

}
