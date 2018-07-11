package com.liinji.ppgdeliver.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 朱志强 on 2017/7/18.
 */

public class BillOrder implements MultiItemEntity {

    public static final int ITEM_TYPE_COMPLETE = 0;
    public static final int ITEM_TYPE_DAISHOU = 1;

    /**
     * ArticleName : 汽配
     * BasicFee : 10
     * BranchCode : R0040
     * BranchName : 宁波
     * CollectionTradeCharges : 0
     * CollectionCargoFee : 0
     * CompanyCode : 001
     * CompanyName : 千一物流
     * ConsignDate : 2017-06-30 00:00:00
     * DeliveryAddress : 
     * DeliveryAddressDetail : 
     * DeliveryTel : 13567890987
     * Shipper : 张三
     * DeliveryUserTel : 
     * SettleWay : 提付
     * DepartureBranchCode : R0005
     * DepartureBranchName : 杭州
     * DestinationBranchCode : R0040
     * DestinationBranchName : 宁波
     * InsuranceFee : 0
     * IsSendAndLoad : 0
     * OperationTime : 2017-06-30 10:06:32
     * Operator : 赵杨杰
     * ReceiveAddress : XX
     * ReceiveAddressDetail : 
     * Receiver : 李四
     * ReceiveTel : 13678900987
     * StaffID : 36
     * StaffName : 许文照
     * Status : 3
     * TotalFee : 10
     * TotalPieces : 1
     * WaybillNo : 010051800016
     * WaybillStatus : 3
     * CashPayAmount : 0.0
     * PickUpPayAmount : 10.0
     * ReceiptPayAmount : 0.0
     * MonthPayAmount : 0.0
     * AdvanceTransitFee : 0.0
     * SignStatus : 0
     * SignDate : 2017/7/1 15:42:45
     * FreightStatus : 0
     * CargoPaymentStatus : 0
     */


    private String PaperNumber;
    private String ArticleName;
    private float BasicFee;
    private String BranchCode;
    private String BranchName;
    private float CollectionTradeCharges;
    private float CollectionCargoFee;
    private String CompanyCode;
    private String CompanyName;
    private String ConsignDate;
    private String DeliveryAddress;
    private String DeliveryAddressDetail;
    private String DeliveryTel;
    private String Shipper;
    private String DeliveryUserTel;
    private String SettleWay;
    private String DepartureBranchCode;
    private String DepartureBranchName;
    private String DestinationBranchCode;
    private String DestinationBranchName;
    private float InsuranceFee;
    private int IsSendAndLoad;
    private String OperationTime;
    private String Operator;
    private String ReceiveAddress;
    private String ReceiveAddressDetail;
    private String Receiver;
    private String ReceiveTel;
    private int StaffID;
    private String StaffName;
    private int Status;
    private float TotalFee;
    private int TotalPieces;
    private String WaybillNo;
    private int WaybillStatus;
    private float CashPayAmount;
    private float PickUpPayAmount;
    private float ReceiptPayAmount;
    private float MonthPayAmount;
    private float AdvanceTransitFee;
    private int SignStatus;
    private String SignDate;
    private int FreightStatus;
    private int CargoPaymentStatus;
    private String WaybillId;

    private int DayToSignDate;

    private String WaybillCargoNo;

    public String getWaybillCargoNo() {
        return WaybillCargoNo;
    }

    public void setWaybillCargoNo(String waybillCargoNo) {
        WaybillCargoNo = waybillCargoNo;
    }

    public String getPaperNumber() {
        return PaperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        PaperNumber = paperNumber;
    }

    public int getDayToSignDate() {
        return DayToSignDate;
    }

    public void setDayToSignDate(int dayToSignDate) {
        DayToSignDate = dayToSignDate;
    }

    public String getWaybillId() {
        return WaybillId;
    }

    public void setWaybillId(String waybillId) {
        WaybillId = waybillId;
    }

    public String getArticleName() {
        return ArticleName;
    }

    public void setArticleName(String ArticleName) {
        this.ArticleName = ArticleName;
    }

    public float getBasicFee() {
        return BasicFee;
    }

