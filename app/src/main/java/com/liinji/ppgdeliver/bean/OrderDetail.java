package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/28.
 */
public class OrderDetail {

    /**
     * WaybillInfo : {"WaybillId":"b56c4aff-bb30-468e-be42-bc1cdf40801d","WaybillNo":"0100001011300409","Shipper":"钟亮","DeliveryTel":"18816568506","DeliveryAddress":"上海市上海市闵行区浦江镇联航路1588号1幢B212室","Receiver":"袁林","ReceiveTel":"18602187924","ReceiveAddress":"江苏省南京市玄武区白水路与衡南路交叉口2685号C座506室","CargoName":"七","TotalNumber":1,"PackageWay":"箱子","WaybillStatus":4,"PutAmount":"10.00","CashAmount":"0.00","MonthAmount":"0.00","CollectionTradeCharges":"1000.00","AdvanceTransitFee":"0.00","PremiumAmount":"0.00","PayMode":"提付","TotalAmount":"10.00","Remark":"","DepartureBranchCode":"001","DepartureBranchName":"总部（乌）","DestinationBranchCode":"031","DestinationBranchName":"喀什","TransitBranchCode":"","TransitBranchName":"","CollectCargoFee":"1000.00","PickUpFee":10,"ReduceAmount":"0","OperationTime":"2017-04-24 16:34:53","CorWaybillStatus":3,"IsSendAndLoad":1,"SignDate":"2017-04-25 14:28:47"}
     */

    private WaybillInfoBean WaybillInfo;

    public WaybillInfoBean getWaybillInfo() {
        return WaybillInfo;
    }

    public void setWaybillInfo(WaybillInfoBean WaybillInfo) {
        this.WaybillInfo = WaybillInfo;
    }

    public static class WaybillInfoBean {
        /**
         * WaybillId : b56c4aff-bb30-468e-be42-bc1cdf40801d
         * WaybillNo : 0100001011300409
         * Shipper : 钟亮
         * DeliveryTel : 18816568506
         * DeliveryAddress : 上海市上海市闵行区浦江镇联航路1588号1幢B212室
         * Receiver : 袁林
         * ReceiveTel : 18602187924
         * ReceiveAddress : 江苏省南京市玄武区白水路与衡南路交叉口2685号C座506室
         * CargoName : 七
         * TotalNumber : 1
         * PackageWay : 箱子
         * WaybillStatus : 4
         * PutAmount : 10.00
         * CashAmount : 0.00
         * MonthAmount : 0.00
         * CollectionTradeCharges : 1000.00
         * AdvanceTransitFee : 0.00
         * PremiumAmount : 0.00
         * PayMode : 提付
         * TotalAmount : 10.00
         * Remark :
         * DepartureBranchCode : 001
         * DepartureBranchName : 总部（乌）
         * DestinationBranchCode : 031
         * DestinationBranchName : 喀什
         * TransitBranchCode :
         * TransitBranchName :
         * CollectCargoFee : 1000.00
         * PickUpFee : 10
         * ReduceAmount : 0
         * OperationTime : 2017-04-24 16:34:53
         * CorWaybillStatus : 3
         * IsSendAndLoad : 1
         * SignDate : 2017-04-25 14:28:47
         */


        private String ClerkName;
        private String SendBranchName;

        private String DispatchBranchName;

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
        private int WaybillStatus;
        private String PutAmount;
        private String CashAmount;
        private String MonthAmount;
        private String CollectionTradeCharges;
        private String AdvanceTransitFee;
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
        private float PickUpFee;
        private String ReduceAmount;
        private String OperationTime;
        private int CorWaybillStatus;
        private int IsSendAndLoad;
        private String SignDate;
        private String PayTypeDesc;
        private int IsReturn;
        private float OriginalFerightAmount;
        private float OriginalPaymentAmount;

        private float Rebate;
        private int RebateWay;



        private float BasicFee;

        private String PaperNumber;

        private String SignRemark;

        private String ConsignDate;

        //货号
        private String WaybillCargoNo;

        //寄件点电话
        private String SendBranchContactTel;

        //派件点地址
        private String DispatchBranchAddress;

        //派件点电话
        private String DispatchBranchContactTel;

        private String CompanyShortName;

        private String CompanyName;

        private String ServiceTel;

        private String PickUpWay;

        private float TransferFee;
        private String TransferCompanyName;
        private String TransferNo;

        private int IsToEnd;

        private int IsRegisteTransfer;

        private String CompanyUrl;
        private String AdWords;

        private String ReceiptPayAmount;


        private float DeliveryFee;

        private String Signer;

        public String getSigner() {
            return Signer;
        }

        public void setSigner(String signer) {
            Signer = signer;
        }

        public float getDeliveryFee() {
            return DeliveryFee;
        }

        public void setDeliveryFee(float deliveryFee) {
            DeliveryFee = deliveryFee;
        }

