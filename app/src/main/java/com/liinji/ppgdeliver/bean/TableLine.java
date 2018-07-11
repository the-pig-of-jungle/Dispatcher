package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/7/17.
 */

public class TableLine {

    /**
     * ConsignDate : 2017/6/30 14:30:04
     * BranchCode : R0040
     * BranchName : 宁波
     * CompanyCode : 001
     * CompanyName : 千一物流
     * IsSendAndLoad : 1
     * WaybillNo : 010051800092
     * PickUpPayAmountPaid : 15
     * PickUpPayAmount : 15
     * CollectionTradeCharges : 10
     * CollectionTradeChargesPaid : 10
     * PayType : 现金支付
     * CollectionTradeChargesStatus : 无欠款
     */

    private String ConsignDate;
    private String BranchCode;
    private String BranchName;
    private String CompanyCode;
    private String CompanyName;
    private int IsSendAndLoad;
    private String WaybillNo;
    private int PickUpPayAmountPaid;
    private int PickUpPayAmount;
    private int CollectionTradeCharges;
    private int CollectionTradeChargesPaid;
    private String PayType;
    private String CollectionTradeChargesStatus;

    public String getConsignDate() {
        return ConsignDate;
    }

    public void setConsignDate(String ConsignDate) {
        this.ConsignDate = ConsignDate;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String CompanyCode) {
        this.CompanyCode = CompanyCode;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public int getIsSendAndLoad() {
        return IsSendAndLoad;
    }

    public void setIsSendAndLoad(int IsSendAndLoad) {
        this.IsSendAndLoad = IsSendAndLoad;
    }

    public String getWaybillNo() {
        return WaybillNo;
    }

    public void setWaybillNo(String WaybillNo) {
        this.WaybillNo = WaybillNo;
    }

    public int getPickUpPayAmountPaid() {
        return PickUpPayAmountPaid;
    }

    public void setPickUpPayAmountPaid(int PickUpPayAmountPaid) {
        this.PickUpPayAmountPaid = PickUpPayAmountPaid;
    }

    public int getPickUpPayAmount() {
        return PickUpPayAmount;
    }

    public void setPickUpPayAmount(int PickUpPayAmount) {
        this.PickUpPayAmount = PickUpPayAmount;
    }

    public int getCollectionTradeCharges() {
        return CollectionTradeCharges;
    }

    public void setCollectionTradeCharges(int CollectionTradeCharges) {
        this.CollectionTradeCharges = CollectionTradeCharges;
    }

    public int getCollectionTradeChargesPaid() {
        return CollectionTradeChargesPaid;
    }

    public void setCollectionTradeChargesPaid(int CollectionTradeChargesPaid) {
        this.CollectionTradeChargesPaid = CollectionTradeChargesPaid;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String PayType) {
        this.PayType = PayType;
    }

    public String getCollectionTradeChargesStatus() {
        return CollectionTradeChargesStatus;
    }

    public void setCollectionTradeChargesStatus(String CollectionTradeChargesStatus) {
        this.CollectionTradeChargesStatus = CollectionTradeChargesStatus;
    }
}