    public void setBasicFee(float BasicFee) {
        this.BasicFee = BasicFee;
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

    public float getCollectionTradeCharges() {
        return CollectionTradeCharges;
    }

    public void setCollectionTradeCharges(float CollectionTradeCharges) {
        this.CollectionTradeCharges = CollectionTradeCharges;
    }

    public float getCollectionCargoFee() {
        return CollectionCargoFee;
    }

    public void setCollectionCargoFee(float CollectionCargoFee) {
        this.CollectionCargoFee = CollectionCargoFee;
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

    public String getConsignDate() {
        return ConsignDate;
    }

    public void setConsignDate(String ConsignDate) {
        this.ConsignDate = ConsignDate;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }

    public void setDeliveryAddress(String DeliveryAddress) {
        this.DeliveryAddress = DeliveryAddress;
    }

    public String getDeliveryAddressDetail() {
        return DeliveryAddressDetail;
    }

    public void setDeliveryAddressDetail(String DeliveryAddressDetail) {
        this.DeliveryAddressDetail = DeliveryAddressDetail;
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

    public String getDeliveryUserTel() {
        return DeliveryUserTel;
    }

    public void setDeliveryUserTel(String DeliveryUserTel) {
        this.DeliveryUserTel = DeliveryUserTel;
    }

    public String getSettleWay() {
        return SettleWay;
    }

    public void setSettleWay(String SettleWay) {
        this.SettleWay = SettleWay;
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

    public float getInsuranceFee() {
        return InsuranceFee;
    }

    public void setInsuranceFee(float InsuranceFee) {
        this.InsuranceFee = InsuranceFee;
    }

    public int getIsSendAndLoad() {
        return IsSendAndLoad;
    }

    public void setIsSendAndLoad(int IsSendAndLoad) {
        this.IsSendAndLoad = IsSendAndLoad;
    }

    public String getOperationTime() {
        return OperationTime;
    }

    public void setOperationTime(String OperationTime) {
        this.OperationTime = OperationTime;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String Operator) {
        this.Operator = Operator;
    }

    public String getReceiveAddress() {
        return ReceiveAddress;
    }

    public void setReceiveAddress(String ReceiveAddress) {
        this.ReceiveAddress = ReceiveAddress;
    }

    public String getReceiveAddressDetail() {
        return ReceiveAddressDetail;
    }

    public void setReceiveAddressDetail(String ReceiveAddressDetail) {
        this.ReceiveAddressDetail = ReceiveAddressDetail;
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

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int StaffID) {
        this.StaffID = StaffID;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public float getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(int TotalFee) {
        this.TotalFee = TotalFee;
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

    public int getWaybillStatus() {
        return WaybillStatus;
    }

    public void setWaybillStatus(int WaybillStatus) {
        this.WaybillStatus = WaybillStatus;
    }

    public float getCashPayAmount() {
        return CashPayAmount;
    }

    public void setCashPayAmount(float CashPayAmount) {
        this.CashPayAmount = CashPayAmount;
    }

    public float getPickUpPayAmount() {
        return PickUpPayAmount;
    }

    public void setPickUpPayAmount(float PickUpPayAmount) {
        this.PickUpPayAmount = PickUpPayAmount;
    }

    public float getReceiptPayAmount() {
        return ReceiptPayAmount;
    }

    public void setReceiptPayAmount(float ReceiptPayAmount) {
        this.ReceiptPayAmount = ReceiptPayAmount;
    }

    public float getMonthPayAmount() {
        return MonthPayAmount;
    }

    public void setMonthPayAmount(float MonthPayAmount) {
        this.MonthPayAmount = MonthPayAmount;
    }

    public float getAdvanceTransitFee() {
        return AdvanceTransitFee;
    }

    public void setAdvanceTransitFee(float AdvanceTransitFee) {
        this.AdvanceTransitFee = AdvanceTransitFee;
    }

    public int getSignStatus() {
        return SignStatus;
    }

    public void setSignStatus(int SignStatus) {
        this.SignStatus = SignStatus;
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

    public int getCargoPaymentStatus() {
        return CargoPaymentStatus;
    }

    public void setCargoPaymentStatus(int CargoPaymentStatus) {
        this.CargoPaymentStatus = CargoPaymentStatus;
    }


    @Override
    public int getItemType() {


        if ((getCargoPaymentStatus() == 0 && getCollectionTradeCharges() != 0) || (getFreightStatus() == 0 && getPickUpPayAmount() != 0)){
            return ITEM_TYPE_DAISHOU;
        }

        return ITEM_TYPE_COMPLETE;
    }
}
