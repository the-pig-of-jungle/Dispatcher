package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/9/5.
 */

public class HomeOrderNumber {

    /**
     * WaitForNumber : 15
     * SendLoadNumber : 7
     */

    private int WaitForNumber;
    private int SendLoadNumber;

    private int IsForced;

    public int getIsForced() {
        return IsForced;
    }

    public void setIsForced(int isForced) {
        IsForced = isForced;
    }

    public int getWaitForNumber() {
        return WaitForNumber;
    }

    public void setWaitForNumber(int WaitForNumber) {
        this.WaitForNumber = WaitForNumber;
    }

    public int getSendLoadNumber() {
        return SendLoadNumber;
    }

    public void setSendLoadNumber(int SendLoadNumber) {
        this.SendLoadNumber = SendLoadNumber;
    }
}
