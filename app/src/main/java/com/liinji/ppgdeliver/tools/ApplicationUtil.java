package com.liinji.ppgdeliver.tools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

/**
 * Created by YUEYINGSK on 2016/8/16.
 */
public class ApplicationUtil {
    private static ApplicationInfo APPLICATION_INFO;
    private static PackageInfo PACKAGE_INFO;


    private static void initInfos(Context context) {
        try {
            APPLICATION_INFO = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
        }

        try {
            PACKAGE_INFO = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    public static String getAppName(Context context)//获取应用名称
    {
        if (APPLICATION_INFO != null) {

            int labelRes = APPLICATION_INFO.labelRes;
            return labelRes != 0 ? context.getString(labelRes) : "未知应用";
        } else {
            initInfos(context);
            if (APPLICATION_INFO != null) {

                int labelRes = APPLICATION_INFO.labelRes;
                return labelRes != 0 ? context.getString(labelRes) : "未知应用";
            } else {
                return "未知应用";
            }
        }
    }

    public static String getVersionName(Context context)//获取版本号
    {
        initInfos(context);
        if (PACKAGE_INFO != null) {

            return PACKAGE_INFO.versionName;
        } else {
            initInfos(context);
            if (PACKAGE_INFO != null) {

                return PACKAGE_INFO.versionName;
            } else {
                return "未知版本号";
            }
        }
    }

    public static String getPackageName(Context context)//获取包名
    {
        if (PACKAGE_INFO != null) {

            return PACKAGE_INFO.packageName;
        } else {
            initInfos(context);
            if (PACKAGE_INFO != null) {

                return PACKAGE_INFO.packageName;
            } else {
                return "未知版本号";
            }
        }
    }

    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        if (PACKAGE_INFO != null) {

            return PACKAGE_INFO.versionCode;
        } else {
            initInfos(context);
            if (PACKAGE_INFO != null) {

                return PACKAGE_INFO.versionCode;
            } else {
                return 0;
            }
        }
    }


    /**
     * 获取应用程序icon
     */
    public static int getApplicationIcon(Context context) {
        if (APPLICATION_INFO != null) {

            return APPLICATION_INFO.icon;
        } else {
            initInfos(context);
            if (APPLICATION_INFO != null) {

                return APPLICATION_INFO.icon;
            } else {
                return android.R.drawable.sym_def_app_icon;
            }
        }
    }

    /**
     * 获取Application内的metadata的对应key的值(P: 无论是哪种类型的数据都可以获取，并最终返回String类型)
     */
    public static String getApplicationInfoMetaData(Context context, String key) {

        if (APPLICATION_INFO != null) {

            return String.valueOf(APPLICATION_INFO.metaData.get(key));
        } else {
            initInfos(context);
            if (APPLICATION_INFO != null && APPLICATION_INFO.metaData != null) {

                return String.valueOf(APPLICATION_INFO.metaData.get(key));
            } else {
                return null;
            }
        }
    }

    public static String getActivityMetaData(Activity activity, String key) {

        ActivityInfo info = null;
        try {
            info = activity.getPackageManager()
                    .getActivityInfo(getComponentName(activity),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (info != null) {

            return String.valueOf(info.metaData.get(key));
        } else {
            return null;
        }
    }

    public static ComponentName getComponentName(Context context) {
        return new ComponentName(context, context.getClass());
    }

    /**
     * 获取imei
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getDeviceId();
    }
}
