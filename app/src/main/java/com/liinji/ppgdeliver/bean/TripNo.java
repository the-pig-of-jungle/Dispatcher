package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/9/13.
 */

public class TripNo {
    private String TripNo;
    private boolean mIsChecked;

    public String getTripNo() {
        return TripNo;
    }

    public void setTripNo(String tripNo) {
        TripNo = tripNo;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }

}
