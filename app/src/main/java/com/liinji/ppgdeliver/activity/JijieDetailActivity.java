package com.liinji.ppgdeliver.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.bumptech.glide.Glide;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.OrderPics;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JijieDetailActivity extends BaseLightStatusBarActivity implements View.OnClickListener {
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
    @BindView(R.id.order_paper_number)
    TextView mOrderPaperNumber;

    @BindView(R.id.goods_name_number)
    TextView mGoodsNameNumber;


    @BindView(R.id.open_remark)
    TextView mOpenRemark;
    @BindView(R.id.send_time)
    TextView mSendTime;
    @BindView(R.id.sign_remark)
    TextView mSignRemark;
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

    @Override
    protected int returnContentView() {
        return R.layout.activity_jijie_detail;
    }


    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("运单详情");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
    }

    private String mOrderStr;
    private int mPos;
    private List<String> mPreList = new ArrayList<>(3);

    @Override
    protected void initData(Bundle savedInstanceState) {

        mOrderStr = getIntent().getStringExtra("order");
        mPos = getIntent().getIntExtra("item_pos", 0);
        HttpTools.orderDetail(this, mOrderStr, this);
        HttpTools.retrieveOrderPics(this, this, mOrderStr);

    }


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

    private OrderDetail.WaybillInfoBean mJijieDtail;

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.ORDER_DETAIL:

                mJijieDtail = ((OrderDetail) bean.getReturnData()).getWaybillInfo();

                switch (mJijieDtail.getPayMode()) {
                    case "月结":
                        mYunFeeLabel.setText("运费（月结 " + PrintInfo.processFee(Float.parseFloat(mJijieDtail.getMonthAmount())) + "）");
                        break;
                    case "现付":
                        mYunFeeLabel.setText("运费（现付 " + PrintInfo.processFee(Float.parseFloat(mJijieDtail.getCashAmount())) + "）");
                        break;
                    case "回付":
                        mYunFeeLabel.setText("运费（回付 " + PrintInfo.processFee(Float.parseFloat(mJijieDtail.getReceiptPayAmount())) + "）");
                        break;
                }

                mDispatchBranchName.setText("寄件点：" + mJijieDtail.getSendBranchName());

                mDispatchBranchTel.setText(" " + mJijieDtail.getSendBranchContactTel());

                mSigner.setText("签收人：" + mJijieDtail.getSigner());


                mOrderNumber.append(mJijieDtail.getWaybillNo());
                mSender.append(mJijieDtail.getShipper());
                mSenderNumber.append(mJijieDtail.getDeliveryTel());
                mSendAddress.append(mJijieDtail.getDeliveryAddress());
                mReceiver.append(mJijieDtail.getReceiver());
                mReceiverNumber.append(mJijieDtail.getReceiveTel());
                mReceiverAddress.append(mJijieDtail.getReceiveAddress());
                mGoodsName.append(mJijieDtail.getCargoName());
                mPayMode.append(mJijieDtail.getPayMode());
                mJianshu.append(mJijieDtail.getTotalNumber() + "件");

                mSignTime.append(mJijieDtail.getSignDate() + "");
                mYunfei.append(Utils.getCurrencyStr(mJijieDtail.getPickUpFee()));
                mDaishou.append(Utils.getCurrencyStr(Float.parseFloat(mJijieDtail.getCollectionTradeCharges())));
                mGoodsCount.setText("共" + mJijieDtail.getTotalNumber() + "件");
                mTotalFee.append("合计：" + Utils.getCurrencyStr(mJijieDtail.getPickUpFee() + Float.parseFloat(mJijieDtail.getCollectionTradeCharges())));
                mOrderPaperNumber.setText("纸质单号：" + mJijieDtail.getPaperNumber());
                mGoodsNameNumber.setText("货号：" + mJijieDtail.getWaybillCargoNo());
                mGoodsNameNumber.setVisibility(TextUtils.isEmpty(mJijieDtail.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);
                mOrderPaperNumber.setVisibility(TextUtils.isEmpty(mJijieDtail.getPaperNumber()) ? View.GONE : View.VISIBLE);
                mOpenRemark.append(mJijieDtail.getRemark());
                mSignRemark.append(mJijieDtail.getSignRemark());
                mSendTime.append(mJijieDtail.getConsignDate());
                mSendWay.setText("送货方式：" + mJijieDtail.getPickUpWay());
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

    @OnClick({R.id.dial_sender, R.id.dial_receiver,R.id.dial_dispatch_branch})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.dial_sender:
                Utils.dialSomeone(this, mSenderNumber.getText().toString().trim());
                break;
            case R.id.dial_receiver:
                Utils.dialSomeone(this, mReceiverNumber.getText().toString().trim());
                break;
            case R.id.dial_dispatch_branch:
                Utils.dialSomeone(this,mDispatchBranchTel.getText().toString().trim());
                break;
        }
    }

    private float getTotalFee(OrderDetail.WaybillInfoBean waybillInfoBean) {
        return waybillInfoBean.getPickUpFee() + Float.parseFloat(waybillInfoBean.getCollectionTradeCharges());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
