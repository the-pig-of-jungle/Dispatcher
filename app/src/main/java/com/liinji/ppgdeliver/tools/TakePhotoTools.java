package com.liinji.ppgdeliver.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.liinji.ppgdeliver.R;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

/**
 * Created by ShadowMoon on 2017/3/9.
 */
public class TakePhotoTools {
    // 自定义图片加载器
    private static ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {

            GlideTools.loadImg(context, path, imageView);
        }
    };

    /**
     * 单选图片
     *
     * @param context
     * @param requestcode
     */
    public static void toGetPhotoSingle(Context context, int requestcode, int aspectX,
                                        int aspectY,
                                        int outputX,
                                        int outputY) {
        // 自由配置选项
        ImgSelConfig config = new ImgSelConfig.Builder(context, loader)
                // 是否多选, 默认true
                .multiSelect(false)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.TRANSPARENT)
                // “确定”按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                // 返回图标ResId
//                .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .backResId(R.drawable.ic_back)
                // 标题
                .title("选择图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(ContextCompat.getColor(context, R.color.colorPrimary))
                // 裁剪大小。needCrop为true的时候配置
//                .cropSize(aspectX, aspectY, outputX, outputY)
                .needCrop(false)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(9)
                .build();
        // 跳转到图片选择器
        ImgSelActivity.startActivity((Activity) context, config, requestcode);
    }

    public static void toGetPhotoMultiple(Context context, int requestcode, int aspectX,
                                          int aspectY,
                                          int outputX,
                                          int outputY, int num) {
        // 自由配置选项
        ImgSelConfig config = new ImgSelConfig.Builder(context, loader)
                // 是否多选, 默认true
                .multiSelect(true)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.TRANSPARENT)
                // “确定”按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                // 返回图标ResId
//                .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .backResId(R.drawable.ic_back)
                // 标题
                .title("选择图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(ContextCompat.getColor(context, R.color.colorPrimary))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(aspectX, aspectY, outputX, outputY)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(false)
                // 最大选择图片数量，默认9
                .maxNum(num)

                .build();
        // 跳转到图片选择器
        ImgSelActivity.startActivity((Activity) context, config, requestcode);
    }
}
