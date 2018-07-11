package com.liinji.ppgdeliver.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.gprinter.io.PortParameters;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.OrderPics;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.jiaboprint.JiaboPrintService;
import com.liinji.ppgdeliver.print.PrintEvent;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.print.PrintService;
import com.liinji.ppgdeliver.tools.StatusBox;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.Snackbar.make;
import static com.liinji.ppgdeliver.jiaboprint.PrintUtil.showSwitchModeTip;


/**
 * Created by Administrator on 2017/3/16.
 */
public class CompleteDetailActivity extends BaseLightStatusBarActivity implements View.OnClickListener {

    public static final int ENABLE_BLUETOOTH_TO_PRINT = 1;
    public static final int ENABLE_BLUETOOTH_FAIL_BACK = 2;


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
    @BindView(R.id.yunfei)
    TextView mYunfei;
    @BindView(R.id.daishou)
    TextView mDaishou;

    @BindView(R.id.fee_info_line)
    LinearLayout mFeeInfoLine;
    @BindView(R.id.cutdown_deliver_fee)
    TextView mCutdownDeliverFee;
    @BindView(R.id.cutdown_fee)
    RelativeLayout mCutdownFee;
    @BindView(R.id.goods_count)
    TextView mGoodsCount;
    @BindView(R.id.total_fee)
    TextView mTotalFee;
    @BindView(R.id.count_line)
    LinearLayout mCountLine;
    @BindView(R.id.sign_time)
    TextView mSignTime;
    @BindView(R.id.jijie_mark)
    ImageView mJijieMark;
    @BindView(R.id.pay_mode_desc)
    TextView mPayModeDesc;
    @BindView(R.id.pay_mode_desc_label)
    TextView mPayModeDescLabel;
    @BindView(R.id.print_order)
    TextView mPrintOrder;
    @BindView(R.id.goods_info_line)
    RelativeLayout mGoodsInfoLine;
    @BindView(R.id.yunfee_reduce_mark)
    CheckBox mYunfeeReduceMark;
    @BindView(R.id.yunfei_after_reduced)
    TextView mYunfeiAfterReduced;
    @BindView(R.id.daishou_reduce_mark)
    CheckBox mDaishouReduceMark;
    @BindView(R.id.daishou_after_reduce)
    TextView mDaishouAfterReduce;
    @BindView(R.id.order_paper_number)
    TextView mOrderPaperNumber;

    @BindView(R.id.goods_name_number)
    TextView mGoodsNameNumber;

    @BindView(R.id.open_remark)
    TextView mOpenRemark;
    @BindView(R.id.sign_remark)
    TextView mSignRemark;
    @BindView(R.id.consign_time)
    TextView mConsignTime;
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
    @BindView(R.id.deliver_fee)
    TextView mDeliverFee;
    @BindView(R.id.signer)
    TextView mSigner;
    @BindView(R.id.transfer_fee)
    TextView mTransferFee;
    @BindView(R.id.transfer_fee_line)
    LinearLayout mTransferFeeLine;
    @BindView(R.id.transfer_company)
    TextView mTransferCompany;
    @BindView(R.id.transfer_no)
    TextView mTransferNo;
    @BindView(R.id.sign_remarks_line)
    LinearLayout mSignRemarksLine;
    @BindView(R.id.signer_line)
    LinearLayout mSignerLine;

    private int mIsSendAndLoad;

    @Override
    protected int returnContentView() {
        return R.layout.activity_complete_detail;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        mStatusBox = new StatusBox(this, getRootView());
        toolbar_title.setText("运单详情");
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        mIsSendAndLoad = getIntent().getIntExtra(WaitToSentActivity.EXTRA_IS_SEND_AND_LOAD, 0);
        mPrintOrder.setEnabled(false);
    }

    OrderDetail.WaybillInfoBean mOrderComplete;

