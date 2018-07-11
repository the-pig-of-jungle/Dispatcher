package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by 喜欢、陪你看风景 on 2017/12/6.
 */

public class PrintOrders {

    /**
     * ReturnData : [{"ArticleName":"","CollectionTradeCharges":0,"ConsignDate":"2017-10-13 09:48:39","DeliveryTel":"13366669999","Shipper":"萌萌","DestinationBranchName":"合肥","ReceiveAddress":"浙江省杭州市桐庐县前程汽配城E-1020号","Receiver":"杨梅","ReceiveTel":"15612345689","TotalPieces":2,"WaybillNo":"02200728500013","PickUpPayAmount":3,"AdvanceTransitFee":0,"TripNo":"CT00217101300004","SendBranchName":"淮北","ClerkName":"陈晓","PaperNumber":"523456789","Remark":"","ServiceTel":"13645645623","CompanyShortName":"联积物流","SendBranchContactTel":"15612301230","DispatchBranchAddress":"浙江省杭州市西湖区宝马汽配城D区4020号","DispatchBranchContactTel":"18822233344","WaybillCargoNo":"","SettleWay":"提付"}]
     * TotalMessage : {"SumRecords":"1"}
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
         * SumRecords : 1
         */

        private String SumRecords;

        public String getSumRecords() {
            return SumRecords;
        }

