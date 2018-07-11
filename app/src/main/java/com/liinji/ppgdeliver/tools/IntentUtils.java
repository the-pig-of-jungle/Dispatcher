package com.liinji.ppgdeliver.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Description:Intent工具类
 * <p/>
 * Title: IntentUtils.java
 * <p/>
 * Copyright: Copyright (c) 2015
 * author Administrator
 * <p/>
 * Date 2015-4-3
 * <p/>
 * Version 1.0
 */
public class IntentUtils {
    /**
     * 实现页面之间的跳转
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 实现页面之间的跳转并关闭之前页面
     *
     * @param context
     * @param cls
     */
    public static void startActivityAndFinish(Activity context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        context.finish();
    }

    /**
     * 跳转获取结果
     *
     * @param context
     * @param cls
     * @param requsetcode
     */
    public static void startActivityForResult(Activity context, Class<?> cls, int requsetcode) {
        Intent intent = new Intent(context, cls);
        context.startActivityForResult(intent, requsetcode);
    }


    /**
     * 跳转action
     *
     * @param context
     * @param action
     */
    public static void startActivityAction(Context context, String action) {
        Intent intent = new Intent(action);
        context.startActivity(intent);
    }

    /**
     * 延迟启动页面
     *
     * @param context
     * @param cls
     * @param delaytime
     */
    public static void startActivityDelay(final Context context, final Class<?> cls, final long delaytime) {
        new Thread() {
            public void run() {
                try {
                    sleep(delaytime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, cls);
                context.startActivity(intent);
            }

            ;
        }.start();
    }

    /**
     * 延迟启动页面并关闭当前页面
     *
     * @param context
     * @param cls
     * @param delaytime
     */
    public static void startActivityDelayAndFinish(final Activity context, final Class<?> cls, final long delaytime) {
        new Thread() {
            public void run() {
                try {
                    sleep(delaytime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, cls);
                context.startActivity(intent);
                context.finish();
            }

            ;
        }.start();
    }

    /**
     * 安装apk
     *
     * @param context
     * @param uri
     */
    public static void setUpApk(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 获取联系人信息
     *
     * @param context
     * @param requestcode
     */
    public static void getContactInfo(Activity context, int requestcode) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        context.startActivityForResult(intent, requestcode);
    }
}
