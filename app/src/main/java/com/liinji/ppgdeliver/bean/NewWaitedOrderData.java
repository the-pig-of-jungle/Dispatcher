package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by pig on 2017/11/28.
 */

public class NewWaitedOrderData {

    /**
     * ReturnData : [{"ID":1153,"ArticleName":"","BasicFee":10,"BranchCode":"2001","BranchName":"合肥","CollectionTradeCharges":5,"CollectionCargoFee":0,"CompanyCode":"002","CompanyName":"千二物流","ConsignDate":"2017-10-31 10:00:57","DeliveryAddress":"浙江省杭州市桐庐县新阳路100号新阳汽配城1535号","DeliveryAddressDetail":"","DeliveryTel":"05584031715","Shipper":"联积汽配","DeliveryUserTel":"13621671346","SettleWay":"现付","DepartureBranchCode":"2007","DepartureBranchName":"淮北","DestinationBranchCode":"2001","DestinationBranchName":"合肥","InsuranceFee":0,"IsSendAndLoad":0,"OperationTime":"2017-10-31 10:50:25","Operator":"管理员","ReceiveAddress":"上海上海市黄浦区上海外滩8888","ReceiveAddressDetail":"","Receiver":"刘实","ReceiveTel":"055689898986","StaffID":58,"StaffName":"赵","Status":1,"TotalFee":10,"TotalPieces":1,"WaybillId":"8d35c27a-2943-4b09-bd49-23735d0303cd","WaybillNo":"02200730300021","WaybillStatus":3,"CashPayAmount":10,"PickUpPayAmount":0,"ReceiptPayAmount":0,"MonthPayAmount":0,"AdvanceTransitFee":0,"SignStatus":0,"SignDate":"","FreightStatus":1,"CargoPaymentStatus":0,"IsReturn":0,"OriginalFerightAmount":0,"OriginalPaymentAmount":5,"TripNo":"CT00217103100008","SendBranchName":"淮北","ClerkName":"陈晓","PaperNumber":"","Remark":"","ServiceTel":"021-5888666","CompanyAddress":"","CompanyShortName":"联积物流","Tel":"13482347495","SendBranchAddress":"安徽省合肥市肥西县西丰镇1588号","SendBranchContact":"15612301230","SendBranchContactTel":"15612301230","DispatchBranchAddress":"浙江省杭州市西湖区宝马汽配城D区4020号","DispatchBranchContact":"赵丽","DispatchBranchContactTel":"15645611230","Contacts":"袁林","WaybillCargoNo":""},{"ID":1154,"ArticleName":"","BasicFee":10,"BranchCode":"2001","BranchName":"合肥","CollectionTradeCharges":0,"CollectionCargoFee":0,"CompanyCode":"002","CompanyName":"千二物流","ConsignDate":"2017-10-31 10:00:02","DeliveryAddress":"浙江省杭州市桐庐县新阳路100号新阳汽配城1535号","DeliveryAddressDetail":"","DeliveryTel":"05584031715","Shipper":"联积汽配","DeliveryUserTel":"13621671346","SettleWay":"现付","DepartureBranchCode":"2007","DepartureBranchName":"淮北","DestinationBranchCode":"2001","DestinationBranchName":"合肥","InsuranceFee":0,"IsSendAndLoad":0,"OperationTime":"2017-10-31 10:50:25","Operator":"管理员","ReceiveAddress":"上海上海市黄浦区上海外滩8888","ReceiveAddressDetail":"","Receiver":"刘实","ReceiveTel":"055689898986","StaffID":58,"StaffName":"赵","Status":1,"TotalFee":10,"TotalPieces":2,"WaybillId":"0991bf9b-9e7b-4f29-a9e9-03abd471b8c5","WaybillNo":"02200730300014","WaybillStatus":3,"CashPayAmount":10,"PickUpPayAmount":0,"ReceiptPayAmount":0,"MonthPayAmount":0,"AdvanceTransitFee":0,"SignStatus":0,"SignDate":"","FreightStatus":1,"CargoPaymentStatus":0,"IsReturn":0,"OriginalFerightAmount":0,"OriginalPaymentAmount":5,"TripNo":"CT00217103100008","SendBranchName":"淮北","ClerkName":"陈晓","PaperNumber":"","Remark":"","ServiceTel":"021-5888666","CompanyAddress":"","CompanyShortName":"联积物流","Tel":"13482347495","SendBranchAddress":"安徽省合肥市肥西县西丰镇1588号","SendBranchContact":"15612301230","SendBranchContactTel":"15612301230","DispatchBranchAddress":"浙江省杭州市西湖区宝马汽配城D区4020号","DispatchBranchContact":"赵丽","DispatchBranchContactTel":"15645611230","Contacts":"袁林","WaybillCargoNo":""}]
     * TotalMessage : {"SumPieces":"48","SumRecords":"28"}
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
         * SumPieces : 48
         * SumRecords : 28
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
         * ID : 1153
         * ArticleName :
         * BasicFee : 10
         * BranchCode : 2001
         * BranchName : 合肥
         * CollectionTradeCharges : 5
         * CollectionCargoFee : 0
         * CompanyCode : 002
         * CompanyName : 千二物流
         * ConsignDate : 2017-10-31 10:00:57
         * DeliveryAddress : 浙江省杭州市桐庐县新阳路100号新阳汽配城1535号
         * DeliveryAddressDetail :
         * DeliveryTel : 05584031715
         * Shipper : 联积汽配
         * DeliveryUserTel : 13621671346
         * SettleWay : 现付
         * DepartureBranchCode : 2007
         * DepartureBranchName : 淮北
         * DestinationBranchCode : 2001
         * DestinationBranchName : 合肥
         * InsuranceFee : 0
         * IsSendAndLoad : 0
         * OperationTime : 2017-10-31 10:50:25
         * Operator : 管理员
         * ReceiveAddress : 上海上海市黄浦区上海外滩8888
         * ReceiveAddressDetail :
         * Receiver : 刘实
         * ReceiveTel : 055689898986
         * StaffID : 58
         * StaffName : 赵
         * Status : 1
         * TotalFee : 10
         * TotalPieces : 1
         * WaybillId : 8d35c27a-2943-4b09-bd49-23735d0303cd
         * WaybillNo : 02200730300021
         * WaybillStatus : 3
         * CashPayAmount : 10
         * PickUpPayAmount : 0
         * ReceiptPayAmount : 0
         * MonthPayAmount : 0
         * AdvanceTransitFee : 0
         * SignStatus : 0
         * SignDate :
         * FreightStatus : 1
         * CargoPaymentStatus : 0
         * IsReturn : 0
         * OriginalFerightAmount : 0
         * OriginalPaymentAmount : 5
         * TripNo : CT00217103100008
         * SendBranchName : 淮北
         * ClerkName : 陈晓
         * PaperNumber :
         * Remark :
         * ServiceTel : 021-5888666
         * CompanyAddress :
         * CompanyShortName : 联积物流
         * Tel : 13482347495
         * SendBranchAddress : 安徽省合肥市肥西县西丰镇1588号
         * SendBranchContact : 15612301230
         * SendBranchContactTel : 15612301230
         * DispatchBranchAddress : 浙江省杭州市西湖区宝马汽配城D区4020号
         * DispatchBranchContact : 赵丽
         * DispatchBranchContactTel : 15645611230
         * Contacts : 袁林
         * WaybillCargoNo :
         */

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
        private String WaybillId;
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
        private int IsReturn;
        private float OriginalFerightAmount;
        private float OriginalPaymentAmount;
        private String TripNo;
        private String SendBranchName;
        private String ClerkName;
        private String PaperNumber;
        private String Remark;
        private String ServiceTel;
        private String CompanyAddress;
        private String CompanyShortName;
        private String Tel;
        private String SendBranchAddress;
        private String SendBranchContact;
        private String SendBranchContactTel;
        private String DispatchBranchAddress;
        private String DispatchBranchContact;
        private String DispatchBranchContactTel;
        private String Contacts;
        private String WaybillCargoNo;

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

