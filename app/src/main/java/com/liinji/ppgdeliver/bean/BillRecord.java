package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/25.
 */
public class BillRecord {

    /**
     * ID : 26
     * Status : 1
     * VerifyDate : 0001/1/1 0:00:00
     * VerifyID : 0
     * VerifyName :
     * ApplyAmount : 4235
     * ApplyDate : 2017-03-28 17:27:31
     * BranchCode : 029
     * CourierID : 35
     * CourierName : 朱志强
     */

    private int ID;
    private String Status;
    private String VerifyDate;
    private String VerifyID;
    private String VerifyName;
    private float ApplyAmount;
    private String ApplyDate;
    private String BranchCode;
    private int CourierID;
    private String CourierName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getVerifyDate() {
        return VerifyDate;
    }

    public void setVerifyDate(String VerifyDate) {
        this.VerifyDate = VerifyDate;
    }

    public String getVerifyID() {
        return VerifyID;
    }

    public void setVerifyID(String VerifyID) {
        this.VerifyID = VerifyID;
    }

    public String getVerifyName() {
        return VerifyName;
    }

    public void setVerifyName(String VerifyName) {
        this.VerifyName = VerifyName;
    }

    public float getApplyAmount() {
        return ApplyAmount;
    }

    public void setApplyAmount(float ApplyAmount) {
        this.ApplyAmount = ApplyAmount;
    }

    public String getApplyDate() {
        return ApplyDate;
    }

    public void setApplyDate(String ApplyDate) {
        this.ApplyDate = ApplyDate;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

    public int getCourierID() {
        return CourierID;
    }

    public void setCourierID(int CourierID) {
        this.CourierID = CourierID;
    }

    public String getCourierName() {
        return CourierName;
    }

    public void setCourierName(String CourierName) {
        this.CourierName = CourierName;
    }
}
