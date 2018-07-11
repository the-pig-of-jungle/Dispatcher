package com.liinji.ppgdeliver.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/21.
 */
public class PaybackDetailActivity extends BaseLightStatusBarActivity {
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

    private String mOrderNumberStr;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mOrderNumberStr = getIntent().getStringExtra("order");
        HttpTools.orderDetail(this, mOrderNumberStr, this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        OrderDetail orderDetail = (OrderDetail) bean.getReturnData();
        mOrderNumber.append(orderDetail.getWaybillInfo().getWaybillNo());
        mSender.append(orderDetail.getWaybillInfo().getShipper());
        mSenderNumber.append(orderDetail.getWaybillInfo().getDeliveryTel());
        mSendAddress.append(orderDetail.getWaybillInfo().getDeliveryAddress());
        mReceiver.append(orderDetail.getWaybillInfo().getReceiver());
        mReceiverNumber.append(orderDetail.getWaybillInfo().getReceiveTel());
        mReceiverAddress.append(orderDetail.getWaybillInfo().getReceiveAddress());
        mGoodsName.append(orderDetail.getWaybillInfo().getCargoName());
        mPayMode.append(orderDetail.getWaybillInfo().getPayMode());
        mJianshu.append(orderDetail.getWaybillInfo().getTotalNumber() + "件");
        mSignTime.append(orderDetail.getWaybillInfo().getSignDate() + "");
        mYunfei.append(Utils.getCurrencyStr(orderDetail.getWaybillInfo().getPickUpFee()));
        mDaishou.append(Utils.getCurrencyStr(Float.parseFloat(orderDetail.getWaybillInfo().getCollectionTradeCharges())));
        mGoodsCount.append(orderDetail.getWaybillInfo().getTotalNumber() + "件 ");
        mTotalFee.append("合计: " + Utils.getCurrencyStr(getTotalFee(orderDetail.getWaybillInfo())));


    }

    @OnClick({R.id.dial_sender, R.id.dial_receiver})
    public void onClick(View view) {
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

}
