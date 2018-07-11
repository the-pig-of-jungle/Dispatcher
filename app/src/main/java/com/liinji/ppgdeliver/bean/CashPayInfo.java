package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/24.
 */
public class CashPayInfo {

    /**
     * WaybillId : 6f45b675-4c9d-4e21-97fb-793d0c6b7ac4
     * WaybillNo : 0100001006800044
     * Shipper : 李小东
     * DeliveryTel : 15352672190
     * DeliveryAddress : 江苏省南京市玄武区
     * Receiver : 杨洋
     * ReceiveTel : 13621671300
     * ReceiveAddress : 江苏省苏州市虎丘区
     * CargoName : sss
     * TotalNumber : 44
     * PackageWay : 盒子
     * PutAmount : 0.00
     * CashAmount : 15.00
     * MonthAmount : 0.00
     * CollectAmount : 0.00
     * AdvanceAmount : 0.00
     * PremiumAmount : 0.00
     * PayMode : 现付
     * TotalAmount : 15.00
     * Remark :
     * DepartureBranchCode : 001
     * DepartureBranchName : 总部（乌）
     * DestinationBranchCode : 029
     * DestinationBranchName : 新东方（乌）
     * TransitBranchCode :
     * TransitBranchName :
     * CollectCargoFee : 0
     * PickUpFee : 0
     */

    private WaybillInfoBean WaybillInfo;

    public WaybillInfoBean getWaybillInfo() {
        return WaybillInfo;
    }

    public void setWaybillInfo(WaybillInfoBean WaybillInfo) {
        this.WaybillInfo = WaybillInfo;
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
        private String CollectCargoFee;
        private int PickUpFee;

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

        public String getCollectCargoFee() {
            return CollectCargoFee;
        }

        public void setCollectCargoFee(String CollectCargoFee) {
            this.CollectCargoFee = CollectCargoFee;
        }

        public int getPickUpFee() {
            return PickUpFee;
        }

        public void setPickUpFee(int PickUpFee) {
            this.PickUpFee = PickUpFee;
        }
    }
}
