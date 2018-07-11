package com.liinji.ppgdeliver.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JPushInterface.resumePush(MyApplication.sContext);

        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_welcome);
        Class clazz = SharePrefUtils.getUserInfo() == null ? LoginActivity.class : HomeActivity.class;
        if (SharePrefUtils.getUserInfo() != null){
            JPushInterface.setAlias(this, SharePrefUtils.getUserInfo().getUserId(), new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {

                }
            });
        }

        IntentUtils.startActivityDelayAndFinish(this,clazz,1500);
    }
}
