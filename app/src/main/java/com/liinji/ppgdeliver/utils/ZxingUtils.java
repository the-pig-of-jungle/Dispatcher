package com.liinji.ppgdeliver.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.liinji.ppgdeliver.tools.EncodingHandler;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.InputStream;


/**
 * Created by Administrator on 2017/3/14.
 */
public class ZxingUtils {

    public static Bitmap createBarCode2dWithImage(String text, int width, int height, String path) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return CodeUtils.createImage(text, width, height, BitmapFactory.decodeFile(path));
    }


    public static Bitmap createBarCode2d(String text, int width, int height) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return CodeUtils.createImage(text, width, height, null);
    }

    public static Bitmap createBarCode(String text, int width, int height) {
        Bitmap bitmap = null;
        try {
            bitmap = EncodingHandler.createBarCode(text, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    public static Bitmap getBitmap(Context context, int id) {
        InputStream is = context.getResources().openRawResource(id);
        return BitmapFactory.decodeStream(is);
    }

    public static Bitmap getBitmap(Context context, String url) {
        Bitmap bitmap = null;
        Bitmap bmap = null;
        if (!TextUtils.isEmpty(url)) {
            try {
                bitmap = Glide.with(context).load(url).asBitmap().into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int newWid = 164;
                int newHei = 52;
                float wid = (float) newWid / width;
                float hei = (float) newHei / height;
                Matrix matrix = new Matrix();
                matrix.postScale(wid, hei);
                bmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            if (bmap == null) {
                return null;
            }
        }
        return bmap;
    }


    public static Bitmap setScale(Bitmap bitmap, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
