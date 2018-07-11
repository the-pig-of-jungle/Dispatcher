package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by pig on 2017/12/5.
 */

public class WaitedOrder {

    /**
     * ReturnData : [{"WaybillId":"93717ec3-601c-495b-85fe-c49f461f093c","WaybillNo":"02200131300217","ArticleName":"嘻嘻嘻","Shipper":"小A","DeliverTel":"02912345679","Receiver":"秋水","ReceiverTel":"18255022063","ReceiveAddress":"北京市北京市朝阳区","ConsignDate":"2017-11-10 14:45:16","PickUpPayAmount":20,"CollectionTradeCharges":2,"TotalPieces":1,"WaybillCargoNo":"","PaperNumber":"","IsSendAndLoad":1,"SignStatus":0},{"WaybillId":"9c357ae5-12b9-4661-8a1c-3c57770e549e","WaybillNo":"02200129900153","ArticleName":"","Shipper":"联积汽配","DeliverTel":"05584031715","Receiver":"杨梅","ReceiverTel":"15612345689","ReceiveAddress":"浙江省杭州市桐庐县前程汽配城E-1020号","ConsignDate":"2017-10-27 13:49:40","PickUpPayAmount":8,"CollectionTradeCharges":2,"TotalPieces":2,"WaybillCargoNo":"","PaperNumber":"","IsSendAndLoad":0,"SignStatus":0},{"WaybillId":"a63afc0a-2f85-4e3b-8e8c-c6047da07010","WaybillNo":"02200129900139","ArticleName":"","Shipper":"联积汽配","DeliverTel":"05584031715","Receiver":"刘实","ReceiverTel":"055689898986","ReceiveAddress":"上海上海市黄浦区上海外滩8888","ConsignDate":"2017-10-27 13:49:07","PickUpPayAmount":0,"CollectionTradeCharges":5,"TotalPieces":2,"WaybillCargoNo":"","PaperNumber":"","IsSendAndLoad":0,"SignStatus":0}]
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
         * WaybillId : 93717ec3-601c-495b-85fe-c49f461f093c
         * WaybillNo : 02200131300217
         * ArticleName : 嘻嘻嘻
         * Shipper : 小A
         * DeliverTel : 02912345679
         * Receiver : 秋水
         * ReceiverTel : 18255022063
         * ReceiveAddress : 北京市北京市朝阳区
         * ConsignDate : 2017-11-10 14:45:16
         * PickUpPayAmount : 20
         * CollectionTradeCharges : 2
         * TotalPieces : 1
         * WaybillCargoNo :
         * PaperNumber :
         * IsSendAndLoad : 1
         * SignStatus : 0
         */

        private String WaybillId;
        private String WaybillNo;
        private String ArticleName;
        private String Shipper;
        private String DeliverTel;
        private String Receiver;
        private String ReceiverTel;
        private String ReceiveAddress;
        private String ConsignDate;
        private float PickUpPayAmount;
        private float CollectionTradeCharges;
        private int TotalPieces;
        private String WaybillCargoNo;
        private String PaperNumber;
        private int IsSendAndLoad;
        private int SignStatus;
        private float TransferFee;
        private String TransferCompanyName;
        private String TransferNo;

        private float BasicFee;
        private float Rebate;
        private int RebateWay;
        private int IsToEnd;

        private int IsRegisteTransfer;

        private String SendBranchName;

        public String getSendBranchName() {
            return SendBranchName;
        }

        public void setSendBranchName(String sendBranchName) {
            SendBranchName = sendBranchName;
        }

        public float getTransferFee() {
            return TransferFee;
        }

        public void setTransferFee(float transferFee) {
            TransferFee = transferFee;
        }

        public String getTransferCompanyName() {
            return TransferCompanyName;
        }

        public void setTransferCompanyName(String transferCompanyName) {
            TransferCompanyName = transferCompanyName;
        }

        public String getTransferNo() {
            return TransferNo;
        }

        public void setTransferNo(String transferNo) {
            TransferNo = transferNo;
        }

        public int getIsToEnd() {
            return IsToEnd;
        }

        public void setIsToEnd(int isToEnd) {
            IsToEnd = isToEnd;
        }

        public int getIsRegisteTransfer() {
            return IsRegisteTransfer;
        }

        public void setIsRegisteTransfer(int isRegisteTransfer) {
            IsRegisteTransfer = isRegisteTransfer;
        }

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

        public String getArticleName() {
            return ArticleName;
        }

        public void setArticleName(String ArticleName) {
            this.ArticleName = ArticleName;
        }

        public String getShipper() {
            return Shipper;
        }

        public void setShipper(String Shipper) {
            this.Shipper = Shipper;
        }

        public String getDeliverTel() {
            return DeliverTel;
        }

        public void setDeliverTel(String DeliverTel) {
            this.DeliverTel = DeliverTel;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String Receiver) {
            this.Receiver = Receiver;
        }

        public String getReceiverTel() {
            return ReceiverTel;
        }

        public void setReceiverTel(String ReceiverTel) {
            this.ReceiverTel = ReceiverTel;
        }

        public String getReceiveAddress() {
            return ReceiveAddress;
        }

        public void setReceiveAddress(String ReceiveAddress) {
            this.ReceiveAddress = ReceiveAddress;
        }

        public String getConsignDate() {
            return ConsignDate;
        }

        public void setConsignDate(String ConsignDate) {
            this.ConsignDate = ConsignDate;
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

        public int getTotalPieces() {
            return TotalPieces;
        }

        public void setTotalPieces(int TotalPieces) {
            this.TotalPieces = TotalPieces;
        }

        public String getWaybillCargoNo() {
            return WaybillCargoNo;
        }

        public void setWaybillCargoNo(String WaybillCargoNo) {
            this.WaybillCargoNo = WaybillCargoNo;
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

        public float getBasicFee() {
            return BasicFee;
        }

        public void setBasicFee(float basicFee) {
            BasicFee = basicFee;
        }

        public float getRebate() {
            return Rebate;
        }

        public void setRebate(float rebate) {
            Rebate = rebate;
        }

        public int getRebateWay() {
            return RebateWay;
        }

        public void setRebateWay(int rebateWay) {
            RebateWay = rebateWay;
        }
    }
}
