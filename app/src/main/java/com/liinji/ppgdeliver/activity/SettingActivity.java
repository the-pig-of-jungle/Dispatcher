package com.liinji.ppgdeliver.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.liinji.ppgdeliver.BuildConfig;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.SettingAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.manager.ActivityManager;
import com.liinji.ppgdeliver.manager.GreenDaoManager;
import com.liinji.ppgdeliver.tools.ApkDownloadTool;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.tools.WebTools;
import com.liinji.ppgdeliver.utils.SharePrefUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SettingActivity extends BaseDarkStatusBarActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbarId;
    @BindView(R.id.exit_login)
    TextView mExitLogin;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected int returnContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("设置");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> items = Arrays.asList(getResources().getStringArray(R.array.setting_itmes));
        final SettingAdapter settingAdapter = new SettingAdapter(items);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (i) {
                    case 0:
                        IntentUtils.startActivity(SettingActivity.this, AboutActivity.class);
                        break;
                    case 1:
                        IntentUtils.startActivity(SettingActivity.this, SuggestionActivity.class);
                        break;
                    case 2:
                        if (!BuildConfig.IS_PRE_RELEASE){
                            HttpTools.versionUpdate(SettingActivity.this, SettingActivity.this);
                        }

                        break;
                    case 3:
                        IntentUtils.startActivity(SettingActivity.this,UserContractActivity.class);
                        break;
                    case 4:
                        IntentUtils.startActivity(SettingActivity.this,BluetoothActivity.class);
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(settingAdapter);


    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick(R.id.exit_login)
    public void onClick() {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("提示")
                .setContentText("确定退出登录吗？\n")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        SharePrefUtils.storeUserInfo(null);
                        SharePrefUtils.storeJPushClientAlias(null);
                        GreenDaoManager.getInstance().getDaoSession().getBranchInfoDao().deleteAll();
                        IntentUtils.startActivity(SettingActivity.this, LoginActivity.class);
                        WebTools.clearWebViewCookies(MyApplication.sContext);
                        ActivityManager.getSingleManager().popAllActivityExceptOne(LoginActivity.class);
                    }
                }).setCancelText("取消");

        dialog.setCanceledOnTouchOutside(true);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onShow(DialogInterface dialog) {
                ((Dialog) dialog).findViewById(R.id.confirm_button).setBackground(ContextCompat.getDrawable(SettingActivity.this, R.drawable.round_rect__green));
            }
        });

        dialog.show();

    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        ApkDownloadTool.getInstance().checkUpdate(this, bean);
    }


}
