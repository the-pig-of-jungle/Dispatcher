package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by 朱志强 on 2017/7/18.
 */

public class TableInfo {

    private List<ReturnData1Bean> returnData1;
    private List<ReturnData2Bean> returnData2;

    public List<ReturnData1Bean> getReturnData1() {
        return returnData1;
    }

    public void setReturnData1(List<ReturnData1Bean> returnData1) {
        this.returnData1 = returnData1;
    }

    public List<ReturnData2Bean> getReturnData2() {
        return returnData2;
    }

    public void setReturnData2(List<ReturnData2Bean> returnData2) {
        this.returnData2 = returnData2;
    }

    public static class ReturnData1Bean {
        /**
         * ConsignDate : 2017/6/30 0:00:00
         * BranchCode : R0040
         * BranchName : 宁波
         * CompanyCode : 001
         * CompanyName : 千一物流
         * IsSendAndLoad : 0
         * WaybillNo : 010051800108
         * PickUpPayAmountPaid : 0
         * PickUpPayAmount : 0
         * CollectionTradeCharges : 0
         * CollectionTradeChargesPaid : 0
         * PayType :
         * CollectionTradeChargesStatus : 无货款
         *
         */

        private String ConsignDate;
        private String BranchCode;
        private String BranchName;
        private String CompanyCode;
        private String CompanyName;
        private int IsSendAndLoad;
        private String WaybillNo;
        private float PickUpPayAmountPaid;
        private float PickUpPayAmount;
        private float CollectionTradeCharges;
        private String WaybillId;
        private float CollectionTradeChargesPaid;
        private String PayType;
        private String CollectionTradeChargesStatus;
        private int TotalPieces;

        private String Shipper;
        private String Receiver;

        private String PickUpPayTime;
        private String CollectionTradeChargesPayTime;

        private String Signer;


        public String getSigner() {
            return Signer;
        }

        public void setSigner(String signer) {
            Signer = signer;
        }

        public String getPickUpPayTime() {
            return PickUpPayTime;
        }

        public void setPickUpPayTime(String pickUpPayTime) {
            PickUpPayTime = pickUpPayTime;
        }

        public String getCollectionTradeChargesPayTime() {
            return CollectionTradeChargesPayTime;
        }

        public void setCollectionTradeChargesPayTime(String collectionTradeChargesPayTime) {
            CollectionTradeChargesPayTime = collectionTradeChargesPayTime;
        }

        public String getShipper() {
            return Shipper;
        }

        public void setShipper(String shipper) {
            Shipper = shipper;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String receiver) {
            Receiver = receiver;
        }

        public int getTotalPieces() {
            return TotalPieces;
        }

        public void setTotalPieces(int totalPieces) {
            TotalPieces = totalPieces;
        }

        public String getConsignDate() {
            return ConsignDate;
        }

        public void setConsignDate(String ConsignDate) {
            this.ConsignDate = ConsignDate;
        }

        public String getWaybillId() {
            return WaybillId;
        }

        public void setWaybillId(String waybillId) {
            WaybillId = waybillId;
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

        public float getPickUpPayAmountPaid() {
            return PickUpPayAmountPaid;
        }

        public void setPickUpPayAmountPaid(float PickUpPayAmountPaid) {
            this.PickUpPayAmountPaid = PickUpPayAmountPaid;
        }

        public float getPickUpPayAmount() {
            return PickUpPayAmount;
        }

        public void setPickUpPayAmount(float PickUpPayAmount) {
            this.PickUpPayAmount = PickUpPayAmount;
        }

        public float getCollectionTradeCharges() {
            return CollectionTradeCharges;
        }

        public void setCollectionTradeCharges(float CollectionTradeCharges) {
            this.CollectionTradeCharges = CollectionTradeCharges;
        }

        public float getCollectionTradeChargesPaid() {
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

    public static class ReturnData2Bean {
        /**
         * WaybillCount : 47
         * PickUpPayAmountTotal : 317.00
         * CollectionTradeChargesPaidTotal : 2203.00
         * PickUpPayAmountPaidTotal : 181.00
         * CollectionTradeChargesTotal : 5827.00
         */

        private String WaybillCount;
        private String PickUpPayAmountTotal;
        private String CollectionTradeChargesPaidTotal;
        private String PickUpPayAmountPaidTotal;
        private String CollectionTradeChargesTotal;

        public String getWaybillCount() {
            return WaybillCount;
        }

        public void setWaybillCount(String WaybillCount) {
            this.WaybillCount = WaybillCount;
        }

        public String getPickUpPayAmountTotal() {
            return PickUpPayAmountTotal;
        }

        public void setPickUpPayAmountTotal(String PickUpPayAmountTotal) {
            this.PickUpPayAmountTotal = PickUpPayAmountTotal;
        }

        public String getCollectionTradeChargesPaidTotal() {
            return CollectionTradeChargesPaidTotal;
        }

        public void setCollectionTradeChargesPaidTotal(String CollectionTradeChargesPaidTotal) {
            this.CollectionTradeChargesPaidTotal = CollectionTradeChargesPaidTotal;
        }

        public String getPickUpPayAmountPaidTotal() {
            return PickUpPayAmountPaidTotal;
        }

        public void setPickUpPayAmountPaidTotal(String PickUpPayAmountPaidTotal) {
            this.PickUpPayAmountPaidTotal = PickUpPayAmountPaidTotal;
        }

        public String getCollectionTradeChargesTotal() {
            return CollectionTradeChargesTotal;
        }

        public void setCollectionTradeChargesTotal(String CollectionTradeChargesTotal) {
            this.CollectionTradeChargesTotal = CollectionTradeChargesTotal;
        }
    }
}
