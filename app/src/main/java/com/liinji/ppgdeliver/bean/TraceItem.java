package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/21.
 */
public class TraceItem {
    private String mTime;
    private String mDesc;

    public TraceItem() {

    }

    public TraceItem(String time, String desc) {
        mTime = time;
        mDesc = desc;
    }


    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }
}
