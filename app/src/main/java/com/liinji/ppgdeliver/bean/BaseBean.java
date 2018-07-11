package com.liinji.ppgdeliver.bean;

import java.io.Serializable;

/**
 * Created by YUEYINGSK on 2016/8/15.
 */
public class BaseBean<ReturnData> implements Serializable {
    private String ReturnCode;//	接口返回结果（1：成功，0：失败）
    private String ReturnMsg;//	返回消息的具体信息
    private ReturnData ReturnData;//	返回的数据对象
    private String ReturnTotalRecords;//返回的总条目数

    private String SumPieces;

    private String SumRecords;


    private int mOverdue5;
    private int mOverdue15;
    private int mOverdue30;
    private String mTotalOrders;

    public int getmOverdue5() {
        return mOverdue5;
    }

    public void setmOverdue5(int mOverdue5) {
        this.mOverdue5 = mOverdue5;
    }

    public int getmOverdue15() {
        return mOverdue15;
    }

    public void setmOverdue15(int mOverdue15) {
        this.mOverdue15 = mOverdue15;
    }

    public int getmOverdue30() {
        return mOverdue30;
    }

    public void setmOverdue30(int mOverdue30) {
        this.mOverdue30 = mOverdue30;
    }

    public String getmTotalOrders() {
        return mTotalOrders;
    }

    public void setmTotalOrders(String mTotalOrders) {
        this.mTotalOrders = mTotalOrders;
    }

    public String getSumPieces() {
        return SumPieces;
    }

    public void setSumPieces(String sumPieces) {
        SumPieces = sumPieces;
    }

    public String getSumRecords() {
        return SumRecords;
    }

    public void setSumRecords(String sumRecords) {
        SumRecords = sumRecords;
    }

    public String getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(String returnCode) {
        ReturnCode = returnCode;
    }

    public String getReturnMsg() {
        return ReturnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        ReturnMsg = returnMsg;
    }

    public ReturnData getReturnData() {
        return ReturnData;
    }

    public void setReturnData(ReturnData returnData) {
        ReturnData = returnData;
    }

    public String getReturnTotalRecords() {
        return ReturnTotalRecords;
    }

    public void setReturnTotalRecords(String returnTotalRecords) {
        ReturnTotalRecords = returnTotalRecords;
    }
}