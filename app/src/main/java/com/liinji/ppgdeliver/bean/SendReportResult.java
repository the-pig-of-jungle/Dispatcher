package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by 朱志强 on 2017/10/25.
 */

public class SendReportResult {

    /**
     * ListWaybill : [{"WaybillNo":"02200124700215","Number":5,"Shipper":"杨梅","Receiver":"现实","SettleWay":"提付","FreightAmout":20,"CollectAmount":10,"ConsignDate":"2017-09-05 17:26:22","SignDesc":"是"},{"WaybillNo":"02200124700239","Number":2,"Shipper":"杨梅","Receiver":"实哥","SettleWay":"提付","FreightAmout":10,"CollectAmount":20,"ConsignDate":"2017-09-05 17:26:43","SignDesc":"是"},{"WaybillNo":"02200124700246","Number":2,"Shipper":"杨梅","Receiver":"实哥","SettleWay":"提付","FreightAmout":10,"CollectAmount":10,"ConsignDate":"2017-09-05 17:26:51","SignDesc":"是"},{"WaybillNo":"02200125700221","Number":2,"Shipper":"大碗～贾玲","Receiver":"小红","SettleWay":"提付","FreightAmout":10,"CollectAmount":1,"ConsignDate":"2017-09-15 16:28:37","SignDesc":"是"},{"WaybillNo":"02200125700238","Number":1,"Shipper":"秋水","Receiver":"小红","SettleWay":"提付","FreightAmout":10,"CollectAmount":10,"ConsignDate":"2017-09-15 16:28:47","SignDesc":"是"},{"WaybillNo":"02200125700245","Number":1,"Shipper":"秋水","Receiver":"周末","SettleWay":"提付","FreightAmout":10,"CollectAmount":10,"ConsignDate":"2017-09-15 16:28:56","SignDesc":"是"},{"WaybillNo":"02200125700252","Number":1,"Shipper":"联积---杨梅","Receiver":"周末","SettleWay":"提付","FreightAmout":10,"CollectAmount":20,"ConsignDate":"2017-09-15 16:29:05","SignDesc":"是"},{"WaybillNo":"02200125700269","Number":1,"Shipper":"联积汽配-A先生","Receiver":"周末","SettleWay":"提付","FreightAmout":10,"CollectAmount":20,"ConsignDate":"2017-09-15 16:29:18","SignDesc":"是"},{"WaybillNo":"02200125700276","Number":1,"Shipper":"联积汽配-A先生","Receiver":"周末","SettleWay":"提付","FreightAmout":10,"CollectAmount":10,"ConsignDate":"2017-09-15 16:29:26","SignDesc":"是"},{"WaybillNo":"02200125700283","Number":1,"Shipper":"李明","Receiver":"周末","SettleWay":"提付","FreightAmout":1,"CollectAmount":0,"ConsignDate":"2017-09-15 16:29:34","SignDesc":"是"}]
     * TotalCount : 228
     * TotalNumber : 426
     */

    private int TotalCount;
    private int TotalNumber;
    private List<ListWaybillBean> ListWaybill;

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public int getTotalNumber() {
        return TotalNumber;
    }

    public void setTotalNumber(int TotalNumber) {
        this.TotalNumber = TotalNumber;
    }

    public List<ListWaybillBean> getListWaybill() {
        return ListWaybill;
    }

    public void setListWaybill(List<ListWaybillBean> ListWaybill) {
        this.ListWaybill = ListWaybill;
    }

    public static class ListWaybillBean {
        /**
         * WaybillNo : 02200124700215
         * Number : 5
         * Shipper : 杨梅
         * Receiver : 现实
         * SettleWay : 提付
         * FreightAmout : 20
         * CollectAmount : 10
         * ConsignDate : 2017-09-05 17:26:22
         * SignDesc : 是
         */

        private String WaybillNo;
        private int Number;
        private String Shipper;
        private String Receiver;
        private String SettleWay;
        private float FreightAmout;
        private float CollectAmount;
        private String ConsignDate;
        private String SignDesc;

        public String getWaybillNo() {
            return WaybillNo;
        }

        public void setWaybillNo(String WaybillNo) {
            this.WaybillNo = WaybillNo;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public String getShipper() {
            return Shipper;
        }

        public void setShipper(String Shipper) {
            this.Shipper = Shipper;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String Receiver) {
            this.Receiver = Receiver;
        }

        public String getSettleWay() {
            return SettleWay;
        }

        public void setSettleWay(String SettleWay) {
            this.SettleWay = SettleWay;
        }

        public float getFreightAmout() {
            return FreightAmout;
        }

        public void setFreightAmout(float FreightAmout) {
            this.FreightAmout = FreightAmout;
        }

        public float getCollectAmount() {
            return CollectAmount;
        }

        public void setCollectAmount(float CollectAmount) {
            this.CollectAmount = CollectAmount;
        }

        public String getConsignDate() {
            return ConsignDate;
        }

        public void setConsignDate(String ConsignDate) {
            this.ConsignDate = ConsignDate;
        }

        public String getSignDesc() {
            return SignDesc;
        }

        public void setSignDesc(String SignDesc) {
            this.SignDesc = SignDesc;
        }
    }
}
