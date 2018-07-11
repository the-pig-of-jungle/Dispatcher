package com.liinji.ppgdeliver.tools;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * glide简单方法的封装，根据自己功能需要加入新的方法
 * Created by ShadowMoon on 2017/3/9.
 */
public class GlideTools {
    /**
     * 一般加载图片，包括网络本地
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadImg(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);

    }

    /**
     * 一般加载图片，包括网络本地
     *
     * @param fragment
     * @param path
     * @param imageView
     */
    public static void loadImg(Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment).load(path).into(imageView);
    }

    /**
     * 一般加载图片，包括网络本地
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadImg(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file).into(imageView);
    }

    /**
     * 一般加载图片，包括网络本地
     *
     * @param fragment
     * @param file
     * @param imageView
     */
    public static void loadImg(Fragment fragment, File file, ImageView imageView) {
        Glide.with(fragment).load(file).into(imageView);
    }

    /**
     * 一般加载图片，包括网络本地
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadImg(Context context, String path, ImageView imageView, int placehoderimg, int errorimg) {
        Glide.with(context).load(path).placeholder(placehoderimg).error(errorimg).into(imageView);
    }

    /**
     * 一般加载图片，包括网络本地
     *
     * @param fragment
     * @param path
     * @param imageView
     */
    public static void loadImg(Fragment fragment, String path, ImageView imageView, int placehoderimg, int errorimg) {
        Glide.with(fragment).load(path).placeholder(placehoderimg).error(errorimg).into(imageView);
    }
}
