package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/17.
 */
public class ApplyActivity extends BaseLightStatusBarActivity {

    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.amount)
    TextView mAmount;
    @BindView(R.id.error_tip)
    TextView mErrorTip;


    @Override
    protected int returnContentView() {
        return R.layout.activity_apply;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar_title.setText("申请交账");
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        toolbar.inflateMenu(R.menu.apply_aciton_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_record:
                        IntentUtils.startActivity(ApplyActivity.this, BillRecordActivity.class);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        HttpTools.howManyToSubmit(this, this);
    }

    @OnClick(R.id.btn_submit)
    public void onClick() {
        mErrorTip.setText("");

        String amount = mAmount.getText().toString().trim();
        Logger.d("我爱你" + Utils.currencyStrToNumber(amount));
        HttpTools.applySubmitBill(this, Utils.currencyStrToNumber(amount) + "", this);

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.APPLY_SUBMIT_BILL:
                Intent intent = new Intent(this, BillSubmitResultActivity.class);
                intent.putExtra("amount", mAmount.getText().toString().trim());
                startActivity(intent);
                finish();
                break;
            case HttpRequestType.HOW_MANY_TO_SUBMIT:

                mAmount.setText(Utils.getCurrencyStr(((Float) bean.getReturnData())));
                if (mAmount.getText().toString().trim().equals(Utils.getCurrencyStr(0))) {

                    mErrorTip.setText("没有相关账目需要提交");
                    mErrorTip.setTextColor(Color.parseColor("#FF4500"));
                    mErrorTip.setEnabled(false);
                    mBtnSubmit.setEnabled(false);
                }else {
                    mErrorTip.setEnabled(true);
                    SpannableString spannableString = new SpannableString("查看明细");
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            Intent intent = new Intent(ApplyActivity.this,JiaozhangListActivity.class);
                            intent.putExtra("bill_id",0);
                            startActivity(intent);
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(ContextCompat.getColor(MyApplication.sContext,R.color.colorPrimary));
                        }
                    },0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mErrorTip.setMovementMethod(LinkMovementMethod.getInstance());
                    mErrorTip.setText(spannableString);
                    mBtnSubmit.setEnabled(true);
                }

                break;
        }
    }


    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type) {
            case HttpRequestType.HOW_MANY_TO_SUBMIT:
                mErrorTip.setText(bean != null ? bean.getReturnMsg() : "发生错误，请重试！");
                mErrorTip.setEnabled(false);
                mAmount.setText(Utils.getCurrencyStr(0));
                mBtnSubmit.setEnabled(false);

                break;

        }
    }


}
