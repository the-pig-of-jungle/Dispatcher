package com.liinji.ppgdeliver.bean;

/**
 * Created by 喜欢、陪你看风景 on 2017/12/6.
 */

public class ReDispatchOrder {

    /**
     * ID : 1081
     * ArticleName :
     * BasicFee : 10
     * BranchCode : 2001
     * BranchName : 合肥
     * CollectionTradeCharges : 0
     * CollectionCargoFee : 0
     * CompanyCode : 002
     * CompanyName : 千二物流
     * ConsignDate : 2017-09-20 15:30:31
     * DeliveryAddress :
     * DeliveryAddressDetail :
     * DeliveryTel :
     * Shipper : 121
     * DeliveryUserTel :
     * SettleWay : 现付
     * DepartureBranchCode : 2007
     * DepartureBranchName : 淮北
     * DestinationBranchCode : 2001
     * DestinationBranchName : 合肥
     * InsuranceFee : 0
     * IsSendAndLoad : 0
     * OperationTime : 2017-10-25 11:08:25
     * Operator : 管理员
     * ReceiveAddress :
     * ReceiveAddressDetail :
     * Receiver : 131
     * ReceiveTel :
     * StaffID : 58
     * StaffName : 赵
     * Status : 1
     * TotalFee : 10
     * TotalPieces : 1
     * WaybillNo : 02200726200021
     * WaybillStatus : 1
     * CashPayAmount : 10
     * PickUpPayAmount : 0
     * ReceiptPayAmount : 0
     * MonthPayAmount : 0
     * AdvanceTransitFee : 0
     * SignStatus : 0
     * SignDate :
     * FreightStatus : 1
     * CargoPaymentStatus : 0
     */

    private int ID;
    private String ArticleName;
    private int BasicFee;
    private String BranchCode;
    private String BranchName;
    private int CollectionTradeCharges;
    private int CollectionCargoFee;
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
    private int InsuranceFee;
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
    private int TotalFee;
    private int TotalPieces;
    private String WaybillNo;
    private int WaybillStatus;
    private int CashPayAmount;
    private int PickUpPayAmount;
    private int ReceiptPayAmount;
    private int MonthPayAmount;
    private int AdvanceTransitFee;
    private int SignStatus;
    private String SignDate;
    private int FreightStatus;
    private int CargoPaymentStatus;
    private boolean IsChecked;
    private static boolean sDefaultChecked;

    public static void setDefaultChecked(boolean defaultChecked) {
        sDefaultChecked = defaultChecked;
    }

    public ReDispatchOrder() {
        IsChecked = sDefaultChecked;
    }

    public boolean isChecked() {
        return IsChecked;
    }

    public void setChecked(boolean checked) {
        IsChecked = checked;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getArticleName() {
        return ArticleName;
    }

    public void setArticleName(String ArticleName) {
        this.ArticleName = ArticleName;
    }

    public int getBasicFee() {
        return BasicFee;
    }

    public void setBasicFee(int BasicFee) {
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

    public int getCollectionTradeCharges() {
        return CollectionTradeCharges;
    }

    public void setCollectionTradeCharges(int CollectionTradeCharges) {
        this.CollectionTradeCharges = CollectionTradeCharges;
    }

    public int getCollectionCargoFee() {
        return CollectionCargoFee;
    }

    public void setCollectionCargoFee(int CollectionCargoFee) {
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

    public int getInsuranceFee() {
        return InsuranceFee;
    }

    public void setInsuranceFee(int InsuranceFee) {
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

    public int getTotalFee() {
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

    public int getCashPayAmount() {
        return CashPayAmount;
    }

    public void setCashPayAmount(int CashPayAmount) {
        this.CashPayAmount = CashPayAmount;
    }

    public int getPickUpPayAmount() {
        return PickUpPayAmount;
    }

    public void setPickUpPayAmount(int PickUpPayAmount) {
        this.PickUpPayAmount = PickUpPayAmount;
    }

    public int getReceiptPayAmount() {
        return ReceiptPayAmount;
    }

    public void setReceiptPayAmount(int ReceiptPayAmount) {
        this.ReceiptPayAmount = ReceiptPayAmount;
    }

    public int getMonthPayAmount() {
        return MonthPayAmount;
    }

    public void setMonthPayAmount(int MonthPayAmount) {
        this.MonthPayAmount = MonthPayAmount;
    }

    public int getAdvanceTransitFee() {
        return AdvanceTransitFee;
    }

    public void setAdvanceTransitFee(int AdvanceTransitFee) {
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
}
