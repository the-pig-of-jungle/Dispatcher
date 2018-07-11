package com.liinji.ppgdeliver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.liinji.ppgdeliver.activity.BaseDarkStatusBarActivity;
import com.liinji.ppgdeliver.http.NetWorkCon;
import com.liinji.ppgdeliver.listener.NetConnectLis;


/**
 * Created by YUEYINGSK on 2016/8/24.
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {
    private NetConnectLis lis = BaseDarkStatusBarActivity.lis;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (lis != null){
            lis.onNetChanged(NetWorkCon.isConnectInternet(context));
        }

    }
}
