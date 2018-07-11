package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/10/12.
 */

public class QuickDispatchResult {

    private int TotalSucceed;
    private int TotalQty;
    private int TotalFailed;

    public int getTotalSucceed() {
        return TotalSucceed;
    }

    public void setTotalSucceed(int totalSucceed) {
        TotalSucceed = totalSucceed;
    }

    public int getTotalQty() {
        return TotalQty;
    }

    public void setTotalQty(int totalQty) {
        TotalQty = totalQty;
    }

    public int getTotalFailed() {
        return TotalFailed;
    }

    public void setTotalFailed(int totalFailed) {
        TotalFailed = totalFailed;
    }
}
