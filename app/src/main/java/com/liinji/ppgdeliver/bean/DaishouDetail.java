package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/6/29.
 */

public class DaishouDetail {

    /**
     * WaybillId : c374ef05-0161-4dc0-8d21-a166ddd1f459
     * WaybillNo : 02200734100085
     * Shipper : 上海联积汽配有限公司联航路分部
     * DeliveryTel : 13621671346
     * DeliveryAddress : 浙江省杭州市桐庐县新阳路100号新阳汽配城1535号
     * Receiver : 上海大实汽配有限责任公司
     * ReceiveTel : 13612348908
     * ReceiveAddress : 内蒙古自治区呼和浩特市新城区人民路批发市场A区1235号
     * CargoName :
     * SettleWay : 提付
     * TotalNumber : 2
     * SignDate : 2017-12-13 10:18:38
     * FreightStatus : 1
     * FreightFee : 10.00
     * CollectPayStatus : 0
     * CollectAmount : 2.00
     * PaperNumber :
     * ConsignDate : 2017-12-08 10:56:09
     * Remark :
     * SignRemark :
     * WaybillCargoNo : 北-芜-8-69-8-2-100085
     * PickUpWay : 自提
     */

    private String WaybillId;
    private String WaybillNo;
    private String Shipper;
    private String DeliveryTel;
    private String DeliveryAddress;
    private String Receiver;
    private String ReceiveTel;
    private String ReceiveAddress;
    private String CargoName;
    private String SettleWay;
    private int TotalNumber;
    private String SignDate;
    private int FreightStatus;
    private String FreightFee;
    private int CollectPayStatus;
    private String CollectAmount;
    private String PaperNumber;
    private String ConsignDate;
    private String Remark;
    private String SignRemark;
    private String WaybillCargoNo;
    private String PickUpWay;

    private String ReceiptPayAmount;
    private String MonthPayAmount;

    private String CashPayAmount;

    private int IsToEnd;

    private String Signer;

    private String SendBranchName;

    private String SendBranchContactTel;

    private float TransferFee;

    private String TransferCompanyName;

    private String TransferNo;

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

    public String getSigner() {
        return Signer;
    }

    public void setSigner(String signer) {
        Signer = signer;
    }

    public String getSendBranchName() {
        return SendBranchName;
    }

    public void setSendBranchName(String sendBranchName) {
        SendBranchName = sendBranchName;
    }

    public String getSendBranchContactTel() {
        return SendBranchContactTel;
    }

    public void setSendBranchContactTel(String sendBranchContactTel) {
        SendBranchContactTel = sendBranchContactTel;
    }

    public String getReceiptPayAmount() {
        return ReceiptPayAmount;
    }

    public void setReceiptPayAmount(String receiptPayAmount) {
        ReceiptPayAmount = receiptPayAmount;
    }

    public String getMonthPayAmount() {
        return MonthPayAmount;
    }

    public void setMonthPayAmount(String monthPayAmount) {
        MonthPayAmount = monthPayAmount;
    }

    public String getCashPayAmount() {
        return CashPayAmount;
    }

    public void setCashPayAmount(String cashPayAmount) {
        CashPayAmount = cashPayAmount;
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

    public String getSettleWay() {
        return SettleWay;
    }

    public void setSettleWay(String SettleWay) {
        this.SettleWay = SettleWay;
    }

    public int getTotalNumber() {
        return TotalNumber;
    }

    public void setTotalNumber(int TotalNumber) {
        this.TotalNumber = TotalNumber;
    }

    public String getSignDate() {
        return SignDate;
    }

    public void setSignDate(String SignDate) {
        this.SignDate = SignDate;
    }

    public int getFreightStatus() {
        return FreightStatus;
    }

    public void setFreightStatus(int FreightStatus) {
        this.FreightStatus = FreightStatus;
    }

    public String getFreightFee() {
        return FreightFee;
    }

    public void setFreightFee(String FreightFee) {
        this.FreightFee = FreightFee;
    }

    public int getCollectPayStatus() {
        return CollectPayStatus;
    }

    public void setCollectPayStatus(int CollectPayStatus) {
        this.CollectPayStatus = CollectPayStatus;
    }

    public String getCollectAmount() {
        return CollectAmount;
    }

    public void setCollectAmount(String CollectAmount) {
        this.CollectAmount = CollectAmount;
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getSignRemark() {
        return SignRemark;
    }

    public void setSignRemark(String SignRemark) {
        this.SignRemark = SignRemark;
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
