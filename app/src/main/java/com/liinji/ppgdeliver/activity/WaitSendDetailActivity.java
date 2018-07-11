package com.liinji.ppgdeliver.activity;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.gprinter.io.PortParameters;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.PicAdapter;
import com.liinji.ppgdeliver.adapter.SignPicAdapter;
import com.liinji.ppgdeliver.bean.Authority;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.DebitConfig;
import com.liinji.ppgdeliver.bean.OfflineInfo;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.OrderPics;
import com.liinji.ppgdeliver.bean.PayEvent;
import com.liinji.ppgdeliver.bean.SignPic;
import com.liinji.ppgdeliver.bean.UploadImgResult;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.jiaboprint.JiaboPrintService;
import com.liinji.ppgdeliver.listener.PayItemClickListener;
import com.liinji.ppgdeliver.print.PrintEvent;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.print.PrintService;
import com.liinji.ppgdeliver.tools.BitmapTools;
import com.liinji.ppgdeliver.tools.StatusBox;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;
import com.yuyh.library.imgsel.ImgSelActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.liinji.ppgdeliver.jiaboprint.PrintUtil.showSwitchModeTip;
import static com.liinji.ppgdeliver.print.PrintInfo.processFee;
import static com.liinji.ppgdeliver.print.PrintInfo.sDecimalFormat;


/**
 * Created by Administrator on 2017/3/16.
 */
public class WaitSendDetailActivity extends BaseDarkStatusBarActivity implements OfflinePay {
    private static final String PAY_FINISH = "pay_finish";
    private static final int REQ_SET_BLUETOOTH = 0x123;
    public static final String EXTRA_YUNFEE_REDUCE_AMOUNT = "yunfee_reduce_amount";
    public static final String EXTRA_DAISHOU_REDUCE_AMOUNT = "daishou_reduce_amount";
    public static final String EXTRA_SIGN_REMARKS = "sign_remarks";


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
    @BindView(R.id.receiver)
    TextView mReceiver;
    @BindView(R.id.receiver_number)
    TextView mReceiverNumber;
    @BindView(R.id.dial_receiver)
    TextView mDialReceiver;
    @BindView(R.id.receiver_address)
    TextView mReceiverAddress;
    @BindView(R.id.goods_name)
    TextView mGoodsName;
    @BindView(R.id.jianshu)
    TextView mJianshu;
    @BindView(R.id.yunfei)
    TextView mYunfei;
    @BindView(R.id.daishou)
    TextView mDaishou;
    @BindView(R.id.goods_count)
    TextView mGoodsCount;
    @BindView(R.id.total_fee)
    TextView mTotalFee;
    @BindView(R.id.btn_pay)
    Button mBtnPay;
    @BindView(R.id.sender_line)
    LinearLayout mSenderLine;
    @BindView(R.id.receiver_line)
    LinearLayout mReceiverLine;
    @BindView(R.id.send_time)
    TextView mSendTime;
    @BindView(R.id.pay_mode)
    TextView mPayMode;
    @BindView(R.id.fee_info_line)
    LinearLayout mFeeInfoLine;
    @BindView(R.id.count_line)
    LinearLayout mCountLine;
    @BindView(R.id.jijie_mark)
    ImageView mJijieMark;
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
    @BindView(R.id.sign_remarks)
    EditText mSignRemarks;
    @BindView(R.id.remark_num)
    TextView mRemarkNum;
    @BindView(R.id.order_paper_number)
    TextView mOrderPaperNumber;
    @BindView(R.id.goods_name_number)
    TextView mGoodsNameNumber;
    @BindView(R.id.open_remark)
    TextView mOpenRemark;
    @BindView(R.id.send_way)
    TextView mSendWay;
    @BindView(R.id.pic_list)
    RecyclerView mPicList;
    @BindView(R.id.scroll_view)
    ScrollView mScrollView;
    @BindView(R.id.transfer_charges_remark)
    CheckBox mTransferChargesRemark;
    @BindView(R.id.transfer_charges)
    TextView mTransferCharges;
    @BindView(R.id.transfer_company)
    TextView mTransferCompany;
    @BindView(R.id.transfer_no)
    TextView mTransferNo;
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
    EditText mSigner;
    private String mOrderStr;


