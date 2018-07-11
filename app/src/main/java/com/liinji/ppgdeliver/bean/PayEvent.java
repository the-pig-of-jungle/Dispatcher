package com.liinji.ppgdeliver.bean;

import android.support.annotation.IntDef;

/**
 * Created by 朱志强 on 2017/9/6.
 */

public class PayEvent {
    public static final int PAY_SUCCESSFUL = 1;
    public static final int PAY_CANCELED= 0;

    @IntDef({PAY_SUCCESSFUL,PAY_CANCELED})
    public @interface PayResult{};

    private @PayResult int mPayResult;

    public PayEvent(int payResult) {
        mPayResult = payResult;
    }

    public int getPayResult() {
        return mPayResult;
    }

    public void setPayResult(int payResult) {
        mPayResult = payResult;
    }
}
