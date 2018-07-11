package com.liinji.ppgdeliver.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.http.HttpNetUtils;
import com.liinji.ppgdeliver.http.HttpRequestListener;
import com.liinji.ppgdeliver.listener.NeedRefreshListener;
import com.liinji.ppgdeliver.listener.NetConnectLis;
import com.liinji.ppgdeliver.manager.ActivityManager;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.tools.StatusBarTools;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 状态栏为浅色，字体为深色，toolbar为浅色
 * Created by YUEYINGSK on 2016/8/11.
 */
public abstract class BaseLightStatusBarActivity extends AppCompatActivity implements NeedRefreshListener, HttpRequestListener, NetConnectLis,IBaseActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    protected TextView toolbar_title;
    protected Context mContext;
    public static NetConnectLis lis;

    private boolean mShowing;

    public boolean isShowing() {
        return mShowing;
    }

    public void setmShowing(boolean mShowing) {
        this.mShowing = mShowing;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(returnContentView());
        ButterKnife.bind(this);
        init();
        initData(savedInstanceState);
        initView(savedInstanceState);
    }

    private void init() {
        mContext = this;
        ActivityManager.getSingleManager().pushActivity(this);
        lis = this;

//        根据安卓版本改变状态栏图标颜色
        int result = StatusBarTools.StatusBarLightMode(this);
        if (result == 0) {
            StatusBarTools.setStatusBarColor(this, R.color.color_statusbar);
        }else{
            StatusBarTools.setStatusBarColor(this, R.color.white);
        }


        toolbar.setTitle("");
        toolbar_title.setTextColor(ContextCompat.getColor(this, R.color.white));
        toolbar.setBackgroundResource(R.color.white);
        toolbar.setNavigationIcon(R.drawable.ds_bk);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initToolbarStyle();

    }


    /**
     * 设置toolbar
     */
    protected void initToolbarStyle() {

    }


    /**
     * 返回layout res id 相当于调用setCotentView
     *
     * @return
     */
    protected abstract int returnContentView();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getSingleManager().popActivity(this);
        HttpNetUtils.cancelNetWork(this);
        SmartSnackbar.destroy(this);
    }

    /**
     * 获取activity的根view
     *
     * @return
     */
    protected View getRootView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }

    protected void myToast(String string) {
        SmartSnackbar.get(this).show(string);
    }



    @Override
    public void refresh() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mShowing = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShowing = false;
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {

    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {

    }


    @Override
    public void onNetChanged(boolean isnetconnected) {
        if (isnetconnected) {
            SmartSnackbar.dismiss();
        } else {
            SmartSnackbar.get(this).showIndefinite(getString(R.string.netconnected_failed), "设置", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivityAction(BaseLightStatusBarActivity.this, Settings.ACTION_SETTINGS);
                }
            });
        }
    }

    /**
     * 隐藏键盘
     */
    protected void hideKeyBoard() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
