package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Order {


    /**
     * ID : 79
     * ArticleName : M
     * BasicFee : 35
     * BranchCode : 031
     * BranchName : 喀什
     * CollectionTradeCharges : 600
     * CollectionCargoFee : 0
     * CompanyCode : 001
     * CompanyName : 千一物流
     * ConsignDate : 2017-03-31 10:55:50
     * DeliveryAddress : 新疆省乌鲁木齐头屯河区
     * DeliveryAddressDetail :
     * DeliveryTel : 13621671234
     * Shipper : 杨梅
     * DeliveryUserTel : 18602187924
     * SettleWay : 提付
     * DepartureBranchCode : 001
     * DepartureBranchName : 总部（乌）
     * DestinationBranchCode : 031
     * DestinationBranchName : 喀什
     * InsuranceFee : 0
     * IsSendAndLoad : 0
     * OperationTime : 2017-03-31 11:06:58
     * Operator : 赵杨杰
     * ReceiveAddress : 上海市上海市松江区
     * ReceiveAddressDetail :
     * Receiver : 马力
     * ReceiveTel : 15812345678
     * StaffID : 41
     * StaffName : 赵杨杰
     * Status : 1
     * TotalFee : 35
     * TotalPieces : 1
     * WaybillNo : 0100001008900148
     * WaybillStatus : 3
     * CashPayAmount : 0
     * PickUpPayAmount : 35
     * ReceiptPayAmount : 0
     * MonthPayAmount : 0
     * AdvanceTransitFee : 0
     * ReduceAmount : 0
     * SignStatus : 0
     */

    private String ClerkName;
    private String SendBranchName;




    private int ID;
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
    private float ReduceAmount;
    private int SignStatus;
    private String WaybillId;

    private String SignDate;
    private String TempSignRemarks = "";

    private boolean mIsChecked;
    private static boolean sDefaultChecked;
    private int IsReturn;

    private String TripNo;

    private String PaperNumber;
    private String Remark;

    //货号
    private String WaybillCargoNo;

    //寄件点电话
    private String SendBranchContactTel;

    //派件点地址
    private String DispatchBranchAddress;

    //派件点电话
    private String DispatchBranchContactTel;

    private String CompanyShortName;

    public String getCompanyShortName() {
        return CompanyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        CompanyShortName = companyShortName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getPaperNumber() {
        return PaperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        PaperNumber = paperNumber;
    }

    public String getTripNo() {
        return TripNo;
    }

    public void setTripNo(String tripNo) {
        TripNo = tripNo;
    }

    private String ServiceTel;

    public String getServiceTel() {
        return ServiceTel;
    }

    public void setServiceTel(String serviceTel) {
        ServiceTel = serviceTel;
    }

    public Order(){
        mIsChecked = sDefaultChecked;
    }

    public static final void setDefaultChecked(boolean checked){
        sDefaultChecked = checked;
    }


    public int getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(int isReturn) {
        IsReturn = isReturn;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }

    public String getSignDate() {
        return SignDate;
    }

    public void setSignDate(String signDate) {
        SignDate = signDate;
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

    public void setTotalFee(float TotalFee) {
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

    public float getReduceAmount() {
        return ReduceAmount;
    }

    public void setReduceAmount(float ReduceAmount) {
        this.ReduceAmount = ReduceAmount;
    }

    public int getSignStatus() {
        return SignStatus;
    }

    public void setSignStatus(int SignStatus) {
        this.SignStatus = SignStatus;
    }

    @Override
    public String toString() {
        return getWaybillNo();
    }

    public String getClerkName() {
        return ClerkName;
    }

    public void setClerkName(String clerkName) {
        ClerkName = clerkName;
    }

    public String getSendBranchName() {
        return SendBranchName;
    }

    public void setSendBranchName(String sendBranchName) {
        SendBranchName = sendBranchName;
    }

    public String getWaybillId() {
        return WaybillId;
    }

    public void setWaybillId(String waybillId) {
        WaybillId = waybillId;
    }

    public String getTempSignRemarks() {
        return TempSignRemarks;
    }

    public void setTempSignRemarks(String tempSignRemarks) {
        TempSignRemarks = tempSignRemarks;
    }

    public String getWaybillCargoNo() {
        return WaybillCargoNo;
    }

    public void setWaybillCargoNo(String waybillCargoNo) {
        WaybillCargoNo = waybillCargoNo;
    }

    public String getSendBranchContactTel() {
        return SendBranchContactTel;
    }

    public void setSendBranchContactTel(String sendBranchContactTel) {
        SendBranchContactTel = sendBranchContactTel;
    }

    public String getDispatchBranchAddress() {
        return DispatchBranchAddress;
    }

    public void setDispatchBranchAddress(String dispatchBranchAddress) {
        DispatchBranchAddress = dispatchBranchAddress;
    }

    public String getDispatchBranchContactTel() {
        return DispatchBranchContactTel;
    }

    public void setDispatchBranchContactTel(String dispatchBranchContactTel) {
        DispatchBranchContactTel = dispatchBranchContactTel;
    }
}
