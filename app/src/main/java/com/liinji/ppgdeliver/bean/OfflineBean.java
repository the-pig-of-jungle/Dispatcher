package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by pig on 2017/11/29.
 */

public class OfflineBean {

    /**
     * ReturnData : [{"WaybillId":"88a2ab8c-e56a-4dc9-8d3a-64303a597b5c","WaybillNo":"02200125500210","CargoName":"bbb","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","Receiver":"周末","ReceiveTel":"18255022058","SignDate":"2017-09-14 17:05:08","FreightAmount":10,"FreightStatus":0,"CollectPayStatus":0,"CollectAmount":20,"Number":1,"OweDay":89,"PaperNumber":"","ConsignDate":"2017-09-13 15:19:26","WaybillCargoNo":"","PickUpWay":"自提"}]
     * TotalMessage : {"TotalOver5":5,"TotalOver15":3,"TotalOver30":30,"TotalRec":"38"}
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
         * TotalOver5 : 5
         * TotalOver15 : 3
         * TotalOver30 : 30
         * TotalRec : 38
         */

        private int TotalOver5;
        private int TotalOver15;
        private int TotalOver30;
        private String TotalRec;

        public int getTotalOver5() {
            return TotalOver5;
        }

        public void setTotalOver5(int TotalOver5) {
            this.TotalOver5 = TotalOver5;
        }

        public int getTotalOver15() {
            return TotalOver15;
        }

        public void setTotalOver15(int TotalOver15) {
            this.TotalOver15 = TotalOver15;
        }

        public int getTotalOver30() {
            return TotalOver30;
        }

        public void setTotalOver30(int TotalOver30) {
            this.TotalOver30 = TotalOver30;
        }

        public String getTotalRec() {
            return TotalRec;
        }

        public void setTotalRec(String TotalRec) {
            this.TotalRec = TotalRec;
        }
    }

    public static class ReturnDataBean {
        /**
         * WaybillId : 88a2ab8c-e56a-4dc9-8d3a-64303a597b5c
         * WaybillNo : 02200125500210
         * CargoName : bbb
         * ReceiveAddress : 浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号
         * Receiver : 周末
         * ReceiveTel : 18255022058
         * SignDate : 2017-09-14 17:05:08
         * FreightAmount : 10
         * FreightStatus : 0
         * CollectPayStatus : 0
         * CollectAmount : 20
         * Number : 1
         * OweDay : 89
         * PaperNumber :
         * ConsignDate : 2017-09-13 15:19:26
         * WaybillCargoNo :
         * PickUpWay : 自提
         */

        private String WaybillId;
        private String WaybillNo;
        private String CargoName;
        private String ReceiveAddress;
        private String Receiver;
        private String ReceiveTel;
        private String SignDate;
        private int FreightAmount;
        private int FreightStatus;
        private int CollectPayStatus;
        private int CollectAmount;
        private int Number;
        private int OweDay;
        private String PaperNumber;
        private String ConsignDate;
        private String WaybillCargoNo;
        private String PickUpWay;

        private String SendBranchName;

        public String getSendBranchName() {
            return SendBranchName;
        }

        public void setSendBranchName(String sendBranchName) {
            SendBranchName = sendBranchName;
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

        public String getCargoName() {
            return CargoName;
        }

        public void setCargoName(String CargoName) {
            this.CargoName = CargoName;
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

        public String getSignDate() {
            return SignDate;
        }

        public void setSignDate(String SignDate) {
            this.SignDate = SignDate;
        }

        public int getFreightAmount() {
            return FreightAmount;
        }

        public void setFreightAmount(int FreightAmount) {
            this.FreightAmount = FreightAmount;
        }

        public int getFreightStatus() {
            return FreightStatus;
        }

        public void setFreightStatus(int FreightStatus) {
            this.FreightStatus = FreightStatus;
        }

        public int getCollectPayStatus() {
            return CollectPayStatus;
        }

        public void setCollectPayStatus(int CollectPayStatus) {
            this.CollectPayStatus = CollectPayStatus;
        }

        public int getCollectAmount() {
            return CollectAmount;
        }

        public void setCollectAmount(int CollectAmount) {
            this.CollectAmount = CollectAmount;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public int getOweDay() {
            return OweDay;
        }

        public void setOweDay(int OweDay) {
            this.OweDay = OweDay;
        }

        public String getPaperNumber() {
            return PaperNumber;
        }

        public void setPaperNumber(String PaperNumber) {
            this.PaperNumber = PaperNumber;
        }

        public String getConsignDate() {
            return ConsignDate;
        }

        public void setConsignDate(String ConsignDate) {
            this.ConsignDate = ConsignDate;
        }

        public String getWaybillCargoNo() {
            return WaybillCargoNo;
        }

        public void setWaybillCargoNo(String WaybillCargoNo) {
            this.WaybillCargoNo = WaybillCargoNo;
        }

        public String getPickUpWay() {
            return PickUpWay;
        }

        public void setPickUpWay(String PickUpWay) {
            this.PickUpWay = PickUpWay;
        }
    }
}
