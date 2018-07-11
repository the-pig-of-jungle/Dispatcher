package com.liinji.ppgdeliver.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.liinji.ppgdeliver.http.HttpRequestListener;
import com.liinji.ppgdeliver.http.HttpTools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShadowMoon on 2017/3/9.
 */
public class BitmapTools {
    /**
     * 将bitmap转化为base64字节流
     *
     * @param bitmap
     * @return
     */
    public static void bitmapToBase64(final Activity context, final Bitmap bitmap, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (bitmap == null)
                    return ;

                String base64 = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();

                    byte[] bitmapBytes = byteArrayOutputStream.toByteArray();
                    base64 = android.util.Base64.encodeToString(bitmapBytes, android.util.Base64.DEFAULT);
                    final String finalBase6 = base64;
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onConvertFinish(finalBase6);
                        }
                    });

                } catch (IOException e) {
                } finally {
                    try {
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.flush();
                            byteArrayOutputStream.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }).start();


    }

    public static void uploadSignPic(final Activity context, final String order, final List<Bitmap> bitmaps) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (bitmaps == null || bitmaps.isEmpty()){
                    return ;
                }
                final List<String> pics = new ArrayList<String>();
                for (int i = 0;i < bitmaps.size();i++){
                    String base64 = null;
                    ByteArrayOutputStream byteArrayOutputStream = null;
                    try {

                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmaps.get(i).compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();

                        byte[] bitmapBytes = byteArrayOutputStream.toByteArray();
                        base64 = android.util.Base64.encodeToString(bitmapBytes, android.util.Base64.DEFAULT);

                    } catch (IOException e) {
                    } finally {
                        try {
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.flush();
                                byteArrayOutputStream.close();
                            }
                        } catch (IOException e) {
                        }
                    }

                    pics.add(base64);
                }

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HttpTools.uploadSignPic(context, (HttpRequestListener) context,order,pics);
                    }
                });


            }
        }).start();


    }



    /**
     * 压缩Bitmap
     *
     * @param image
     * @return
     */
    public static Bitmap compressBitmap(Bitmap image) {
        if (image == null)
            return null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        return BitmapFactory.decodeStream(byteArrayInputStream, null, null);
    }

    public static Bitmap getSmallBitmap(Bitmap bitmap, int max_width, int max_height) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        int width = 0;
        int height = 0;
        int sampleSize = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        height = options.outHeight;
        width = options.outWidth;

        while ((height / sampleSize > max_height) || (width / sampleSize > max_width)) {
            sampleSize *= 2;
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    public static void setSmallBitmap(Bitmap bitmap, ImageView imageView) {
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        imageView.setImageBitmap(getSmallBitmap(bitmap, width, height));
    }

    /**
     * 从图片的文件地址获取bitmap对象
     *
     * @param pathName
     * @return
     */
    public static Bitmap getBitmap(String pathName) {
        return getBitmap(pathName, -1);
    }

    /**
     * 从图片的文件地址获取bitmap对象
     *
     * @param pathName
     * @param inSampleSize (缩小倍数 )
     * @return
     */
    public static Bitmap getBitmap(String pathName, int inSampleSize) {
        if (inSampleSize > 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.inSampleSize = inSampleSize;
            return BitmapFactory.decodeFile(pathName, options);
        }
        return BitmapFactory.decodeFile(pathName);
    }

    /**
     * 从Uri里获取图片
     *
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap getBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (Exception e) {
        }
        return bitmap;
    }


    public  interface CallBack{
        void onConvertFinish(String result);
    }

    public interface MultipleCallback{
        void onConvertFinish(List<String> result);
    }
}
