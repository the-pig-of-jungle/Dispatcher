package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/6/28.
 */

public class OfflineInfo {

    private String mOrderNumber;
    private String mSigner;
    private String mSignRemarks;
    private float mDeliverFee;
    private float mDaishouFee;
    private String mOrderId;

    private boolean mIsJijie;

    public OfflineInfo(String orderNumber, String signer, String signRemarks, String orderId, float deliverFee, float daishouFee, boolean isJijie) {
        mOrderNumber = orderNumber;
        mDeliverFee = deliverFee;
        mDaishouFee = daishouFee;
        mIsJijie = isJijie;
        mSigner = signer;
        mSignRemarks = signRemarks;
        mOrderId = orderId;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public float getDeliverFee() {
        return mDeliverFee;
    }

    public float getDaishouFee() {
        return mDaishouFee;
    }

    public boolean isJijie() {
        return mIsJijie;
    }

    public String getReceiver() {
        return mSigner;
    }

    public String getSignRemarks() {
        return mSignRemarks;
    }

    public String getOrderId() {
        return mOrderId;
    }
}
