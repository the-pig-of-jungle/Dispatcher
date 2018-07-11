package com.liinji.ppgdeliver.listener;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.activity.OfflinePay;
import com.liinji.ppgdeliver.activity.OnlinePayActivity;
import com.liinji.ppgdeliver.bean.DebitConfig;
import com.liinji.ppgdeliver.bean.OfflineInfo;
import com.liinji.ppgdeliver.http.HttpRequestListener;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;


/**
 * Created by Administrator on 2017/3/23.
 */
public class PayItemClickListener implements View.OnClickListener {

    private PopupWindow mPopupWindow;
    private OfflinePay mContext;
    private OfflineInfo mOfflineInfo;
    private AlertDialog mAlertDialog;

    public PayItemClickListener(OfflinePay activity, PopupWindow popupWindow, OfflineInfo offlineInfo) {
        mPopupWindow = popupWindow;
        mContext = activity;
        mOfflineInfo = offlineInfo;
    }

    public void setOfflineInfo(OfflineInfo offlineInfo) {
        mOfflineInfo = offlineInfo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.online_pay:
                Intent intent = new Intent(((Context) mContext), OnlinePayActivity.class);
                intent.putExtra("order", mOfflineInfo.getOrderNumber());
                intent.putExtra("sign_remarks",mOfflineInfo.getSignRemarks());
                intent.putExtra("order_id",mOfflineInfo.getOrderId());
                ((Activity) mContext).startActivityForResult(intent, 222);
                mPopupWindow.dismiss();
                break;
            case R.id.offline_pay:
                mPopupWindow.dismiss();
                getDialog().show();
                break;
            case R.id.cancel_pay:
                mPopupWindow.dismiss();
                break;
        }
    }

    public void onActivityExit() {
        mContext = null;
        mPopupWindow = null;
    }

    private View mContent;

    public AlertDialog getDialog() {
        if (mAlertDialog == null) {

            mContent = LayoutInflater.from(((Context) mContext)).inflate(R.layout.cash_pay_dilog, null);
            ((TextView) mContent.findViewById(R.id.deliver_fee)).setText(Utils.getCurrencyStr(mOfflineInfo.getDeliverFee()));
            ((TextView) mContent.findViewById(R.id.daishou_fee)).setText(Utils.getCurrencyStr(mOfflineInfo.getDaishouFee()));
            final CheckBox daishou = (CheckBox) mContent.findViewById(R.id.daishou_fee_icon);
            final CheckBox yunfee = (CheckBox) mContent.findViewById(R.id.deliver_fee_icon);
            final TextView totalFee = (TextView) mContent.findViewById(R.id.total_fee);
            final View yunFeeLine = mContent.findViewById(R.id.yunfei_line);
            final View daishouLine = mContent.findViewById(R.id.daishou_line);
            DebitConfig debitConfig = mContext.getDebitConfig();
            totalFee.setText("合计：" + Utils.getCurrencyStr(mOfflineInfo.getDaishouFee() + mOfflineInfo.getDeliverFee()));
            if (mOfflineInfo.isJijie()) {
                process(daishou, yunfee, totalFee,yunFeeLine,daishouLine, debitConfig.getJJMode());
            } else {
                process(daishou,yunfee,totalFee,yunFeeLine,daishouLine, debitConfig.getNormalMode());
            }




            mContent.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAlertDialog.dismiss();
                }
            });

            mContent.findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float all = Utils.currencyStrToNumber(((TextView) mContent.findViewById(R.id.total_fee)).getText().toString().trim().substring(3));
                    Logger.d("总金额：" + all);
                    int payWay = 0;
                    if (mOfflineInfo.isJijie()){
                        switch (mContext.getDebitConfig().getJJMode()){
                            case DebitConfig.MODE_0_0_0:
                                payWay = 0;
                                break;
                            case DebitConfig.MODE_0_0_1:
                                if (daishouLine.getVisibility() == View.VISIBLE){
                                    payWay = daishou.isChecked() ? 0 : 1;
                                }else {
                                    payWay = 0;
                                }

                                break;
                            case DebitConfig.MODE_0_1_0:
                                if (yunFeeLine.getVisibility() == View.VISIBLE){
                                    payWay = yunfee.isChecked() ? 0 : 2;
                                }else {
                                    payWay = 0;
                                }

                                break;
                            case DebitConfig.MODE_0_1_1:
                                if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE){
                                    if (yunfee.isChecked() && daishou.isChecked()){
                                        payWay = 0;
                                    };
                                    if (!yunfee.isChecked() && !daishou.isChecked()){
                                        payWay = 3;
                                    }

                                    if (!yunfee.isChecked() && daishou.isChecked()){
                                        payWay = 2;
                                    }
                                    if (yunfee.isChecked() && !daishou.isChecked()){
                                        payWay = 1;
                                    }
                                }else if (yunFeeLine.getVisibility() == View.GONE){
                                    if (daishou.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }else {
                                    if (yunfee.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }

                                break;
                            case DebitConfig.MODE_1_1_1:
                                if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE){
                                    if (yunfee.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }else if (yunFeeLine.getVisibility() == View.GONE){
                                    if (daishou.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }else {
                                    if (yunfee.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }

                                break;
                        }
                    }else {
                        switch (mContext.getDebitConfig().getNormalMode()){
                            case DebitConfig.MODE_0_0_0:
                                payWay = 0;
                                break;
                            case DebitConfig.MODE_0_0_1:
                                if (daishouLine.getVisibility() == View.VISIBLE){
                                    payWay = daishou.isChecked() ? 0 : 1;
                                }else {
                                    payWay = 0;
                                }

                                break;
                            case DebitConfig.MODE_0_1_0:
                                if (yunFeeLine.getVisibility() == View.VISIBLE){
                                    payWay = yunfee.isChecked() ? 0 : 2;
                                }else {
                                    payWay = 0;
                                }

                                break;
                            case DebitConfig.MODE_0_1_1:
                                if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE){
                                    if (yunfee.isChecked() && daishou.isChecked()){
                                        payWay = 0;
                                    };
                                    if (!yunfee.isChecked() && !daishou.isChecked()){
                                        payWay = 3;
                                    }

                                    if (!yunfee.isChecked() && daishou.isChecked()){
                                        payWay = 2;
                                    }
                                    if (yunfee.isChecked() && !daishou.isChecked()){
                                        payWay = 1;
                                    }
                                }else if (yunFeeLine.getVisibility() == View.GONE){
                                    if (daishou.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }else {
                                    if (yunfee.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }

                                break;
                            case DebitConfig.MODE_1_1_1:
                                if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE){
                                    if (yunfee.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }else if (yunFeeLine.getVisibility() == View.GONE){
                                    if (daishou.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }else {
                                    if (yunfee.isChecked()){
                                        payWay = 0;
                                    }else {
                                        payWay = 3;
                                    }
                                }

                                break;
                        }
                    }




                    HttpTools.cashPay(((Context) mContext), mOfflineInfo.getOrderNumber(), mOfflineInfo.getReceiver(),mOfflineInfo.getSignRemarks(), all, payWay, ((HttpRequestListener) mContext));
                    mAlertDialog.dismiss();
                }
            });

            mAlertDialog = new AlertDialog.Builder(((Context) mContext))
                    .setView(mContent)
                    .create();
            mAlertDialog.setCanceledOnTouchOutside(false);


        }

        ((TextView) mContent.findViewById(R.id.deliver_fee)).setText(Utils.getCurrencyStr(mOfflineInfo.getDeliverFee()));
        ((TextView) mContent.findViewById(R.id.daishou_fee)).setText(Utils.getCurrencyStr(mOfflineInfo.getDaishouFee()));

        if (mOfflineInfo.getDeliverFee() == 0){
            mContent.findViewById(R.id.yunfei_line).setVisibility(View.GONE);
        }else {
            mContent.findViewById(R.id.yunfei_line).setVisibility(View.VISIBLE);
        }

        if (mOfflineInfo.getDaishouFee() == 0){
            mContent.findViewById(R.id.daishou_line).setVisibility(View.GONE);

        }else {
            mContent.findViewById(R.id.daishou_line).setVisibility(View.VISIBLE);
        }
        TextView orderNum = (TextView) mContent.findViewById(R.id.order_number);
        orderNum.setText(mOfflineInfo.getOrderNumber());
        final TextView totalFee = (TextView) mContent.findViewById(R.id.total_fee);
        totalFee.setText("合计：" + Utils.getCurrencyStr(mOfflineInfo.getDaishouFee() + mOfflineInfo.getDeliverFee()));
        return mAlertDialog;
    }

    private void process(final CheckBox daishou, final CheckBox yunfee, final TextView totalFee, View yunFeeLine, View daishouLine, String mode) {
        yunFeeLine.setVisibility(mOfflineInfo.getDeliverFee() == 0 ? View.GONE : View.VISIBLE);
        daishouLine.setVisibility(mOfflineInfo.getDaishouFee() == 0 ? View.GONE : View.VISIBLE);
        switch (mode) {
            case DebitConfig.MODE_1_1_1:
                yunfee.setEnabled(true);
                daishou.setEnabled(true);
                yunfee.setButtonDrawable(R.drawable.pay_select);
                daishou.setButtonDrawable(R.drawable.pay_select);
                yunfee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        daishou.setChecked(isChecked);
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? (mOfflineInfo.getDaishouFee() + mOfflineInfo.getDeliverFee()) : 0));
                    }
                });
                daishou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        yunfee.setChecked(isChecked);
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? (mOfflineInfo.getDaishouFee() + mOfflineInfo.getDeliverFee()) : 0));
                    }
                });
                break;
            case DebitConfig.MODE_0_0_0:
                yunfee.setButtonDrawable(R.drawable.dsydxq_cancel);
                yunfee.setEnabled(false);
                daishou.setButtonDrawable(R.drawable.dsydxq_cancel);
                daishou.setEnabled(false);
                totalFee.setText("合计：" + Utils.getCurrencyStr(mOfflineInfo.getDaishouFee() + mOfflineInfo.getDeliverFee()));
                break;
            case DebitConfig.MODE_0_0_1:
                yunfee.setButtonDrawable(R.drawable.dsydxq_cancel);
                yunfee.setEnabled(false);
                daishou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? mOfflineInfo.getDaishouFee() + mOfflineInfo.getDeliverFee() : mOfflineInfo.getDeliverFee()));
                    }
                });
                daishou.setButtonDrawable(R.drawable.pay_select);
                daishou.setEnabled(true);
                break;
            case DebitConfig.MODE_0_1_0:
                yunfee.setEnabled(true);
                daishou.setEnabled(false);
                daishou.setButtonDrawable(R.drawable.dsydxq_cancel);
                yunfee.setButtonDrawable(R.drawable.pay_select);
                yunfee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? (mOfflineInfo.getDaishouFee() + mOfflineInfo.getDeliverFee()) : mOfflineInfo.getDaishouFee()));
                    }
                });
                break;
            case DebitConfig.MODE_0_1_1:
                yunfee.setEnabled(true);
                daishou.setEnabled(true);
                yunfee.setButtonDrawable(R.drawable.pay_select);
                daishou.setButtonDrawable(R.drawable.pay_select);
                yunfee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            totalFee.setText("合计：" + Utils.getCurrencyStr(mOfflineInfo.getDeliverFee() + (daishou.isChecked() ? mOfflineInfo.getDaishouFee() : 0)));
                        }else {
                            totalFee.setText("合计：" + Utils.getCurrencyStr(daishou.isChecked() ? mOfflineInfo.getDaishouFee() : 0));
                        }
                    }
                });

                daishou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            totalFee.setText("合计：" + Utils.getCurrencyStr(mOfflineInfo.getDaishouFee() + (yunfee.isChecked() ? mOfflineInfo.getDeliverFee() : 0)));
                        }else {
                            totalFee.setText("合计：" + Utils.getCurrencyStr(yunfee.isChecked() ? mOfflineInfo.getDeliverFee() : 0));
                        }
                    }
                });
                break;
        }
    }


}
