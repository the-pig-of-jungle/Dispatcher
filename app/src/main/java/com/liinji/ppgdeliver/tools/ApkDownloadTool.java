package com.liinji.ppgdeliver.tools;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.VersionInfo;
import com.liinji.ppgdeliver.configure.AppConstants;
import com.liinji.ppgdeliver.manager.SharedPreManager;

/**
 * Created by YUEYINGSK on 2016/9/6.
 */
public class ApkDownloadTool {
    private static ApkDownloadTool apkDownloadTool;


    public static ApkDownloadTool getInstance() {
        if (apkDownloadTool == null) {
            apkDownloadTool = new ApkDownloadTool();
        }
        return apkDownloadTool;
    }

    /**
     * 检查版本
     *
     * @param context
     * @param bean
     */
    public void checkUpdate(final Context context, final BaseBean bean) {
        final VersionInfo versionInfo = (VersionInfo) bean.getReturnData();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本更新");
        if (versionInfo.getIsUpdate() == AppConstants.TRUE) {
            builder.setMessage("发现新版本: " + versionInfo.getVersionName() + "\n" + versionInfo.getVersionInfo());
            builder.setPositiveButton("立刻升级", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    downLoad(context, versionInfo);
                    if (versionInfo.getForceUpdate() == AppConstants.TRUE) {
                        CustomDialogTool.showProgressDialogNoCancel(context);
                    }
                }
            });

            if (versionInfo.getForceUpdate() != AppConstants.TRUE) {
                builder.setNegativeButton("以后再说", null);
            }
            builder.setCancelable(false);
            builder.show();


        } else {
            builder.setMessage(bean.getReturnMsg());
            builder.setPositiveButton("确定", null);
            builder.show();
        }
    }

    /**
     * 初始化检查版本
     *
     * @param context
     * @param bean
     */
    public void initCheckUpdate(final Context context, final BaseBean bean) {
        final VersionInfo versionInfo = (VersionInfo) bean.getReturnData();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本更新");
        if (versionInfo.getIsUpdate() == AppConstants.TRUE) {
            builder.setMessage("发现新版本: " + versionInfo.getVersionName() + "\n" + versionInfo.getVersionInfo());
            builder.setPositiveButton("立刻升级", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    downLoad(context, versionInfo);
                    if (versionInfo.getForceUpdate() == AppConstants.TRUE) {
                        CustomDialogTool.showProgressDialogNoCancel(context);
                    }
                }
            });

            if (versionInfo.getForceUpdate() != AppConstants.TRUE) {
                builder.setNegativeButton("以后再说", null);
            }
            builder.setCancelable(false);
            builder.show();


        }
    }


    public void downLoad(Context context, VersionInfo versionInfo) {
        String nowtime = System.currentTimeMillis() + "";
        //取得系统的下载服务
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        //创建下载请求对象
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionInfo.getDownLoadUrl()));
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ApplicationUtil.getAppName(context) + "POS-" + nowtime + ".apk");
        request.setNotificationVisibility(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(ApplicationUtil.getAppName(context) + "下载，下载完成后请点击安装");
        request.setDescription("版本号：" + versionInfo.getVersionName());
        request.setVisibleInDownloadsUi(true);
        request.setMimeType("application/vnd.android.package-archive");
        long refernece = downloadManager.enqueue(request);
        SharedPreManager.putLong(AppConstants.DOWNLOADID, refernece);
        SharedPreManager.putString(AppConstants.DOWNLOADVERSIONNAME, nowtime);
    }

}