        public String getReceiptPayAmount() {
            return ReceiptPayAmount;
        }

        public void setReceiptPayAmount(String receiptPayAmount) {
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

        public int getIsRegisteTransfer() {
            return IsRegisteTransfer;
        }

        public void setIsRegisteTransfer(int isRegisteTransfer) {
            IsRegisteTransfer = isRegisteTransfer;
        }

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

        public void setTransferNo(String transferFeeNo) {
            TransferNo = transferFeeNo;
        }

        public int getIsToEnd() {
            return IsToEnd;
        }

        public void setIsToEnd(int isToEnd) {
            IsToEnd = isToEnd;
        }

        public String getPickUpWay() {
            return PickUpWay;
        }

        public void setPickUpWay(String pickUpWay) {
            PickUpWay = pickUpWay;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String companyName) {
            CompanyName = companyName;
        }

        public String getServiceTel() {
            return ServiceTel;
        }

        public void setServiceTel(String serviceTel) {
            ServiceTel = serviceTel;
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

        public String getCompanyShortName() {
            return CompanyShortName;
        }

        public void setCompanyShortName(String companyShortName) {
            CompanyShortName = companyShortName;
        }

        public String getConsignDate() {
            return ConsignDate;
        }

        public void setConsignDate(String consignDate) {
            ConsignDate = consignDate;
        }

        public String getSignRemark() {
            return SignRemark;
        }

        public void setSignRemark(String signRemark) {
            SignRemark = signRemark;
        }

        public String getPaperNumber() {
            return PaperNumber;
        }

        public void setPaperNumber(String paperNumber) {
            PaperNumber = paperNumber;
        }

        public float getBasicFee() {
            return BasicFee;
        }

        public void setBasicFee(float basicFee) {
            BasicFee = basicFee;
        }

        public float getOriginalFerightAmount() {
            return OriginalFerightAmount;
        }

        public void setOriginalFerightAmount(float originalFerightAmount) {
            OriginalFerightAmount = originalFerightAmount;
        }

        public float getOriginalPaymentAmount() {
            return OriginalPaymentAmount;
        }

        public void setOriginalPaymentAmount(float originalPaymentAmount) {
            OriginalPaymentAmount = originalPaymentAmount;
        }

        public int getIsReturn() {
            return IsReturn;
        }

        public void setIsReturn(int isReturn) {
            IsReturn = isReturn;
        }

        public String getPayTypeDesc() {
            return PayTypeDesc;
        }

        public void setPayTypeDesc(String payTypeDesc) {
            PayTypeDesc = payTypeDesc;
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

        public int getWaybillStatus() {
            return WaybillStatus;
        }

        public void setWaybillStatus(int WaybillStatus) {
            this.WaybillStatus = WaybillStatus;
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

        public String getCollectionTradeCharges() {
            return CollectionTradeCharges;
        }

        public void setCollectionTradeCharges(String CollectionTradeCharges) {
            this.CollectionTradeCharges = CollectionTradeCharges;
        }

        public String getAdvanceTransitFee() {
            return AdvanceTransitFee;
        }

        public void setAdvanceTransitFee(String AdvanceTransitFee) {
            this.AdvanceTransitFee = AdvanceTransitFee;
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

        public float getPickUpFee() {
            return PickUpFee;
        }

        public void setPickUpFee(float PickUpFee) {
            this.PickUpFee = PickUpFee;
        }

        public String getReduceAmount() {
            return ReduceAmount;
        }

        public void setReduceAmount(String ReduceAmount) {
            this.ReduceAmount = ReduceAmount;
        }

        public String getOperationTime() {
            return OperationTime;
        }

        public void setOperationTime(String OperationTime) {
            this.OperationTime = OperationTime;
        }

        public int getCorWaybillStatus() {
            return CorWaybillStatus;
        }

        public void setCorWaybillStatus(int CorWaybillStatus) {
            this.CorWaybillStatus = CorWaybillStatus;
        }

        public int getIsSendAndLoad() {
            return IsSendAndLoad;
        }

        public void setIsSendAndLoad(int IsSendAndLoad) {
            this.IsSendAndLoad = IsSendAndLoad;
        }

        public String getSignDate() {
            return SignDate;
        }

        public void setSignDate(String SignDate) {
            this.SignDate = SignDate;
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

        public String getDispatchBranchName() {
            return DispatchBranchName;
        }

        public void setDispatchBranchName(String dispatchBranchName) {
            DispatchBranchName = dispatchBranchName;
        }

        public float getRebate() {
            return Rebate;
        }

        public void setRebate(float rebate) {
            Rebate = rebate;
        }

        public int getRebateWay() {
            return RebateWay;
        }

        public void setRebateWay(int rebateWay) {
            RebateWay = rebateWay;
        }
    }
}