    private String mOrderStr;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mOrderStr = getIntent().getStringExtra("order");
        HttpTools.orderDetail(this, mOrderStr, this);
        HttpTools.retrieveOrderPics(this, this, mOrderStr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    @OnClick({R.id.dial_sender, R.id.dial_receiver})
    public void onViewClick(View view) {
        String phoneNumber = "";
        switch (view.getId()) {
            case R.id.dial_sender:
                phoneNumber = mSenderNumber.getText().toString().trim();
                break;
            case R.id.dial_receiver:
                phoneNumber = mReceiverNumber.getText().toString().trim();
                break;

        }

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.ORDER_DETAIL:
                mOrderComplete = ((OrderDetail) bean.getReturnData()).getWaybillInfo();
                if (!TextUtils.isEmpty(mOrderComplete.getCompanyUrl())) {
                    MyApplication.sMyApplication.loadCompanyLogo(mOrderComplete.getCompanyUrl());
                }

                switch (mOrderComplete.getPayMode()) {
                    case "月结":
                        mYunFeeLabel.setText("运费（月结 " + PrintInfo.processFee(Float.parseFloat(mOrderComplete.getMonthAmount())) + "）");
                        break;
                    case "现付":
                        mYunFeeLabel.setText("运费（现付 " + PrintInfo.processFee(Float.parseFloat(mOrderComplete.getCashAmount())) + "）");
                        break;
                    case "回付":
                        mYunFeeLabel.setText("运费（回付 " + PrintInfo.processFee(Float.parseFloat(mOrderComplete.getReceiptPayAmount())) + "）");
                        break;
                }

                mDispatchBranchName.setText("寄件点：" + mOrderComplete.getSendBranchName());

                mDispatchBranchTel.setText(" " + mOrderComplete.getSendBranchContactTel());

                mSigner.setText("签收人：" + mOrderComplete.getSigner());


                mDeliverFee.append(PrintInfo.processFee(mOrderComplete.getDeliveryFee()));
                mDeliverFee.setVisibility(View.VISIBLE);


                mUntilEndMark.setVisibility(mOrderComplete.getIsToEnd() == 1 ? View.VISIBLE : View.INVISIBLE);

                mOrderNumber.append(mOrderComplete.getWaybillNo());
                mOpenRemark.append(mOrderComplete.getRemark());
                mSignRemark.append(mOrderComplete.getSignRemark());
                mSender.append(mOrderComplete.getShipper());
                mSenderNumber.append(mOrderComplete.getDeliveryTel());
                mSendAddress.append(mOrderComplete.getDeliveryAddress());
                mReceiver.append(mOrderComplete.getReceiver());
                mReceiverNumber.append(mOrderComplete.getReceiveTel());
                mReceiverAddress.append(mOrderComplete.getReceiveAddress());
                mGoodsName.append(mOrderComplete.getCargoName());
                mConsignTime.append(mOrderComplete.getConsignDate());
                mPayMode.append(mOrderComplete.getPayMode());
                String payType = mOrderComplete.getPayTypeDesc();
                if (TextUtils.isEmpty(payType)) {
                    mPayModeDescLabel.setVisibility(View.INVISIBLE);
                    mPayModeDesc.setVisibility(View.INVISIBLE);
                } else {
                    mPayModeDescLabel.setVisibility(View.VISIBLE);
                    mPayModeDesc.setVisibility(View.VISIBLE);
                    mPayModeDesc.setText(payType);
                }

                mJianshu.append(mOrderComplete.getTotalNumber() + "件");
                mSignTime.append(mOrderComplete.getSignDate() + "");
                if (mOrderComplete.getOriginalFerightAmount() != mOrderComplete.getPickUpFee()) {
                    mYunfei.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                    mYunfei.setTextColor(Color.parseColor("#999999"));
                    mYunfeiAfterReduced.setVisibility(View.VISIBLE);
                    mYunfeeReduceMark.setEnabled(false);
                }
                mYunfei.append(Utils.getCurrencyStr(mOrderComplete.getOriginalFerightAmount()));
                mYunfeiAfterReduced.setText(Utils.getCurrencyStr(mOrderComplete.getPickUpFee()));


                if (Float.parseFloat(mOrderComplete.getCollectionTradeCharges()) != mOrderComplete.getOriginalPaymentAmount()) {
                    mDaishou.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                    mDaishou.setTextColor(Color.parseColor("#999999"));
                    mDaishouAfterReduce.setVisibility(View.VISIBLE);
                    mDaishouReduceMark.setEnabled(false);
                }
                mDaishou.append(Utils.getCurrencyStr(mOrderComplete.getOriginalPaymentAmount()));
                mDaishouAfterReduce.setText(Utils.getCurrencyStr(Float.parseFloat(mOrderComplete.getCollectionTradeCharges())));
                mGoodsCount.setText("共" + mOrderComplete.getTotalNumber() + "件 合计：");


                if (mOrderComplete.getIsSendAndLoad() == 1) {
                    mJijieMark.setImageResource(R.drawable.jijie_mark);
                    mJijieMark.setVisibility(View.VISIBLE);
                }

                if (mOrderComplete.getIsReturn() == 1) {
                    mJijieMark.setImageResource(R.drawable.return_mark);
                    mJijieMark.setVisibility(View.VISIBLE);
                    mSignTime.setText("签收时间：");
                }


                mTotalFee.append(Utils.getCurrencyStr(getTotalFee()));

                mOrderPaperNumber.setText("纸质单号：" + mOrderComplete.getPaperNumber());
                mOrderPaperNumber.setVisibility(TextUtils.isEmpty(mOrderComplete.getPaperNumber()) ? View.GONE : View.VISIBLE);
                mDialSender.setVisibility(TextUtils.isEmpty(mOrderComplete.getDeliveryTel()) ? View.INVISIBLE : View.VISIBLE);
                mDialReceiver.setVisibility(TextUtils.isEmpty(mOrderComplete.getReceiveTel()) ? View.INVISIBLE : View.VISIBLE);
                mGoodsNameNumber.append(mOrderComplete.getWaybillCargoNo());
                mGoodsNameNumber.setVisibility(TextUtils.isEmpty(mOrderComplete.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);
                mPrintOrder.setEnabled(true);
                mSendWay.setText("送货方式：" + mOrderComplete.getPickUpWay());
                String imgUrl = mOrderComplete.getCompanyUrl();
                if (!TextUtils.isEmpty(imgUrl)) {
                    Glide.with(this).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            MyApplication.sCompanyLogo = resource;
                        }
                    });
                }