        public void setSumRecords(String SumRecords) {
            this.SumRecords = SumRecords;
        }
    }

    public static class ReturnDataBean {
        /**
         * ArticleName :
         * CollectionTradeCharges : 0
         * ConsignDate : 2017-10-13 09:48:39
         * DeliveryTel : 13366669999
         * Shipper : 萌萌
         * DestinationBranchName : 合肥
         * ReceiveAddress : 浙江省杭州市桐庐县前程汽配城E-1020号
         * Receiver : 杨梅
         * ReceiveTel : 15612345689
         * TotalPieces : 2
         * WaybillNo : 02200728500013
         * PickUpPayAmount : 3
         * AdvanceTransitFee : 0
         * TripNo : CT00217101300004
         * SendBranchName : 淮北
         * ClerkName : 陈晓
         * PaperNumber : 523456789
         * Remark :
         * ServiceTel : 13645645623
         * CompanyShortName : 联积物流
         * SendBranchContactTel : 15612301230
         * DispatchBranchAddress : 浙江省杭州市西湖区宝马汽配城D区4020号
         * DispatchBranchContactTel : 18822233344
         * WaybillCargoNo :
         * SettleWay : 提付
         *
         */

        private String ArticleName;
        private float CollectionTradeCharges;
        private String ConsignDate;
        private String DeliveryTel;
        private String Shipper;
        private String DestinationBranchName;
        private String ReceiveAddress;
        private String Receiver;
        private String ReceiveTel;
        private int TotalPieces;
        private String WaybillNo;
        private float PickUpPayAmount;
        private float AdvanceTransitFee;
        private String TripNo;
        private String SendBranchName;
        private String ClerkName;
        private String PaperNumber;
        private String Remark;
        private String ServiceTel;
        private String CompanyShortName;
        private String SendBranchContactTel;
        private String DispatchBranchAddress;
        private String DispatchBranchContactTel;
        private String WaybillCargoNo;
        private String SettleWay;
        private boolean IsChecked;
        private String PickUpWay;

        private String CompanyUrl;
        private String AdWords;

        private float CashPayAmount;

        private float MonthPayAmount;

        private float ReceiptPayAmount;

        private float DeliveryFee;

        private String DispatchBranchName;

        public String getDispatchBranchName() {
            return DispatchBranchName;
        }

        public void setDispatchBranchName(String dispatchBranchName) {
            DispatchBranchName = dispatchBranchName;
        }

        public float getDeliveryFee() {
            return DeliveryFee;
        }

        public void setDeliveryFee(float deliveryFee) {
            DeliveryFee = deliveryFee;
        }

        public float getCashPayAmount() {
            return CashPayAmount;
        }

        public void setCashPayAmount(float cashPayAmount) {
            CashPayAmount = cashPayAmount;
        }

        public float getMonthPayAmount() {
            return MonthPayAmount;
        }

        public void setMonthPayAmount(float monthPayAmount) {
            MonthPayAmount = monthPayAmount;
        }

        public float getReceiptPayAmount() {
            return ReceiptPayAmount;
        }

        public void setReceiptPayAmount(float receiptPayAmount) {
            ReceiptPayAmount = receiptPayAmount;
        }

        public String getCompanyUrl() {
            return CompanyUrl;
        }

        public void setCompanyUrl(String companyUrl) {
            CompanyUrl = companyUrl;
        }

        public String getAdWords() {
            return AdWords;
        }

        public void setAdWords(String adWords) {
            AdWords = adWords;
        }

        public String getPickUpWay() {
            return PickUpWay;
        }

        public void setPickUpWay(String pickUpWay) {
            PickUpWay = pickUpWay;
        }

        private static boolean sDefaultChecked;


        public ReturnDataBean() {
            IsChecked = sDefaultChecked;
        }

        public static void setDefaultChecked(boolean defaultChecked) {
            sDefaultChecked = defaultChecked;
        }

        public boolean isChecked() {
            return IsChecked;
        }

        public void setChecked(boolean checked) {
            IsChecked = checked;
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

        public String getDeliveryTel() {
            return DeliveryTel;
        }

        public void setDeliveryTel(String DeliveryTel) {
            this.DeliveryTel = DeliveryTel;
        }

        public String getShipper() {
            return Shipper;
        }

        public void setShipper(String Shipper) {
            this.Shipper = Shipper;
        }

        public String getDestinationBranchName() {
            return DestinationBranchName;
        }

        public void setDestinationBranchName(String DestinationBranchName) {
            this.DestinationBranchName = DestinationBranchName;
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

        public float getAdvanceTransitFee() {
            return AdvanceTransitFee;
        }

        public void setAdvanceTransitFee(float AdvanceTransitFee) {
            this.AdvanceTransitFee = AdvanceTransitFee;
        }

        public String getTripNo() {
            return TripNo;
        }

        public void setTripNo(String TripNo) {
            this.TripNo = TripNo;
        }

        public String getSendBranchName() {
            return SendBranchName;
        }

        public void setSendBranchName(String SendBranchName) {
            this.SendBranchName = SendBranchName;
        }

        public String getClerkName() {
            return ClerkName;
        }

        public void setClerkName(String ClerkName) {
            this.ClerkName = ClerkName;
        }

        public String getPaperNumber() {
            return PaperNumber;
        }

        public void setPaperNumber(String PaperNumber) {
            this.PaperNumber = PaperNumber;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getServiceTel() {
            return ServiceTel;
        }

        public void setServiceTel(String ServiceTel) {
            this.ServiceTel = ServiceTel;
        }

        public String getCompanyShortName() {
            return CompanyShortName;
        }

        public void setCompanyShortName(String CompanyShortName) {
            this.CompanyShortName = CompanyShortName;
        }

        public String getSendBranchContactTel() {
            return SendBranchContactTel;
        }

        public void setSendBranchContactTel(String SendBranchContactTel) {
            this.SendBranchContactTel = SendBranchContactTel;
        }

        public String getDispatchBranchAddress() {
            return DispatchBranchAddress;
        }

        public void setDispatchBranchAddress(String DispatchBranchAddress) {
            this.DispatchBranchAddress = DispatchBranchAddress;
        }

        public String getDispatchBranchContactTel() {
            return DispatchBranchContactTel;
        }

        public void setDispatchBranchContactTel(String DispatchBranchContactTel) {
            this.DispatchBranchContactTel = DispatchBranchContactTel;
        }

        public String getWaybillCargoNo() {
            return WaybillCargoNo;
        }

        public void setWaybillCargoNo(String WaybillCargoNo) {
            this.WaybillCargoNo = WaybillCargoNo;
        }

        public String getSettleWay() {
            return SettleWay;
        }

        public void setSettleWay(String SettleWay) {
            this.SettleWay = SettleWay;
        }
    }
}
