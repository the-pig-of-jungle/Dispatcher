package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by pig on 2017/12/5.
 */

public class CompleteOrder {

    /**
     * ReturnData : [{"ArticleName":"QQ糖","CollectionTradeCharges":0,"ConsignDate":"2017-09-08 15:01:05","Shipper":"绿泡泡","DeliveryUserTel":"18756157607","ReceiveAddress":"","Receiver":"红果果","ReceiveTel":"18756157607","TotalPieces":1,"WaybillNo":"02200125000482","PickUpPayAmount":8,"PaperNumber":"","IsSendAndLoad":0,"SignStatus":0,"IsReturn":0,"SignDate":"2017-12-05 16:51:23"},{"ArticleName":"","CollectionTradeCharges":0,"ConsignDate":"2017-09-08 15:02:12","Shipper":"伊丽莎白","DeliveryUserTel":"18756157607","ReceiveAddress":"","Receiver":"达西先生","ReceiveTel":"18255022063","TotalPieces":1,"WaybillNo":"02200125000499","PickUpPayAmount":5,"PaperNumber":"","IsSendAndLoad":0,"SignStatus":0,"IsReturn":0,"SignDate":"2017-12-05 16:51:19"}]
     * TotalMessage : {"SumPieces":"18","SumRecords":"10"}
     */

    private TotalMessageBean TotalMessage;
    private List<ReturnDataBean> ReturnData;

    public TotalMessageBean getTotalMessage() {
        return TotalMessage;
    }

    public void setTotalMessage(TotalMessageBean TotalMessage) {
        this.TotalMessage = TotalMessage;
    }

    public List<ReturnDataBean> getReturnData() {
        return ReturnData;
    }

    public void setReturnData(List<ReturnDataBean> ReturnData) {
        this.ReturnData = ReturnData;
    }

    public static class TotalMessageBean {
        /**
         * SumPieces : 18
         * SumRecords : 10
         */

        private String SumPieces;
        private String SumRecords;

        public String getSumPieces() {
            return SumPieces;
        }

        public void setSumPieces(String SumPieces) {
            this.SumPieces = SumPieces;
        }

        public String getSumRecords() {
            return SumRecords;
        }

        public void setSumRecords(String SumRecords) {
            this.SumRecords = SumRecords;
        }
    }

    public static class ReturnDataBean {
        /**
         * ArticleName : QQ糖
         * CollectionTradeCharges : 0
         * ConsignDate : 2017-09-08 15:01:05
         * Shipper : 绿泡泡
         * DeliveryUserTel : 18756157607
         * ReceiveAddress :
         * Receiver : 红果果
         * ReceiveTel : 18756157607
         * TotalPieces : 1
         * WaybillNo : 02200125000482
         * PickUpPayAmount : 8
         * PaperNumber :
         * IsSendAndLoad : 0
         * SignStatus : 0
         * IsReturn : 0
         * SignDate : 2017-12-05 16:51:23
         */

        private String ArticleName;
        private float CollectionTradeCharges;
        private String ConsignDate;
        private String Shipper;
        private String DeliveryUserTel;
        private String ReceiveAddress;
        private String Receiver;
        private String ReceiveTel;
        private int TotalPieces;
        private String WaybillNo;
        private float PickUpPayAmount;
        private String PaperNumber;
        private int IsSendAndLoad;
        private int SignStatus;
        private int IsReturn;
        private String SignDate;

        private String WaybillCargoNo;

        private String SendBranchName;

        public String getSendBranchName() {
            return SendBranchName;
        }

        public void setSendBranchName(String sendBranchName) {
            SendBranchName = sendBranchName;
        }

        public String getWaybillCargoNo() {
            return WaybillCargoNo;
        }

        public void setWaybillCargoNo(String waybillCargoNo) {
            WaybillCargoNo = waybillCargoNo;
        }

        public String getArticleName() {
            return ArticleName;
        }

        public void setArticleName(String ArticleName) {
            this.ArticleName = ArticleName;
        }

        public float getCollectionTradeCharges() {
            return CollectionTradeCharges;
        }

        public void setCollectionTradeCharges(float CollectionTradeCharges) {
            this.CollectionTradeCharges = CollectionTradeCharges;
        }

        public String getConsignDate() {
            return ConsignDate;
        }

        public void setConsignDate(String ConsignDate) {
            this.ConsignDate = ConsignDate;
        }

        public String getShipper() {
            return Shipper;
        }

        public void setShipper(String Shipper) {
            this.Shipper = Shipper;
        }

        public String getDeliveryUserTel() {
            return DeliveryUserTel;
        }

        public void setDeliveryUserTel(String DeliveryUserTel) {
            this.DeliveryUserTel = DeliveryUserTel;
        }

        public String getReceiveAddress() {
            return ReceiveAddress;
        }

        public void setReceiveAddress(String ReceiveAddress) {
            this.ReceiveAddress = ReceiveAddress;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String Receiver) {
            this.Receiver = Receiver;
        }

        public String getReceiveTel() {
            return ReceiveTel;
        }

        public void setReceiveTel(String ReceiveTel) {
            this.ReceiveTel = ReceiveTel;
        }

        public int getTotalPieces() {
            return TotalPieces;
        }

        public void setTotalPieces(int TotalPieces) {
            this.TotalPieces = TotalPieces;
        }

        public String getWaybillNo() {
            return WaybillNo;
        }

        public void setWaybillNo(String WaybillNo) {
            this.WaybillNo = WaybillNo;
        }

        public float getPickUpPayAmount() {
            return PickUpPayAmount;
        }

        public void setPickUpPayAmount(float PickUpPayAmount) {
            this.PickUpPayAmount = PickUpPayAmount;
        }

        public String getPaperNumber() {
            return PaperNumber;
        }

        public void setPaperNumber(String PaperNumber) {
            this.PaperNumber = PaperNumber;
        }

        public int getIsSendAndLoad() {
            return IsSendAndLoad;
        }

        public void setIsSendAndLoad(int IsSendAndLoad) {
            this.IsSendAndLoad = IsSendAndLoad;
        }

        public int getSignStatus() {
            return SignStatus;
        }

        public void setSignStatus(int SignStatus) {
            this.SignStatus = SignStatus;
        }

        public int getIsReturn() {
            return IsReturn;
        }

        public void setIsReturn(int IsReturn) {
            this.IsReturn = IsReturn;
        }

        public String getSignDate() {
            return SignDate;
        }

        public void setSignDate(String SignDate) {
            this.SignDate = SignDate;
        }
    }
}
