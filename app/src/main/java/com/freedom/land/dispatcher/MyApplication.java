package com.freedom.land.dispatcher;

import android.app.Application;

import com.mastersdk.android.NewMasterSDK;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("http://muv5.com:9991");
        addresses.add("http://f3gk.com:9991");
        addresses.add("http://2sur.com:9991");
        addresses.add("http://jc4x.com:9991");
        addresses.add("http://y64g.com:9991");
        NewMasterSDK.init(MainActivity.class,addresses,this);
    }
}
