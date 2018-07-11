package com.liinji.ppgdeliver.bean;

/**
 * Created by pig on 2017/12/28.
 */

public class GlobalMsg {

    private String waybillNo;
    private String mId;

    private String IsForced;

    public String getIsForced() {
        return IsForced;
    }

    public void setIsForced(String isForced) {
        IsForced = isForced;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }
}
