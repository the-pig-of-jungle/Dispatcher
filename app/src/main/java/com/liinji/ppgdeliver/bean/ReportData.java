package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/17.
 */
public class ReportData {
    private double mOffline;
    private double mOnline;
    private double mTotal;

    public ReportData(double offline, double online) {
        mOffline = offline;
        mOnline = online;
        mTotal = offline + online;
    }

    public ReportData() {

    }


    public double getOffline() {
        return mOffline;
    }

    public void setOffline(double offline) {
        mOffline = offline;
    }

    public double getOnline() {
        return mOnline;
    }

    public void setOnline(double online) {
        mOnline = online;
    }

    public double getTotal() {
        return mTotal;
    }

    public void setTotal(double total) {
        mTotal = total;
    }
}
