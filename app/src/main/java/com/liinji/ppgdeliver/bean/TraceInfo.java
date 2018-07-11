package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class TraceInfo {

    /**
     * WaybillId : a1d2a70c-2ec6-4432-9945-07d86407fa59
     * WaybillNo : 0100001008800325
     * Shipper : 池子
     * DeliveryTel : 13621671346
     * DeliveryAddress : 北京市北京市朝阳区
     * Receiver : 六六
     * ReceiveTel : 15558403294
     * ReceiveAddress : 山东省济南市章丘市
     * CargoName : 鸡胗
     * TotalNumber : 3
     * PackageWay : 箱子
     * PutAmount : 23.00
     * CashAmount : 0.00
     * MonthAmount : 0.00
     * CollectAmount : 3800.00
     * AdvanceAmount : 2.00
     * PremiumAmount : 0.00
     * PayMode : 提付
     * TotalAmount : 23.00
     * Remark :
     * DepartureBranchCode : 001
     * DepartureBranchName : 总部（乌）
     * DestinationBranchCode : 031
     * DestinationBranchName : 喀什
     * TransitBranchCode :
     * TransitBranchName :
     * WaybillStatus : 3
     * WaybillStatusDescribe : 到达目的地
     * UserId : 15
     */

    private WaybillInfoBean WaybillInfo;
    /**
     * Description : 派件员 赵杨杰  正在为您派件
     * CreateTime : 2017-03-30 16:32:12
     */

    private List<ListTrackingBean> ListTracking;

    public WaybillInfoBean getWaybillInfo() {
        return WaybillInfo;
    }

    public void setWaybillInfo(WaybillInfoBean WaybillInfo) {
        this.WaybillInfo = WaybillInfo;
    }

    public List<ListTrackingBean> getListTracking() {
        return ListTracking;
    }

    public void setListTracking(List<ListTrackingBean> ListTracking) {
        this.ListTracking = ListTracking;
    }

    public static class WaybillInfoBean {
        private String WaybillId;
        private String WaybillNo;
        private String Shipper;
        private String DeliveryTel;
        private String DeliveryAddress;
        private String Receiver;
        private String ReceiveTel;
        private String ReceiveAddress;
        private String CargoName;
        private int TotalNumber;
        private String PackageWay;
        private String PutAmount;
        private String CashAmount;
        private String MonthAmount;
        private String CollectAmount;
        private String AdvanceAmount;
        private String PremiumAmount;
        private String PayMode;
        private String TotalAmount;
        private String Remark;
        private String DepartureBranchCode;
        private String DepartureBranchName;
        private String DestinationBranchCode;
        private String DestinationBranchName;
        private String TransitBranchCode;
        private String TransitBranchName;
        private int WaybillStatus;
        private String WaybillStatusDescribe;
        private int UserId;

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

        public String getShipper() {
            return Shipper;
        }

        public void setShipper(String Shipper) {
            this.Shipper = Shipper;
        }

        public String getDeliveryTel() {
            return DeliveryTel;
        }

        public void setDeliveryTel(String DeliveryTel) {
            this.DeliveryTel = DeliveryTel;
        }

        public String getDeliveryAddress() {
            return DeliveryAddress;
        }

        public void setDeliveryAddress(String DeliveryAddress) {
            this.DeliveryAddress = DeliveryAddress;
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

        public String getReceiveAddress() {
            return ReceiveAddress;
        }

        public void setReceiveAddress(String ReceiveAddress) {
            this.ReceiveAddress = ReceiveAddress;
        }

        public String getCargoName() {
            return CargoName;
        }

        public void setCargoName(String CargoName) {
            this.CargoName = CargoName;
        }

        public int getTotalNumber() {
            return TotalNumber;
        }

        public void setTotalNumber(int TotalNumber) {
            this.TotalNumber = TotalNumber;
        }

        public String getPackageWay() {
            return PackageWay;
        }

        public void setPackageWay(String PackageWay) {
            this.PackageWay = PackageWay;
        }

        public String getPutAmount() {
            return PutAmount;
        }

        public void setPutAmount(String PutAmount) {
            this.PutAmount = PutAmount;
        }

        public String getCashAmount() {
            return CashAmount;
        }

        public void setCashAmount(String CashAmount) {
            this.CashAmount = CashAmount;
        }

        public String getMonthAmount() {
            return MonthAmount;
        }

        public void setMonthAmount(String MonthAmount) {
            this.MonthAmount = MonthAmount;
        }

        public String getCollectAmount() {
            return CollectAmount;
        }

        public void setCollectAmount(String CollectAmount) {
            this.CollectAmount = CollectAmount;
        }

        public String getAdvanceAmount() {
            return AdvanceAmount;
        }

        public void setAdvanceAmount(String AdvanceAmount) {
            this.AdvanceAmount = AdvanceAmount;
        }

        public String getPremiumAmount() {
            return PremiumAmount;
        }

        public void setPremiumAmount(String PremiumAmount) {
            this.PremiumAmount = PremiumAmount;
        }

        public String getPayMode() {
            return PayMode;
        }

        public void setPayMode(String PayMode) {
            this.PayMode = PayMode;
        }

        public String getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(String TotalAmount) {
            this.TotalAmount = TotalAmount;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getDepartureBranchCode() {
            return DepartureBranchCode;
        }

        public void setDepartureBranchCode(String DepartureBranchCode) {
            this.DepartureBranchCode = DepartureBranchCode;
        }

        public String getDepartureBranchName() {
            return DepartureBranchName;
        }

        public void setDepartureBranchName(String DepartureBranchName) {
            this.DepartureBranchName = DepartureBranchName;
        }

        public String getDestinationBranchCode() {
            return DestinationBranchCode;
        }

        public void setDestinationBranchCode(String DestinationBranchCode) {
            this.DestinationBranchCode = DestinationBranchCode;
        }

        public String getDestinationBranchName() {
            return DestinationBranchName;
        }

        public void setDestinationBranchName(String DestinationBranchName) {
            this.DestinationBranchName = DestinationBranchName;
        }

        public String getTransitBranchCode() {
            return TransitBranchCode;
        }

        public void setTransitBranchCode(String TransitBranchCode) {
            this.TransitBranchCode = TransitBranchCode;
        }

        public String getTransitBranchName() {
            return TransitBranchName;
        }

        public void setTransitBranchName(String TransitBranchName) {
            this.TransitBranchName = TransitBranchName;
        }

        public int getWaybillStatus() {
            return WaybillStatus;
        }

        public void setWaybillStatus(int WaybillStatus) {
            this.WaybillStatus = WaybillStatus;
        }

        public String getWaybillStatusDescribe() {
            return WaybillStatusDescribe;
        }

        public void setWaybillStatusDescribe(String WaybillStatusDescribe) {
            this.WaybillStatusDescribe = WaybillStatusDescribe;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }
    }

    public static class ListTrackingBean {
        private String Description;
        private String CreateTime;

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