                if (!TextUtils.isEmpty(mOrderComplete.getTransferCompanyName())) {
                    mTransferFeeLine.setVisibility(View.VISIBLE);
                    mTransferFee.setText(Utils.getCurrencyStr(mOrderComplete.getTransferFee()));
                    mTransferCompany.setText("中转公司：" + mOrderComplete.getTransferCompanyName());
                    mTransferNo.setText("中转运单号：" + mOrderComplete.getTransferNo());

                }

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

    private List<String> mPreList = new ArrayList<>();

    private float getTotalFee() {
        return mOrderComplete.getPickUpFee() + Float.parseFloat(mOrderComplete.getCollectionTradeCharges());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ENABLE_BLUETOOTH_TO_PRINT) {
            if (SharePrefUtils.getSavedDeviceAddress().isEmpty() || mBluetoothAdapter.getRemoteDevice(SharePrefUtils.getSavedDeviceAddress()).getBondState() != BluetoothDevice.BOND_BONDED) {
                SharePrefUtils.storeDeviceAddress("");
                SharePrefUtils.storeDeviceName("");
                startActivity(new Intent(this, BluetoothActivity.class));
            } else {
                getStatusBox().Show("正在连接打印机...");
                toPrint();
            }
        }


    }


    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onRestart() {
        super.onRestart();
        mPrintOrder.setEnabled(true);
        mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mStatusBox.Close();
    }

    @OnClick(R.id.print_order)
    public void onPrintViewClicked() {

        SharePrefUtils.setCancelConnect(false);
        mPrintOrder.setEnabled(false);
        mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.c_light_gray));

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            SmartToast.showLongInCenter("该设备不支持蓝牙!");
        } else if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_TO_PRINT);
        } else {

            if (SharePrefUtils.getSavedDeviceAddress().isEmpty() || mBluetoothAdapter.getRemoteDevice(SharePrefUtils.getSavedDeviceAddress()).getBondState() != BluetoothDevice.BOND_BONDED) {
                SharePrefUtils.storeDeviceAddress("");
                SharePrefUtils.storeDeviceName("");
                startActivity(new Intent(this, BluetoothActivity.class));
            } else {
                getStatusBox().Show("正在连接打印机...");
                toPrint();

            }
        }

    }

    private void toPrint() {

        PrintInfo printInfo = new PrintInfo(mOrderComplete, "")
                .setNeedPrintSequence(0);
        if (SharePrefUtils.getSavedDeviceName().startsWith("HDT") || SharePrefUtils.getSavedDeviceName().startsWith("XT")) {
            PrintService.startService(printInfo);
            return;
        }

        if (SharePrefUtils.getSavedDeviceName().startsWith("Printer_")) {
            JiaboPrintService.startService(printInfo);
        }
    }


    private StatusBox mStatusBox;

    public StatusBox getStatusBox() {
        if (mStatusBox == null) {
            mStatusBox = new StatusBox(this, toolbar);
        }

        return mStatusBox;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivePrintEvent(PrintEvent printEvent) {
        switch (printEvent.getResult()) {
            case PrintEvent.CONNECT_SUCCESSFUL:
                getStatusBox().Show("连接成功，即将开始打印。");
                getStatusBox().Close();
                break;
            case PrintEvent.FAIL_BLUETOOTH_CLOSE:
                getStatusBox().Close();
                final Snackbar snackbar = make(toolbar, "蓝牙已关闭，请开启蓝牙后重试！", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("开启蓝牙", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                        BluetoothAdapter.getDefaultAdapter().enable();
                    }
                });
                break;
            case PrintEvent.FAIL_DEVICE_NOT_BONDED:
                getStatusBox().Close();
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                final Snackbar snackbar2 = make(toolbar, "设备尚未配对！", Snackbar.LENGTH_INDEFINITE);
                snackbar2.setAction("去重新配对", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar2.dismiss();
                        startActivityForResult(new Intent(CompleteDetailActivity.this, BluetoothActivity.class), ENABLE_BLUETOOTH_FAIL_BACK);
                    }
                });
                break;
            case PrintEvent.FAIL_REASON_UNKNOWN:
                getStatusBox().Close();
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getPrintAlert().show();
                break;
            case PrintEvent.PRINT_COMPLETE:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                make(toolbar, "打印完成！", Snackbar.LENGTH_SHORT).show();
                break;
            case PrintEvent.FAIL_OFFLINE:
                try {
                    MyApplication.sMyApplication.mGpService.openPort(2, PortParameters.BLUETOOTH, SharePrefUtils.getSavedDeviceAddress(), 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case PrintEvent.JIABO_FAIL:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                getPrintAlert().show();
                MyApplication.isConnect = false;
                break;
            case PrintEvent.PAPER_LACK:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                getPrintAlert().show();
                SmartToast.showLongInCenter("打印机缺纸，请续纸后重试！");
                break;
            case PrintEvent.PRINTER_ERROR:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                getPrintAlert().show();
                SmartToast.showLongInCenter("打印机内部错误，请重试！");
                break;
            case PrintEvent.MODE_ERROR:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                showSwitchModeTip(this);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getStatusBox().Close();
    }

    private AlertDialog mPrintAlert;

    public AlertDialog getPrintAlert() {
        if (mPrintAlert == null) {
            View content = LayoutInflater.from(this).inflate(R.layout.print_alert, null);
            content.findViewById(R.id.cancel_conn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPrintAlert.dismiss();
                }
            });
            content.findViewById(R.id.reconnect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPrintAlert().dismiss();
                    SharePrefUtils.setCancelConnect(false);
                    onPrintViewClicked();
                }
            });
            mPrintAlert = new AlertDialog.Builder(this)
                    .setView(content)
                    .create();
        }

        return mPrintAlert;
    }


    @OnClick({R.id.yunfee_reduce_mark, R.id.daishou_reduce_mark, R.id.dial_dispatch_branch})

    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.yunfee_reduce_mark:
                break;
            case R.id.daishou_reduce_mark:
                break;
            case R.id.dial_dispatch_branch:
                Utils.dialSomeone(this, mDispatchBranchTel.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
