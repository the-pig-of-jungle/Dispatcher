package com.liinji.ppgdeliver.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.bumptech.glide.Glide;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.DaishouDetail;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.OrderPics;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DaishouDebitDetailActivity extends BaseLightStatusBarActivity implements View.OnClickListener {
    @BindView(R.id.order_number)
    TextView mOrderNumber;
    @BindView(R.id.sender)
    TextView mSender;
    @BindView(R.id.sender_number)
    TextView mSenderNumber;
    @BindView(R.id.dial_sender)
    TextView mDialSender;
    @BindView(R.id.send_address)
    TextView mSendAddress;
    @BindView(R.id.sender_line)
    LinearLayout mSenderLine;
    @BindView(R.id.receiver)
    TextView mReceiver;
    @BindView(R.id.receiver_number)
    TextView mReceiverNumber;
    @BindView(R.id.dial_receiver)
    TextView mDialReceiver;
    @BindView(R.id.receiver_address)
    TextView mReceiverAddress;
    @BindView(R.id.receiver_line)
    LinearLayout mReceiverLine;
    @BindView(R.id.goods_name)
    TextView mGoodsName;
    @BindView(R.id.pay_mode)
    TextView mPayMode;
    @BindView(R.id.jianshu)
    TextView mJianshu;
    @BindView(R.id.sign_time)
    TextView mSignTime;
    @BindView(R.id.payback_mark)
    ImageView mPaybackMark;
    @BindView(R.id.goods_info_line)
    RelativeLayout mGoodsInfoLine;
    @BindView(R.id.yunfei)
    TextView mYunfei;
    @BindView(R.id.daishou)
    TextView mDaishou;
    @BindView(R.id.fee_info_line)
    LinearLayout mFeeInfoLine;
    @BindView(R.id.goods_count)
    TextView mGoodsCount;
    @BindView(R.id.total_fee)
    TextView mTotalFee;
    @BindView(R.id.count_line)
    LinearLayout mCountLine;
    @BindView(R.id.btn_pay)
    Button mBtnPay;
    @BindView(R.id.order_paper_number)
    TextView mOrderPaperNumber;
    @BindView(R.id.open_remark)
    TextView mOpenRemark;
    @BindView(R.id.send_time)
    TextView mSendTime;
    @BindView(R.id.sign_remark)
    TextView mSignRemark;
    @BindView(R.id.goods_name_number)
    TextView mGoodsNameNumber;
    @BindView(R.id.send_way)
    TextView mSendWay;
    @BindView(R.id.pic_imgv01)
    ImageView mPicImgv01;
    @BindView(R.id.pic_imgv02)
    ImageView mPicImgv02;
    @BindView(R.id.pic_imgv03)
    ImageView mPicImgv03;
    @BindView(R.id.pic_upload_line)
    LinearLayout mPicUploadLine;
    @BindView(R.id.yun_fee_label)
    TextView mYunFeeLabel;
    @BindView(R.id.dispatch_branch_name)
    TextView mDispatchBranchName;
    @BindView(R.id.dispatch_branch_tel)
    TextView mDispatchBranchTel;
    @BindView(R.id.dial_dispatch_branch)
    TextView mDialDispatchBranch;
    @BindView(R.id.until_end_mark)
    ImageView mUntilEndMark;
    @BindView(R.id.signer)
    TextView mSigner;
    @BindView(R.id.transfer_fee)
    TextView mTransferFee;
    @BindView(R.id.transfer_company)
    TextView mTransferCompany;
    @BindView(R.id.transfer_no)
    TextView mTransferNo;
    @BindView(R.id.transfer_fee_line)
    LinearLayout mTransferFeeLine;

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("运单详情");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_pay_back_detail;
    }

    private String mOrderIdStr;
    private String mOrderStr;
    private List<String> mPreList = new ArrayList<>(3);

    @Override
    protected void initData(Bundle savedInstanceState) {
        mOrderIdStr = getIntent().getStringExtra("order_id");
        mOrderStr = getIntent().getStringExtra("order");
        mPos = getIntent().getIntExtra("item_pos", 0);
        HttpTools.getDaishouDebitDetail(this, this, mOrderIdStr);
        HttpTools.retrieveOrderPics(this, this, mOrderStr);
    }

    private int mPos;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPicImgv01.setOnClickListener(this);
        mPicImgv02.setOnClickListener(this);
        mPicImgv03.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (((ImageView) v).getDrawable() != null) {
            //使用方式
            int pos = 0;
            switch (v.getId()) {
                case R.id.pic_imgv01:
                    pos = 0;
                    break;
                case R.id.pic_imgv02:
                    pos = 1;
                    break;
                case R.id.pic_imgv03:
                    pos = 2;
                    break;
            }
            PictureConfig config = new PictureConfig.Builder()
                    .setListData((ArrayList<String>) mPreList)    //图片数据List<String> list
                    .setPosition(pos)    //图片下标（从第position张图片开始浏览）
                    .setDownloadPath("pictureviewer")    //图片下载文件夹地址
                    .setIsShowNumber(true)//是否显示数字下标
                    .needDownload(true)    //是否支持图片下载
                    .setPlacrHolder(R.drawable.yx_icon_default)    //占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                    .build();
            ImagePagerActivity.startActivity(this, config);
        }

    }

    private DaishouDetail mDaishouDetail;

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.DAISHOU_DEBIT_DETAIL:

                mDaishouDetail = (DaishouDetail) bean.getReturnData();
                switch (mDaishouDetail.getSettleWay()) {
                    case "月结":
                        mYunFeeLabel.setText("运费（月结 " + PrintInfo.processFee(Float.parseFloat(mDaishouDetail.getMonthPayAmount())) + "）");
                        break;
                    case "现付":
                        mYunFeeLabel.setText("运费（现付 " + PrintInfo.processFee(Float.parseFloat(mDaishouDetail.getCashPayAmount())) + "）");
                        break;
                    case "回付":
                        mYunFeeLabel.setText("运费（回付 " + PrintInfo.processFee(Float.parseFloat(mDaishouDetail.getReceiptPayAmount())) + "）");
                        break;
                }

                mDispatchBranchName.setText("寄件点：" + mDaishouDetail.getSendBranchName());

                mDispatchBranchTel.setText(" " + mDaishouDetail.getSendBranchContactTel());

                if (mDaishouDetail.getIsToEnd() == 1) {
                    mUntilEndMark.setVisibility(View.VISIBLE);
                }

                mSigner.setText("签收人：" + mDaishouDetail.getSigner());

                mOrderNumber.append(mDaishouDetail.getWaybillNo());
                mSender.append(mDaishouDetail.getShipper());
                Logger.d(mDaishouDetail.getDeliveryTel());
                mSenderNumber.append(mDaishouDetail.getDeliveryTel());
                mSendAddress.append(mDaishouDetail.getDeliveryAddress());
                mReceiver.append(mDaishouDetail.getReceiver());
                mReceiverNumber.append(mDaishouDetail.getReceiveTel());
                mReceiverAddress.append(mDaishouDetail.getReceiveAddress());
                mGoodsName.append(mDaishouDetail.getCargoName());
                mPayMode.append(mDaishouDetail.getSettleWay());
                mJianshu.append(mDaishouDetail.getTotalNumber() + "件");
                mSignTime.append(mDaishouDetail.getSignDate() + "");
                mYunfei.append(Utils.getCurrencyStr(mDaishouDetail.getFreightFee()));
                mDaishou.append(Utils.getCurrencyStr(Float.parseFloat(mDaishouDetail.getCollectAmount())));
                mGoodsCount.setText("");
                mSendTime.append(mDaishouDetail.getConsignDate());
                mOrderPaperNumber.setText("纸质单号：" + mDaishouDetail.getPaperNumber());
                Logger.d("货号：" + mDaishouDetail.getWaybillCargoNo());
                mGoodsNameNumber.setText("货号：" + mDaishouDetail.getWaybillCargoNo());
                mGoodsNameNumber.setVisibility(TextUtils.isEmpty(mDaishouDetail.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);
                mOrderPaperNumber.setVisibility(TextUtils.isEmpty(mDaishouDetail.getPaperNumber()) ? View.GONE : View.VISIBLE);
                mSendWay.setText("送货方式：" + mDaishouDetail.getPickUpWay());

                String label = "合计：";
                if (mDaishouDetail.getFreightStatus() == 1) {
                    if (Float.parseFloat(mDaishouDetail.getFreightFee()) != 0) {
                        mYunfei.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                    }

                    mPayType = 2;
                } else {
                    mTotalFeeAmount += Float.parseFloat(mDaishouDetail.getFreightFee());
                }

                if (mDaishouDetail.getCollectPayStatus() == 1) {
                    mDaishou.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                    mPayType = 1;
                } else {
                    mTotalFeeAmount += Float.parseFloat(mDaishouDetail.getCollectAmount());
                }

                if (mDaishouDetail.getFreightStatus() == 0 && mDaishouDetail.getCollectPayStatus() == 0) {
                    mPayType = 0;
                    label = "合计：";
                } else {
                    label = "待还款金额：";
                }

                mTotalFee.append(label + Utils.getCurrencyStr(mTotalFeeAmount));

                mOpenRemark.append(mDaishouDetail.getRemark());

                mSignRemark.append(mDaishouDetail.getSignRemark());

                mDialSender.setVisibility(TextUtils.isEmpty(mDaishouDetail.getDeliveryTel()) ? View.INVISIBLE : View.VISIBLE);

                mDialReceiver.setVisibility(TextUtils.isEmpty(mDaishouDetail.getReceiveTel()) ? View.INVISIBLE : View.VISIBLE);

                if (!TextUtils.isEmpty(mDaishouDetail.getTransferCompanyName())){
                    mTransferFeeLine.setVisibility(View.VISIBLE);
                    mTransferFee.setText(Utils.getCurrencyStr(mDaishouDetail.getTransferFee()));
                    mTransferCompany.setText("中转公司：" + mDaishouDetail.getTransferCompanyName());

                    mTransferNo.setText("中转运单号：" + mDaishouDetail.getTransferNo());
                }


                break;
            case HttpRequestType.DAISHOU_PAY:
                SmartToast.showInCenter("收款成功");
                Intent intent = new Intent();
                intent.putExtra("pay_succ", true);
                intent.putExtra("item_pos", mPos);
                setResult(RESULT_OK, intent);
                finish();
                break;

            case HttpRequestType.GET_ORDER_PIC:
                List<OrderPics.ReturnDataBean> hasUpload = ((OrderPics) bean.getReturnData()).getReturnData();


                if (!hasUpload.isEmpty()) {

                    for (int i = 0; i < hasUpload.size(); i++) {
                        mPreList.add(hasUpload.get(i).getImageUrl());
                    }

                    mPicUploadLine.setVisibility(View.VISIBLE);

                    for (int index = 0; index < hasUpload.size(); index++) {
                        switch (index) {
                            case 0:
                                Glide.with(this).load(hasUpload.get(index).getImageUrl()).placeholder(R.drawable.yx_icon_default).override(400, 600).into(mPicImgv01);
                                break;
                            case 1:
                                Glide.with(this).load(hasUpload.get(index).getImageUrl()).placeholder(R.drawable.yx_icon_default).override(400, 600).into(mPicImgv02);
                                break;
                            case 2:
                                Glide.with(this).load(hasUpload.get(index).getImageUrl()).placeholder(R.drawable.yx_icon_default).override(400, 600).into(mPicImgv03);
                                break;
                        }
                    }
                }

                break;
        }


    }

    @OnClick({R.id.dial_sender, R.id.dial_receiver})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.dial_sender:
                Utils.dialSomeone(this, mSenderNumber.getText().toString().trim());
                break;
            case R.id.dial_receiver:
                Utils.dialSomeone(this, mReceiverNumber.getText().toString().trim());
                break;
        }
    }

    private float getTotalFee(OrderDetail.WaybillInfoBean waybillInfoBean) {
        return waybillInfoBean.getPickUpFee() + Float.parseFloat(waybillInfoBean.getCollectionTradeCharges());
    }

    private int mPayType;
    private float mTotalFeeAmount;

    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
        String daishou = "0";
        String yunFee = "0";
        switch (mPayType) {
            case 0:
                daishou = mDaishouDetail.getCollectAmount();
                yunFee = mDaishouDetail.getFreightFee();
                break;
            case 1:
                yunFee = mDaishouDetail.getFreightFee();
                break;
            case 2:
                daishou = mDaishouDetail.getCollectAmount();
                break;
        }
        SweetAlertDialog dialog = new SweetAlertDialog(this);
        final String finalDaishou = daishou;
        final String finalYunFee = yunFee;
        dialog.setConfirmText("确认")
                .setCancelText("取消")
                .setTitleText("提示")
                .setContentText("收取欠款" + Utils.getCurrencyStr(Float.parseFloat(daishou) + Float.parseFloat(yunFee)))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();

                        HttpTools.daishouPay(DaishouDebitDetailActivity.this, DaishouDebitDetailActivity.this, mDaishouDetail.getWaybillId(), finalDaishou, finalYunFee);
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onShow(DialogInterface dialog) {
                ((Dialog) dialog).findViewById(R.id.confirm_button).setBackground(ContextCompat.getDrawable(DaishouDebitDetailActivity.this, R.drawable.round_rect__green));
            }
        });
        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
