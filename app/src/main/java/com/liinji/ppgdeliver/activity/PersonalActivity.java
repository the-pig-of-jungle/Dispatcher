package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.MsgOutline;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.sendreport.SendReportActivity;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.lunger.bidirtabledemo.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/26.
 */
public class PersonalActivity extends BaseDarkStatusBarActivity {
    @BindView(R.id.sender_tel)
    TextView mSenderTel;
    @BindView(R.id.message)
    TextView mMessage;
    @BindView(R.id.change_pwd)
    TextView mChangePwd;
    @BindView(R.id.setting)
    TextView mSetting;
    @BindView(R.id.sender)
    TextView mSender;
    @BindView(R.id.unread_msg)
    TextView unreadMsg;
    @BindView(R.id.my_report)
    TextView mMyReport;


    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("个人中心");
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mSenderTel.setText(SharePrefUtils.getUserInfo().getPhone());
        mSender.setText(SharePrefUtils.getUserInfo().getUserName());

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        HttpTools.hasUnreadMsg(this, this);
    }

    @OnClick({R.id.message, R.id.change_pwd, R.id.setting,R.id.my_report,R.id.branch_recharge,R.id.account_for_confirm,R.id.send_report})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message:

                IntentUtils.startActivity(this, MessageListActivity.class);
                break;
            case R.id.change_pwd:
                IntentUtils.startActivity(this, ModifyPswActivity.class);
                break;
            case R.id.setting:
                IntentUtils.startActivity(this, SettingActivity.class);
                break;
            case R.id.my_report:
                IntentUtils.startActivity(this, MainActivity.class);
                break;
            case R.id.account_for_confirm:
                IntentUtils.startActivity(this, AccountForConfirmActivity.class);
                break;
            case R.id.branch_recharge:
                IntentUtils.startActivity(this,BranchRechargeActivity.class);
                break;
            case R.id.send_report:
                IntentUtils.startActivity(this, SendReportActivity.class);
                break;
        }
    }


    private int mUnreadNum;

    private Intent mBackData = new Intent();

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        MsgOutline msgOutline = ((List<MsgOutline>) bean.getReturnData()).get(0);

        mUnreadNum = msgOutline.getUnReadCount();
        Logger.d("未读消息数：" + mUnreadNum);
        if (mUnreadNum == 0) {
            unreadMsg.setVisibility(View.GONE);
        } else {
            unreadMsg.setVisibility(View.VISIBLE);
            unreadMsg.setText(mUnreadNum + "");
        }

        mBackData.putExtra("unread_num", mUnreadNum);
        setResult(RESULT_OK, mBackData);
    }



}