        public int getIsReturn() {
            return IsReturn;
        }

        public void setIsReturn(int IsReturn) {
            this.IsReturn = IsReturn;
        }

        public float getOriginalFerightAmount() {
            return OriginalFerightAmount;
        }

        public void setOriginalFerightAmount(float OriginalFerightAmount) {
            this.OriginalFerightAmount = OriginalFerightAmount;
        }

        public float getOriginalPaymentAmount() {
            return OriginalPaymentAmount;
        }

        public void setOriginalPaymentAmount(float OriginalPaymentAmount) {
            this.OriginalPaymentAmount = OriginalPaymentAmount;
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

        public String getCompanyAddress() {
            return CompanyAddress;
        }

        public void setCompanyAddress(String CompanyAddress) {
            this.CompanyAddress = CompanyAddress;
        }

        public String getCompanyShortName() {
            return CompanyShortName;
        }

        public void setCompanyShortName(String CompanyShortName) {
            this.CompanyShortName = CompanyShortName;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getSendBranchAddress() {
            return SendBranchAddress;
        }

        public void setSendBranchAddress(String SendBranchAddress) {
            this.SendBranchAddress = SendBranchAddress;
        }

        public String getSendBranchContact() {
            return SendBranchContact;
        }

        public void setSendBranchContact(String SendBranchContact) {
            this.SendBranchContact = SendBranchContact;
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

        public String getDispatchBranchContact() {
            return DispatchBranchContact;
        }

        public void setDispatchBranchContact(String DispatchBranchContact) {
            this.DispatchBranchContact = DispatchBranchContact;
        }

        public String getDispatchBranchContactTel() {
            return DispatchBranchContactTel;
        }

        public void setDispatchBranchContactTel(String DispatchBranchContactTel) {
            this.DispatchBranchContactTel = DispatchBranchContactTel;
        }

        public String getContacts() {
            return Contacts;
        }

        public void setContacts(String Contacts) {
            this.Contacts = Contacts;
        }

        public String getWaybillCargoNo() {
            return WaybillCargoNo;
        }

        public void setWaybillCargoNo(String WaybillCargoNo) {
            this.WaybillCargoNo = WaybillCargoNo;
        }
    }
}
