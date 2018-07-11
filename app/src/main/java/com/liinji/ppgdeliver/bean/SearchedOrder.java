package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/8/21.
 */

public class SearchedOrder {

    /**
     * WaybillId : 774e22ac-9eec-4984-8b5d-13394acf6680
     * WaybillNo : 02200122900402
     * Receiver : 王向往
     */

    private String WaybillId;
    private String WaybillNo;
    private String Receiver;

    public String getWaybillId() {
        return WaybillId;
    }

    public void setWaybillId(String WaybillId) {
        this.WaybillId = WaybillId;
    }

    public String getWaybillNo() {
        return WaybillNo;
    }

    public void setWaybillNo(String WaybillNo) {
        this.WaybillNo = WaybillNo;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }
}
