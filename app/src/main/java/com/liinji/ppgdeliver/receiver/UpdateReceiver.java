package com.liinji.ppgdeliver.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.liinji.ppgdeliver.configure.AppConstants;
import com.liinji.ppgdeliver.manager.ActivityManager;
import com.liinji.ppgdeliver.manager.SharedPreManager;
import com.liinji.ppgdeliver.tools.ApplicationUtil;
import com.liinji.ppgdeliver.tools.CustomDialogTool;
import com.liinji.ppgdeliver.tools.IntentUtils;

import java.io.File;

/**
 * Created by YUEYINGSK on 2016/9/6.
 */
public class UpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        long refernece = SharedPreManager.getLong(AppConstants.DOWNLOADID);
        String downloadnowtime = SharedPreManager.getString(AppConstants.DOWNLOADVERSIONNAME);
        if (refernece == myDwonloadID) {
            CustomDialogTool.dissProgressDialog();
            if (!TextUtils.isEmpty(downloadnowtime)) {
                Uri downloadFileUri = Uri.fromFile(new File("/sdcard/Download/" + ApplicationUtil.getAppName(context) + "POS-" + downloadnowtime + ".apk"));
                IntentUtils.setUpApk(context, downloadFileUri);
                if (ActivityManager.getSingleManager() != null) {
                    ActivityManager.getSingleManager().popAllActivity();

                }
            }
        }
    }
}