    @Override
    protected int returnContentView() {
        return R.layout.activity_wait_to_send_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();

        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar_title.setText("运单详情");
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);

    }

    OrderDetail.WaybillInfoBean mOrder;

    Intent mIntent = new Intent();


    private boolean mYunFeeHasReduce = true;

    private boolean mDaishouHasReduce = true;


    private String mNewTransferCharge;
    private String mNewTransferCompany;
    private String mNewTransferNo;

    private List<String> mLocalPath;


    @Override
    public void onSuccess(int type, final BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.REDUCE_YUNFEE:
                mIntent.putExtra(EXTRA_YUNFEE_REDUCE_AMOUNT, mReduceYunfeeAmount);
                mYunfei.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                mYunfei.setTextColor(Color.parseColor("#999999"));
                mYunfei.invalidate();
                mYunfeeReduceMark.setEnabled(false);
                mTransferChargesRemark.setEnabled(false);
                mYunfeiAfterReduced.setText(Utils.getCurrencyStr(mOrder.getPickUpFee() - mReduceYunfeeAmount));
                mYunfeiAfterReduced.setVisibility(View.VISIBLE);

                mIntent.putExtra(WaitToSentActivity.OP_MODE, WaitToSentActivity.OP_MODE_CHANGE);
                mIntent.putExtra("order", mOrder.getWaybillNo());
                setResult(RESULT_OK, mIntent);
                float yunFee = mYunfeiAfterReduced.getVisibility() == View.VISIBLE ? Utils.currencyStrToNumber(mYunfeiAfterReduced.getText().toString()) : Utils.currencyStrToNumber(mYunfei.getText().toString());
                float daiShou = mDaishouAfterReduce.getVisibility() == View.VISIBLE ? Utils.currencyStrToNumber(mDaishouAfterReduce.getText().toString()) : Utils.currencyStrToNumber(mDaishou.getText().toString());
                mTotalFee.setText(Utils.getCurrencyStr(yunFee + daiShou));
                mTotalFee.invalidate();
                mYunFeeHasReduce = true;
                mBtnPay.setEnabled(mYunFeeHasReduce && mDaishouHasReduce);
                SmartToast.showInCenter("运费减款成功");
                break;
            case HttpRequestType.REDUCE_DAISHOU:
                mDaishouReduceMark.setEnabled(false);
                mIntent.putExtra(EXTRA_DAISHOU_REDUCE_AMOUNT, mReduceDaishouAmount);
                mIntent.putExtra(WaitToSentActivity.OP_MODE, WaitToSentActivity.OP_MODE_CHANGE);
                mIntent.putExtra("order", mOrder.getWaybillNo());
                setResult(RESULT_OK, mIntent);
                mDaishou.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                mDaishou.setTextColor(Color.parseColor("#999999"));
                mDaishou.invalidate();
                mDaishou.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);

                mDaishouAfterReduce.setText(Utils.getCurrencyStr(Float.parseFloat(mOrder.getCollectionTradeCharges()) - mReduceDaishouAmount));
                mDaishouAfterReduce.setVisibility(View.VISIBLE);
                float yunFee2 = mYunfeiAfterReduced.getVisibility() == View.VISIBLE ? Utils.currencyStrToNumber(mYunfeiAfterReduced.getText().toString()) : Utils.currencyStrToNumber(mYunfei.getText().toString());
                float daiShou2 = mDaishouAfterReduce.getVisibility() == View.VISIBLE ? Utils.currencyStrToNumber(mDaishouAfterReduce.getText().toString()) : Utils.currencyStrToNumber(mDaishou.getText().toString());
                mTotalFee.setText(Utils.getCurrencyStr(yunFee2 + daiShou2));
                mTotalFee.invalidate();
                mDaishouHasReduce = true;
                mBtnPay.setEnabled(mYunFeeHasReduce && mDaishouHasReduce);
                SmartToast.showInCenter("代收金额减款成功！");
                break;
            case HttpRequestType.CASH_PAY:
                mIntent.putExtra("order", mOrder.getWaybillNo());
                setResult(RESULT_OK, mIntent);
                EventBus.getDefault().postSticky(new PayEvent(PayEvent.PAY_SUCCESSFUL));
                finish();
                break;
            case HttpRequestType.ORDER_DETAIL:

                mOrder = ((OrderDetail) bean.getReturnData()).getWaybillInfo();
                if (!TextUtils.isEmpty(mOrder.getCompanyUrl())) {
                    MyApplication.sMyApplication.loadCompanyLogo(mOrder.getCompanyUrl());
                }

                switch (mOrder.getPayMode()) {
                    case "月结":
                        mYunFeeLabel.setText("运费（月结 " + PrintInfo.processFee(Float.parseFloat(mOrder.getMonthAmount())) + "）");
                        break;
                    case "现付":
                        mYunFeeLabel.setText("运费（现付 " + PrintInfo.processFee(Float.parseFloat(mOrder.getCashAmount())) + "）");
                        break;
                    case "回付":
                        mYunFeeLabel.setText("运费（回付 " + PrintInfo.processFee(Float.parseFloat(mOrder.getReceiptPayAmount())) + "）");
                        break;
                }


                    mDeliverFee.append(processFee(mOrder.getDeliveryFee()));
                    mDeliverFee.setVisibility(View.VISIBLE);


                mDispatchBranchName.setText("寄件点：" + mOrder.getSendBranchName());
                mDispatchBranchTel.setText(" " + mOrder.getSendBranchContactTel());
                if (!TextUtils.isEmpty(mOrder.getDispatchBranchContactTel())) {
                    mDialDispatchBranch.setVisibility(View.VISIBLE);
                }
                mSendWay.setText("送货方式：" + mOrder.getPickUpWay());
                mTransferCharges.setText(Utils.getCurrencyStr(mOrder.getTransferFee()));
                mTransferCompany.setText("中转公司：" + mOrder.getTransferCompanyName());
                mTransferNo.setText("中转运单号：" + mOrder.getTransferNo());
                if (TextUtils.isEmpty(mOrder.getTransferCompanyName())) {
                    mTransferCompany.setVisibility(View.GONE);
                    mTransferNo.setVisibility(View.GONE);
                }
                mOrderNumber.append(mOrder.getWaybillNo());
                mSender.append(mOrder.getShipper());
                mSenderNumber.append(mOrder.getDeliveryTel());
                mSendAddress.append(mOrder.getDeliveryAddress());
                mReceiver.append(mOrder.getReceiver());
                mReceiverNumber.append(mOrder.getReceiveTel());
                mReceiverAddress.append(mOrder.getReceiveAddress());
                mGoodsName.append(mOrder.getCargoName());
                mJianshu.append(mOrder.getTotalNumber() + "件");
                mPayMode.append(mOrder.getPayMode());
                mSendTime.append(mOrder.getOperationTime());
                mOpenRemark.append(mOrder.getRemark());
                mOrderPaperNumber.append(mOrder.getPaperNumber());
                mDialSender.setVisibility(TextUtils.isEmpty(mOrder.getDeliveryTel()) ? View.INVISIBLE : View.VISIBLE);
                mDialReceiver.setVisibility(TextUtils.isEmpty(mOrder.getReceiveTel()) ? View.INVISIBLE : View.VISIBLE);
                mOrderPaperNumber.setVisibility(TextUtils.isEmpty(mOrder.getPaperNumber().trim()) ? View.GONE : View.VISIBLE);
                mIsSendAndLoad = mOrder.getIsSendAndLoad();


                if (mOrder.getOriginalFerightAmount() != mOrder.getPickUpFee()) {
                    mYunfei.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                    mYunfei.setTextColor(Color.parseColor("#999999"));
                    mYunfeiAfterReduced.setVisibility(View.VISIBLE);
                    mYunfeeReduceMark.setEnabled(false);
                    mTransferChargesRemark.setEnabled(false);
                } else {
                    mTransferChargesRemark.setEnabled(true);
                }


                mYunfei.append(Utils.getCurrencyStr(mOrder.getOriginalFerightAmount()));
                mYunfeiAfterReduced.setText(Utils.getCurrencyStr(mOrder.getPickUpFee()));

                if (Float.parseFloat(mOrder.getCollectionTradeCharges()) != mOrder.getOriginalPaymentAmount()) {
                    mDaishou.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
                    mDaishou.setTextColor(Color.parseColor("#999999"));
                    mDaishouAfterReduce.setVisibility(View.VISIBLE);
                    mDaishouReduceMark.setEnabled(false);
                }
                mDaishou.append(Utils.getCurrencyStr(mOrder.getOriginalPaymentAmount()));
                mDaishouAfterReduce.setText(Utils.getCurrencyStr(Float.parseFloat(mOrder.getCollectionTradeCharges())));

                mGoodsCount.append(mOrder.getTotalNumber() + "件  合计: ");
                mTotalFee.append(getTotalFeeStr());


                Float rebate = mOrder.getRebate();
                int rebateWay = mOrder.getRebateWay();
                float transferFee = mOrder.getTransferFee();

                judgeReduceYunFeeMark(rebate, rebateWay, transferFee);

                if (Float.parseFloat(mOrder.getCollectionTradeCharges()) == 0) {
                    mDaishouReduceMark.setEnabled(false);
                }
                if (mIsSendAndLoad == 1) {
                    mJijieMark.setVisibility(View.VISIBLE);
                } else {
                    mJijieMark.setVisibility(View.GONE);

                    HttpTools.getAuthority(this, this);
                }

                mUntilEndMark.setVisibility(mOrder.getIsToEnd() == 1 ? View.VISIBLE : View.INVISIBLE);

                String imgUrl = mOrder.getCompanyUrl();

                mGoodsNameNumber.append(mOrder.getWaybillCargoNo());
                mGoodsNameNumber.setVisibility(TextUtils.isEmpty(mOrder.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                mBtnPay.setEnabled(true);

                break;
            case HttpRequestType.DEBIT_CONFIG:
                mDebitConfig = (DebitConfig) bean.getReturnData();
                Logger.d(JsonUtils.jsonStr(mDebitConfig));
                break;
            case HttpRequestType.GET_AUTHORITY:
                Authority authority = (Authority) bean.getReturnData();
                if (authority.getReduceFreightAuthority() == 1) {
                    mYunfeeReduceMark.setVisibility(View.VISIBLE);
                }

                if (authority.getReducePaymentAuthority() == 1) {
                    mDaishouReduceMark.setVisibility(View.VISIBLE);
                }

                break;
            case HttpRequestType.UPDATE_TRANSFER_FEE:
                mTransferCompany.setText("中转公司：" + mNewTransferCompany);
                mTransferNo.setText("中转运单号：" + mNewTransferNo);
                mTransferCompany.setVisibility(View.VISIBLE);
                mTransferNo.setVisibility(View.VISIBLE);
                mTransferCharges.setText(Utils.getCurrencyStr(Float.parseFloat(mNewTransferCharge)));
                mOrder.setTransferFee(Float.parseFloat(mNewTransferCharge));
                mOrder.setTransferCompanyName(mNewTransferCompany);
                mOrder.setTransferNo(mNewTransferNo);
                mOrder.setIsRegisteTransfer(1);
                mIntent.putExtra(WaitToSentActivity.OP_MODE, WaitToSentActivity.OP_MODE_CHANGE);
                mIntent.putExtra("order", mOrder.getWaybillNo());
                mIntent.putExtra("has_register", true);
                judgeReduceYunFeeMark(mOrder.getRebate(), mOrder.getRebateWay(), mOrder.getTransferFee());
                setResult(RESULT_OK, mIntent);
                SmartToast.showInCenter("中转费录入成功！");
                break;
            case HttpRequestType.UPLOAD_SIGN_PIC:
                List<SignPic> signPics = new ArrayList<SignPic>(3);
                List<String> remoteUrls = ((UploadImgResult) bean.getReturnData()).getPath();
                for (int i = 0; i < remoteUrls.size(); i++) {
                    SignPic signPic = new SignPic();
                    signPic.setLocalPath(mLocalPath.get(i));
                    signPic.setRemoteUrl(remoteUrls.get(i));
                    signPics.add(signPic);
                }
                ((SignPicAdapter) mPicList.getAdapter()).add(signPics);
                SmartToast.showInCenter("上传成功！");

                break;
            case HttpRequestType.DELETE_PIC:

                ((SignPicAdapter) mPicList.getAdapter()).remove(mDeletePos);
                MyApplication.sCancelNetTip = false;
                break;
            case HttpRequestType.GET_ORDER_PIC:
                mPicList.setVisibility(View.VISIBLE);

                List<OrderPics.ReturnDataBean> hasUpload = ((OrderPics) bean.getReturnData()).getReturnData();
                List<SignPic> showItems = null;
                if (!hasUpload.isEmpty()) {
                    showItems = new ArrayList<SignPic>(3);
                    for (int i = 0; i < hasUpload.size(); i++) {
                        SignPic signPic = new SignPic();
                        signPic.setLocalPath("");
                        signPic.setRemoteUrl(hasUpload.get(i).getImageUrl());
                        showItems.add(signPic);
                    }
                    ((SignPicAdapter) mPicList.getAdapter()).add(showItems);
                }

                break;
        }

    }

    private void judgeReduceYunFeeMark(Float rebate, int rebateWay, float transferFee) {

        float canReduceAmount = 0;

        if (rebateWay == 2 || rebateWay == 3) {
            canReduceAmount = mOrder.getBasicFee() - rebate - transferFee;
        } else {
            canReduceAmount = mOrder.getBasicFee() - transferFee;
        }

        if (canReduceAmount <= 0 || mOrder.getPickUpFee() == 0 || mOrder.getOriginalFerightAmount() != mOrder.getPickUpFee()) {
            mYunfeeReduceMark.setEnabled(false);
        } else {
            mYunfeeReduceMark.setEnabled(true);
        }
    }

    private DebitConfig mDebitConfig;

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type) {
            case HttpRequestType.DELETE_PIC:
                SmartToast.showLongInCenter("图片删除失败！");
                break;
            case HttpRequestType.UPLOAD_SIGN_PIC:
                SmartToast.showLongInCenter("图片上传失败");

                break;

        }
    }


    int mIsSendAndLoad;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mOrderStr = getIntent().getStringExtra(WaitToSentActivity.EXTRA_WAIT_SEND_DETAIL_JSON);
        HttpTools.retrieveOrderPics(this, this, mOrderStr);

        mPrintOrder.setEnabled(false);
        mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.c_light_gray));
        HttpTools.orderDetail(this, mOrderStr, this);

        mTotalFee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(Utils.getCurrencyStr(0))) {
                    mBtnPay.setText("确认签收");
                } else {
                    mBtnPay.setText("立即支付");
                }
            }
        });
        HttpTools.getDebitConfig(this, this);
        mSignRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() <= 500) {
                    mRemarkNum.setTextColor(ContextCompat.getColor(MyApplication.sContext, R.color.colorPrimary));
                    mRemarkNum.setText("(" + s.length() + "/500)");
                    return;
                }

                if (s.length() > 500) {
                    mRemarkNum.setTextColor(Color.parseColor("#cc0000"));
                    mRemarkNum.setText("(" + (500 - s.length()) + ")");
                    return;
                }
            }
        });
    }

    public DebitConfig getDebitConfig() {
        return mDebitConfig;
    }

    public void setDebitConfig(DebitConfig debitConfig) {
        mDebitConfig = debitConfig;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#888888"));
        }


        mStatusBox = new StatusBox(this, getRootView());
        mPicList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });

        mPicList.setAdapter(new SignPicAdapter(mPicList));
        mTransferChargesRemark.setEnabled(false);
    }


    private PopupWindow mPayWindow;
    PayItemClickListener mPayItemClickListener;


    @OnClick({R.id.dial_sender, R.id.dial_receiver, R.id.dial_dispatch_branch, R.id.btn_pay, R.id.transfer_charges_remark})
    public void onClick(View view) {
        String phoneNumber;
        switch (view.getId()) {
            case R.id.dial_sender:
                phoneNumber = mSenderNumber.getText().toString().trim();
                Utils.dialSomeone(this, phoneNumber);
                break;
            case R.id.dial_receiver:
                phoneNumber = mReceiverNumber.getText().toString().trim();
                Utils.dialSomeone(this, phoneNumber);
                break;
            case R.id.dial_dispatch_branch:
                phoneNumber = mDispatchBranchTel.getText().toString().trim();
                Utils.dialSomeone(this, phoneNumber);
                break;
            case R.id.btn_pay:
                if (mOrder.getIsToEnd() == 1 && mOrder.getIsRegisteTransfer() == 0) {
                    SmartToast.showInCenter("该单需要先录入中转费!");
                    return;
                }
                toPay();
                break;
            case R.id.transfer_charges_remark:

                getSetTransferFeeDialog().show();
                break;
        }
    }

    private AlertDialog mSetTransferFeeDialog;
    private View mTransferFeeDialogContent;

    private AlertDialog getSetTransferFeeDialog() {

        if (mSetTransferFeeDialog == null) {
            mTransferFeeDialogContent = LayoutInflater.from(this).inflate(R.layout.set_transfer_fee, null);

            final float max = mOrder.getRebateWay() != 1 ? (mOrder.getBasicFee() - mOrder.getRebate()) : mOrder.getBasicFee();
            ((TextView) mTransferFeeDialogContent.findViewById(R.id.operation_tip)).setText("*该票中转费不得超过" + sDecimalFormat.format(max) + "元");

            ((EditText) mTransferFeeDialogContent.findViewById(R.id.input_amount)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString().trim())) {
                        mTransferFeeDialogContent.findViewById(R.id.confirmBtn).setEnabled(false);
                        return;
                    }

                    mTransferFeeDialogContent.findViewById(R.id.confirmBtn).setEnabled(Float.parseFloat(s.toString().trim()) <= max);
                }
            });

            mTransferFeeDialogContent.findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mNewTransferCompany = ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_company)).getText().toString().trim();
                    mNewTransferNo = ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_no)).getText().toString().trim();

                    if (mNewTransferCompany.length() > 50) {
                        mNewTransferCompany = mNewTransferCompany.substring(0, 50);
                    }

                    if (mNewTransferNo.length() > 50) {
                        mNewTransferNo = mNewTransferNo.substring(0, 50);
                    }

                    if (TextUtils.isEmpty(mNewTransferCompany) || TextUtils.isEmpty(mNewTransferNo)) {
                        SmartToast.showAtTop("中转公司或中转运单号不可为空！");
                        return;
                    }

                    InputMethodManager imm = (InputMethodManager) WaitSendDetailActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mTransferFeeDialogContent.getWindowToken(), 0);
                    mSetTransferFeeDialog.dismiss();
                    mNewTransferCharge = ((EditText) mTransferFeeDialogContent.findViewById(R.id.input_amount)).getText().toString().trim();

                    HttpTools.updateTransferFee(WaitSendDetailActivity.this, WaitSendDetailActivity.this, mOrderStr, mNewTransferCharge, mNewTransferCompany, mNewTransferNo);
                }
            });


            ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_company)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 50) {
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.company_error)).setErrorEnabled(true);
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.company_error)).setError("公司名最多输入50字！");

                    } else {
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.company_error)).setErrorEnabled(false);
                    }

                }
            });


            ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_no)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 50) {
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.transfer_no_error)).setErrorEnabled(true);
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.transfer_no_error)).setError("中转运单号最多输入50个数字！");
                    } else {
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.transfer_no_error)).setErrorEnabled(false);
                    }

                }
            });


            mSetTransferFeeDialog = new AlertDialog.Builder(this)
                    .setView(mTransferFeeDialogContent)
                    .create();
            mSetTransferFeeDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_company)).setText(mOrder.getTransferCompanyName());
        ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_no)).setText(mOrder.getTransferNo());
        ((EditText) mTransferFeeDialogContent.findViewById(R.id.input_amount)).setText(PrintInfo.processFee(mOrder.getTransferFee()));
        return mSetTransferFeeDialog;
    }

    private void toPay() {
        if (mBtnPay.getText().toString().equals("立即支付")) {

            if (mSignRemarks.getText().length() > 500) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this)
                        .setConfirmText("确定")
                        .setContentText("备注信息限制在500字以内，请重新编辑！")
                        .setTitleText("提示");
                sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                    }
                });
                sweetAlertDialog.show();
                return;
            }

            if (mPayWindow == null) {

                View root = getLayoutInflater().inflate(R.layout.pay_pop, null);


                mPayWindow = new PopupWindow(root, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                OfflineInfo offlineInfo = new OfflineInfo(getIntent().getStringExtra(WaitToSentActivity.EXTRA_WAIT_SEND_DETAIL_JSON), mOrder.getReceiver(), mSignRemarks.getText().toString().trim(), mOrder.getWaybillId(), Utils.currencyStrToNumber(mYunfeiAfterReduced.getVisibility() == View.VISIBLE ? mYunfeiAfterReduced.getText().toString() : mYunfei.getText().toString()), Utils.currencyStrToNumber(mDaishouAfterReduce.getVisibility() == View.VISIBLE ? mDaishouAfterReduce.getText().toString() : mDaishou.getText().toString()), mIsSendAndLoad == 1);

                mPayItemClickListener = new PayItemClickListener(this, mPayWindow, offlineInfo);
                root.findViewById(R.id.online_pay).setOnClickListener(mPayItemClickListener);
                root.findViewById(R.id.offline_pay).setOnClickListener(mPayItemClickListener);
                root.findViewById(R.id.cancel_pay).setOnClickListener(mPayItemClickListener);
                mPayWindow.setFocusable(true);
                mPayWindow.setAnimationStyle(R.style.pay_pop_anim);
            }

            String signer = mSigner.getText().toString().trim();
            if (signer.isEmpty()){
                signer = mOrder.getReceiver();
            }else if (signer.length() > 15){
                signer = signer.substring(0,15);
            }
            OfflineInfo newOfflineInfo = new OfflineInfo(getIntent().getStringExtra(WaitToSentActivity.EXTRA_WAIT_SEND_DETAIL_JSON), signer, mSignRemarks.getText().toString().trim(), mOrder.getWaybillId(), Utils.currencyStrToNumber(mYunfeiAfterReduced.getVisibility() == View.VISIBLE ? mYunfeiAfterReduced.getText().toString() : mYunfei.getText().toString()), Utils.currencyStrToNumber(mDaishouAfterReduce.getVisibility() == View.VISIBLE ? mDaishouAfterReduce.getText().toString() : mDaishou.getText().toString()), mIsSendAndLoad == 1);

            mPayItemClickListener.setOfflineInfo(newOfflineInfo);
            mPayWindow.showAtLocation(getRootView(), Gravity.NO_GRAVITY, 0, 0);
        } else {
            final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
            sweetAlertDialog.setTitleText("提示")
                    .setConfirmText("确定")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            String signer = mSigner.getText().toString().trim();
                            if (signer.isEmpty()){
                                signer = mOrder.getReceiver();
                            }else if (signer.length() > 15){
                                signer = signer.substring(0,15);
                            }
                            HttpTools.cashPay(WaitSendDetailActivity.this, mOrderStr, signer, mSignRemarks.getText().toString().trim(), 0, 0, WaitSendDetailActivity.this);
                        }
                    })
                    .setCancelText("取消")
                    .setContentText("无需支付费用，确定签收？");
            sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                }
            });

            sweetAlertDialog.show();

        }
    }


    PopupWindow mPopupWindow;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
                return true;
            }

            if (mPayWindow != null && mPayWindow.isShowing()) {
                mPayWindow.dismiss();
                return true;
            }

        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPayItemClickListener != null) {
            mPayItemClickListener.onActivityExit();
            mPayItemClickListener = null;
        }

        ((SignPicAdapter) mPicList.getAdapter()).onExit();
    }


    private float getTotalFee() {
        return mOrder.getPickUpFee() + Float.parseFloat(mOrder.getCollectionTradeCharges());
    }

    private String getTotalFeeStr() {
        return Utils.getCurrencyStr(getTotalFee());
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 222 && resultCode == RESULT_OK && data != null) {
            if (data.getBooleanExtra(PAY_FINISH, false) == true) {
                mIntent.putExtra(WaitToSentActivity.OP_MODE, WaitToSentActivity.OP_MODE_REMOVE);
                mIntent.putExtra(EXTRA_SIGN_REMARKS, mSignRemarks.getText().toString().trim());
                mIntent.putExtra("order", mOrder.getWaybillNo());
                setResult(RESULT_OK, mIntent);
                finish();
            }
        }
        if (requestCode == 2) {

            if (SharePrefUtils.getSavedDeviceAddress().isEmpty() || mBluetoothAdapter.getRemoteDevice(SharePrefUtils.getSavedDeviceAddress()).getBondState() != BluetoothDevice.BOND_BONDED) {
                SharePrefUtils.storeDeviceAddress("");
                SharePrefUtils.storeDeviceName("");
                startActivity(new Intent(this, BluetoothActivity.class));
            } else {
                getStatusBox().Show("正在连接打印机...");
                PrintInfo printInfo = new PrintInfo(mOrder, "")
                        .setIsNotSequence(1)
                        .setNeedPrintSequence(0);
                if (SharePrefUtils.getSavedDeviceName().startsWith("HDT334") || SharePrefUtils.getSavedDeviceAddress().startsWith("HDT312")) {
                    PrintService.startService(printInfo);
                    return;
                }

                if (SharePrefUtils.getSavedDeviceName().startsWith("Printer_")) {
                    JiaboPrintService.startService(printInfo);
                }
            }

        }


        if (requestCode == PicAdapter.ViewHolder.REQ_SELECT && resultCode == RESULT_OK && data != null) {
            mLocalPath = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (!mLocalPath.isEmpty()) {
                bmps = new Bitmap[mLocalPath.size()];
                mCount = mLocalPath.size();
                for (int i = 0; i < mLocalPath.size(); i++) {
                    final int finalI = i;
                    Glide.with(this).load(mLocalPath.get(i)).asBitmap().override(400, 600).into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            mCount--;
                            bmps[finalI] = resource;
                            if (mCount == 0) {
                                BitmapTools.uploadSignPic(WaitSendDetailActivity.this, mOrderStr, Arrays.asList(bmps));
                            }
                        }
                    });
                }
            }


        }
        if (requestCode == PicAdapter.ViewHolder.REQ_CAMERA && resultCode == RESULT_OK) {
            Logger.d("我爱你" + mOutPutFileUri);
            mLocalPath = new ArrayList<>(1);
            mLocalPath.add(mOutPutFileUri.getEncodedPath());
            Glide.with(this).load(mOutPutFileUri.getEncodedPath()).asBitmap().override(400, 600).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    BitmapTools.bitmapToBase64(WaitSendDetailActivity.this, resource, new BitmapTools.CallBack() {
                        @Override
                        public void onConvertFinish(String result) {
                            List<String> l = new ArrayList<String>(1);
                            l.add(result);
                            HttpTools.uploadSignPic(WaitSendDetailActivity.this, WaitSendDetailActivity.this, mOrderStr, l);
                        }
                    });
                }
            });
        }

    }


    private ImageView mTempImageView;

    private int mCount;

    private Bitmap[] bmps;


    private BluetoothAdapter mBluetoothAdapter;

    @OnClick(R.id.print_order)
    public void onViewClicked() {
        SharePrefUtils.setCancelConnect(false);
        mPrintOrder.setEnabled(false);
        mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.c_light_gray));
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            SmartToast.showInCenter("该设备不支持蓝牙!");
        } else if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 2);
        } else {

            if (SharePrefUtils.getSavedDeviceAddress().isEmpty() || mBluetoothAdapter.getRemoteDevice(SharePrefUtils.getSavedDeviceAddress()).getBondState() != BluetoothDevice.BOND_BONDED) {
                SharePrefUtils.storeDeviceAddress("");
                SharePrefUtils.storeDeviceName("");
                startActivity(new Intent(this, BluetoothActivity.class));
            } else {
                getStatusBox().Show("正在连接打印机...");
                mOrder.setPickUpFee(mYunfeiAfterReduced.getVisibility() == View.VISIBLE ? Utils.currencyStrToNumber(mYunfeiAfterReduced.getText().toString()) : Utils.currencyStrToNumber(mYunfei.getText().toString()));
                mOrder.setCollectionTradeCharges(mDaishouAfterReduce.getVisibility() == View.VISIBLE ? String.valueOf(Utils.currencyStrToNumber(mDaishouAfterReduce.getText().toString())) : String.valueOf(Utils.currencyStrToNumber(mDaishou.getText().toString())));

                PrintInfo printInfo = new PrintInfo(mOrder, "")
                        .setIsNotSequence(1)
                        .setNeedPrintSequence(0);

                String deviceName = SharePrefUtils.getSavedDeviceName();

                if (deviceName.startsWith("XT") || deviceName.startsWith("HDT")) {
                    PrintService.startService(printInfo);
                    return;
                }

                if (SharePrefUtils.getSavedDeviceName().startsWith("Printer_")) {
                    JiaboPrintService.startService(printInfo);
                }

            }
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mPrintOrder.setEnabled(true);
        mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mStatusBox.Close();
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
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                final Snackbar snackbar = Snackbar.make(toolbar, "蓝牙已关闭，请开启蓝牙后重试！", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("开启蓝牙", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                        BluetoothAdapter.getDefaultAdapter().enable();
                    }
                });
                break;
            case PrintEvent.FAIL_DEVICE_NOT_BONDED:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                final Snackbar snackbar2 = Snackbar.make(toolbar, "蓝牙已关闭，请开启蓝牙后重试！", Snackbar.LENGTH_INDEFINITE);
                snackbar2.setAction("去重新配对", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar2.dismiss();
                        startActivity(new Intent(WaitSendDetailActivity.this, BluetoothActivity.class));
                    }
                });
                break;
            case PrintEvent.FAIL_REASON_UNKNOWN:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                getPrintAlert().show();
                break;
            case PrintEvent.PRINT_COMPLETE:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                Snackbar.make(toolbar, "打印完成！", Snackbar.LENGTH_SHORT).show();
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
                SmartToast.showInCenter("打印机缺纸，请续纸后重试！");
                break;
            case PrintEvent.PRINTER_ERROR:
                mPrintOrder.setEnabled(true);
                mPrintOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getStatusBox().Close();
                getPrintAlert().show();
                SmartToast.showInCenter("打印机内部错误，请重试！");
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

        if (mSelectMethod != null && mSelectMethod.isShowing()) {
            mSelectMethod.dismiss();
            return;
        }


        if (getStatusBox().isShowing()) {
            getStatusBox().Close();
        } else if (mSignRemarks.getText().length() > 500) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this)
                    .setConfirmText("确定")
                    .setContentText("备注信息限制在500字以内，请重新编辑！")
                    .setTitleText("提示");
            sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                }
            });
            sweetAlertDialog.show();
        } else {
            super.onBackPressed();
        }
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

                    onViewClicked();
                }
            });
            mPrintAlert = new AlertDialog.Builder(this)
                    .setView(content)
                    .create();
        }

        return mPrintAlert;
    }


    private float mReduceYunfeeAmount;
    private float mReduceDaishouAmount;

    private AlertDialog mReduceYunfeeDialog;
    private View mReduceYunfeeContent;

    public AlertDialog getReduceYunfeeDialog(final float max) {
        if (mReduceYunfeeDialog == null) {
            mReduceYunfeeContent = LayoutInflater.from(MyApplication.sContext).inflate(R.layout.reduce_dialog, null);
            ((TextView) mReduceYunfeeContent.findViewById(R.id.title)).setText("请输入减运费金额");
            mReduceYunfeeDialog = new AlertDialog.Builder(this)
                    .setView(mReduceYunfeeContent)
                    .create();
            mReduceYunfeeDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        ((TextView) mReduceYunfeeContent.findViewById(R.id.operation_tip)).setText("*可减运费  0~" + PrintInfo.processFee(max) + "元");
        ((EditText) mReduceYunfeeContent.findViewById(R.id.input_amount)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mReduceYunfeeContent.findViewById(R.id.confirmBtn).setEnabled(false);
                    return;
                }

                float f = Float.parseFloat(s.toString());
                mReduceYunfeeAmount = f;
                if (f == 0 || f > max) {
                    mReduceYunfeeContent.findViewById(R.id.confirmBtn).setEnabled(false);
                } else {
                    mReduceYunfeeContent.findViewById(R.id.confirmBtn).setEnabled(true);
                }
            }
        });

        mReduceYunfeeContent.findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float amount = Float.parseFloat(((EditText) mReduceYunfeeContent.findViewById(R.id.input_amount)).getText().toString());
                HttpTools.reduceYunfee(WaitSendDetailActivity.this, WaitSendDetailActivity.this, mOrder.getWaybillId(), amount);
                mYunFeeHasReduce = false;
                mBtnPay.setEnabled(mYunFeeHasReduce && mDaishouHasReduce);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(mReduceYunfeeContent.getWindowToken(), 0);
                }
                mReduceYunfeeDialog.dismiss();

            }
        });
        return mReduceYunfeeDialog;
    }

    private AlertDialog mReduceDaishouDilaog;
    private View mReduceDaishouContent;

    public AlertDialog getReduceDaishDialog(final float max) {
        if (mReduceDaishouDilaog == null) {
            mReduceDaishouContent = LayoutInflater.from(MyApplication.sContext).inflate(R.layout.reduce_dialog, null);
            ((TextView) mReduceDaishouContent.findViewById(R.id.title)).setText("请输入减代收款金额");
            mReduceDaishouDilaog = new AlertDialog.Builder(this)
                    .setView(mReduceDaishouContent)
                    .create();
            mReduceDaishouDilaog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        ((TextView) mReduceDaishouContent.findViewById(R.id.operation_tip)).setText("*可减代收款 0~" + PrintInfo.processFee(max) + "元");
        ((EditText) mReduceDaishouContent.findViewById(R.id.input_amount)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mReduceDaishouContent.findViewById(R.id.confirmBtn).setEnabled(false);
                    return;
                }

                float f = Float.parseFloat(s.toString());
                mReduceDaishouAmount = f;
                if (f == 0 || f > max) {
                    mReduceDaishouContent.findViewById(R.id.confirmBtn).setEnabled(false);
                } else {
                    mReduceDaishouContent.findViewById(R.id.confirmBtn).setEnabled(true);
                }
            }
        });

        mReduceDaishouContent.findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float amount = Float.parseFloat(((EditText) mReduceDaishouContent.findViewById(R.id.input_amount)).getText().toString());
                HttpTools.reduceDaishou(WaitSendDetailActivity.this, WaitSendDetailActivity.this, mOrder.getWaybillId(), amount);
                mDaishouHasReduce = false;
                mBtnPay.setEnabled(mYunFeeHasReduce && mDaishouHasReduce);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(mReduceDaishouContent.getWindowToken(), 0);
                }
                mReduceDaishouDilaog.dismiss();
            }
        });

        return mReduceDaishouDilaog;
    }

    @OnClick({R.id.yunfee_reduce_mark, R.id.daishou_reduce_mark})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yunfee_reduce_mark:
                if (mOrder.getIsToEnd() == 1 && mOrder.getIsRegisteTransfer() == 0) {
                    SmartToast.showInCenter("该单需要先录入中转费!");
                    return;
                }
                float finalFee = mOrder.getBasicFee();
                if (mOrder.getRebateWay() != 1) {
                    finalFee -= mOrder.getRebate();
                }
                if (Utils.currencyStrToNumber(mTransferCharges.getText().toString().trim()) != 0) {
                    finalFee -= Utils.currencyStrToNumber(mTransferCharges.getText().toString().trim());
                }
                getReduceYunfeeDialog(finalFee).show();
                break;
            case R.id.daishou_reduce_mark:
                getReduceDaishDialog(Float.parseFloat(mOrder.getCollectionTradeCharges())).show();
                break;
        }
    }


    private Uri mOutPutFileUri;

    private PopupWindow mSelectMethod;


    public void setOutPutFileUri(Uri outPutFileUri) {
        mOutPutFileUri = outPutFileUri;
    }


    public void setSelectMethod(PopupWindow selectMethod) {
        mSelectMethod = selectMethod;
    }


    private int mDeletePos;

    public void onDeleteClick(int adapterPosition) {

        mDeletePos = adapterPosition;

        List<String> l = new ArrayList<String>(1);
        l.add(((SignPicAdapter) mPicList.getAdapter()).getData().get(adapterPosition).getRemoteUrl());
        HttpTools.deleteSignPic(this, this, mOrderStr, l);

    }
}
