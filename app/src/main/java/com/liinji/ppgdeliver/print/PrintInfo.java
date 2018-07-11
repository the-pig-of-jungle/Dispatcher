package com.liinji.ppgdeliver.print;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.PrintOrders;

/**
 * Created by 朱志强 on 2017/8/17.
 */

public class PrintInfo implements Parcelable {

    public static final String RECEIVE_TIP = "注：货物一旦签收，收货人即认可此票货物无异议";
    public static final String SIGN_TIP = "收货人签字：";
    public static final String NEW_RECEIVE_TIP = "注：1.此票有效期一个月；2.货物一旦签收，收货人即认可此票货物无异议。";



    private String mCompanyName = "";
    private String mCustomerService = "";
    private String mOrderNumber = "";
    private String mGoodsSentTime = "";
    private String mSender = "";
    private String mSenderTel = "";
    private String mGoodsName = "";
    private String mFreight = "0.00";
    private String mCollectionFee = "0.00";
    private String mTotalFee = "0.00";
    private String mGoodsCount = "0";
    private String mSettlementWay = "";
    private String mReceiver = "";
    private String mReceiverTel = "";
    private String mReceiverAddress = "";
    private String mBranchAddress = "";
    private String mBranchTel = "";
    private String mSequence = "";
    private int mNeedPrintSequence = 1;

    private int mIsNotSequence = 0;

    private int mTime = 1;

    private String mPBranchName = "";
    private String mSBranchName = "";
    private String mClerkName="";

    private String mPaperNumber="";

    private String mRemark = "";

    //货号
    private String WaybillCargoNo = "";

    //寄件点电话
    private String SendBranchContactTel = "";

    //派件点地址
    private String DispatchBranchAddress = "";

    //派件点电话
    private String DispatchBranchContactTel = "";

    private String AdvanceTransitFee = "";

    private String SpaceOrder = "";

    private String PickUpWay = "";

    private String CompanyUrl = "";

    private String AdWords = "";

    private String PayModeSuffix = "";

    private String DeliveryFee;

    public String getDeliveryFee() {
        return DeliveryFee;
    }

    public PrintInfo setDeliveryFee(String deliveryFee) {
        DeliveryFee = deliveryFee;
        return this;
    }

    public String getPayModeSuffix() {
        return PayModeSuffix;
    }

    public PrintInfo setPayModeSuffix(String payModeSuffix) {
        PayModeSuffix = payModeSuffix;
        return this;
    }

    public String getAdWords() {
        return AdWords;
    }

    public PrintInfo setAdWords(String adWords) {
        AdWords = adWords;
        return this;
    }

    public String getCompanyUrl() {
        return CompanyUrl;
    }

    public PrintInfo setCompanyUrl(String companyUrl) {

        CompanyUrl = companyUrl;
        return this;
    }

    public String getPickUpWay() {
        return PickUpWay;
    }

    public PrintInfo setPickUpWay(String pickUpWay) {
        PickUpWay = pickUpWay;
        return this;
    }

    public String getWaybillCargoNo() {
        return WaybillCargoNo;
    }

    public PrintInfo setWaybillCargoNo(String waybillCargoNo) {
        WaybillCargoNo = waybillCargoNo;
        return this;
    }

    public String getSendBranchContactTel() {
        return SendBranchContactTel;
    }

    public PrintInfo setSendBranchContactTel(String sendBranchContactTel) {
        SendBranchContactTel = sendBranchContactTel;
        return this;
    }

    public String getRemark() {
        return mRemark;
    }

    public PrintInfo setRemark(String remark) {
        mRemark = remark;
        return this;
    }

    public String getPaperNumber() {
        return mPaperNumber;
    }

    public PrintInfo setPaperNumber(String paperNumber) {
        mPaperNumber = paperNumber;
        return this;
    }

    public String getPBranchName() {
        return mPBranchName;
    }

    public PrintInfo setPBranchName(String PBranchName) {
        mPBranchName = PBranchName;
        return this;
    }

    public String getSBranchName() {
        return mSBranchName;
    }

    public PrintInfo setSBranchName(String SBranchName) {
        mSBranchName = SBranchName;
        return this;
    }

    public String getClerkName() {
        return mClerkName;
    }

    public PrintInfo setClerkName(String clerkName) {
        mClerkName = clerkName;
        return this;
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
    }

    public void addTime() {
        mTime++;
    }

    public int getIsNotSequence() {
        return mIsNotSequence;
    }

    public PrintInfo setIsNotSequence(int isNotSequence) {
        mIsNotSequence = isNotSequence;
        return this;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public PrintInfo setCompanyName(String companyName) {
        mCompanyName = companyName;
        return this;
    }

    public String getCustomerService() {
        return mCustomerService;
    }

    public PrintInfo setCustomerService(String customerService) {
        mCustomerService = customerService;
        return this;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public PrintInfo setOrderNumber(String orderNumber) {
        mOrderNumber = orderNumber;
        return this;
    }

    public String getGoodsSentTime() {
        return mGoodsSentTime;
    }

    public PrintInfo setGoodsSentTime(String goodsSentTime) {
        mGoodsSentTime = goodsSentTime;
        return this;
    }

    public String getSender() {
        return mSender;
    }

    public PrintInfo setSender(String sender) {
        mSender = sender;
        return this;
    }

    public String getSenderTel() {
        return mSenderTel;
    }

    public PrintInfo setSenderTel(String senderTel) {
        mSenderTel = senderTel;
        return this;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public PrintInfo setGoodsName(String goodsName) {
        mGoodsName = goodsName;
        return this;
    }

    public String getFreight() {
        return mFreight;
    }

    public PrintInfo setFreight(String freight) {
        mFreight = freight;
        return this;
    }

    public String getCollectionFee() {
        return mCollectionFee;
    }

    public PrintInfo setCollectionFee(String collectionFee) {
        mCollectionFee = collectionFee;
        return this;
    }

    public String getTotalFee() {
        return mTotalFee;
    }

    public PrintInfo setTotalFee(String totalFee) {
        mTotalFee = totalFee;
        return this;
    }

    public String getGoodsCount() {
        return mGoodsCount;
    }

    public PrintInfo setGoodsCount(String goodsCount) {
        mGoodsCount = goodsCount;
        return this;
    }

    public String getSettlementWay() {
        return mSettlementWay;
    }

    public PrintInfo setSettlementWay(String settlementWay) {
        mSettlementWay = settlementWay;
        return this;
    }

    public String getReceiver() {
        return mReceiver;
    }

    public PrintInfo setReceiver(String receiver) {
        mReceiver = receiver;
        return this;
    }

    public String getReceiverTel() {
        return mReceiverTel;
    }

    public PrintInfo setReceiverTel(String receiverTel) {
        mReceiverTel = receiverTel;
        return this;
    }

    public String getReceiverAddress() {
        return mReceiverAddress;
    }

    public PrintInfo setReceiverAddress(String receiverAddress) {
        mReceiverAddress = receiverAddress;
        return this;
    }

    public String getBranchAddress() {
        return mBranchAddress;
    }

    public PrintInfo setBranchAddress(String branchAddress) {
        mBranchAddress = branchAddress;
        return this;
    }

    public String getBranchTel() {
        return mBranchTel;
    }

    public PrintInfo setBranchTel(String branchTel) {
        mBranchTel = branchTel;
        return this;
    }

    public int isNeedPrintSequence() {
        return mNeedPrintSequence;
    }

    public PrintInfo setNeedPrintSequence(int needPrintSequence) {
        mNeedPrintSequence = needPrintSequence;
        return this;
    }

    public String getDispatchBranchAddress() {
        return DispatchBranchAddress;
    }

    public PrintInfo setDispatchBranchAddress(String dispatchBranchAddress) {
        DispatchBranchAddress = dispatchBranchAddress;
        return this;
    }

    public String getDispatchBranchContactTel() {
        return DispatchBranchContactTel;
    }

    public PrintInfo setDispatchBranchContactTel(String dispatchBranchContactTel) {
        DispatchBranchContactTel = dispatchBranchContactTel;
        return this;
    }

    public String getAdvanceTransitFee() {
        return AdvanceTransitFee;
    }

    public void setAdvanceTransitFee(String advanceTransitFee) {
        AdvanceTransitFee = advanceTransitFee;
    }

    public String getSpaceOrder() {
        return SpaceOrder;
    }

    public PrintInfo setSpaceOrder(String spaceOrder) {
        SpaceOrder = spaceOrder;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCompanyName);
        dest.writeString(mCustomerService);
        dest.writeString(mOrderNumber);
        dest.writeString(mGoodsSentTime);
        dest.writeString(mSender);
        dest.writeString(mSenderTel);
        dest.writeString(mGoodsName);
        dest.writeString(mFreight);
        dest.writeString(mCollectionFee);
        dest.writeString(mTotalFee);
        dest.writeString(mGoodsCount);
        dest.writeString(mSettlementWay);
        dest.writeString(mReceiver);
        dest.writeString(mReceiverTel);
        dest.writeString(mReceiverAddress);
        dest.writeString(mBranchAddress);
        dest.writeString(mBranchTel);
        dest.writeString(mSequence);
        dest.writeInt(mTime);
        dest.writeInt(mNeedPrintSequence);
        dest.writeString(mPBranchName);
        dest.writeString(mSBranchName);
        dest.writeString(mClerkName);
        dest.writeString(mPaperNumber);
        dest.writeString(mRemark);
        dest.writeString(WaybillCargoNo);
        dest.writeString(SendBranchContactTel);
        dest.writeString(DispatchBranchAddress);
        dest.writeString(DispatchBranchContactTel);
        dest.writeString(AdvanceTransitFee);
        dest.writeString(SpaceOrder);
        dest.writeString(PickUpWay);
        dest.writeString(CompanyUrl);
        dest.writeString(AdWords);
        dest.writeString(PayModeSuffix);
        dest.writeString(DeliveryFee);
    }

    public static final Creator<PrintInfo> CREATOR = new Creator<PrintInfo>() {

        @Override
        public PrintInfo createFromParcel(Parcel source) {
            PrintInfo printInfo = new PrintInfo();
            printInfo.mCompanyName = source.readString();
            printInfo.mCustomerService = source.readString();
            printInfo.mOrderNumber = source.readString();
            printInfo.mGoodsSentTime = source.readString();
            printInfo.mSender = source.readString();
            printInfo.mSenderTel = source.readString();
            printInfo.mGoodsName = source.readString();
            printInfo.mFreight = source.readString();
            printInfo.mCollectionFee = source.readString();
            printInfo.mTotalFee = source.readString();
            printInfo.mGoodsCount = source.readString();
            printInfo.mSettlementWay = source.readString();
            printInfo.mReceiver = source.readString();
            printInfo.mReceiverTel = source.readString();
            printInfo.mReceiverAddress = source.readString();
            printInfo.mBranchAddress = source.readString();
            printInfo.mBranchTel = source.readString();
            printInfo.mSequence = source.readString();
            printInfo.mTime = source.readInt();
            printInfo.mNeedPrintSequence = source.readInt();
            printInfo.mPBranchName = source.readString();
            printInfo.mSBranchName = source.readString();
            printInfo.mClerkName = source.readString();
            printInfo.mPaperNumber = source.readString();
            printInfo.mRemark = source.readString();
            printInfo.WaybillCargoNo = source.readString();
            printInfo.SendBranchContactTel = source.readString();
            printInfo.DispatchBranchAddress = source.readString();
            printInfo.DispatchBranchContactTel = source.readString();
            printInfo.AdvanceTransitFee = source.readString();
            printInfo.SpaceOrder = source.readString();
            printInfo.PickUpWay = source.readString();
            printInfo.CompanyUrl = source.readString();
            printInfo.AdWords = source.readString();
            printInfo.PayModeSuffix = source.readString();
            printInfo.DeliveryFee = source.readString();
            return printInfo;
        }

        @Override
        public PrintInfo[] newArray(int size) {
            return new PrintInfo[size];
        }
    };

    public String getSequence() {
        return mSequence;
    }

    public PrintInfo setSequence(String sequence) {
        mSequence = sequence;
        return this;
    }

    public PrintInfo() {

    }

    public PrintInfo(PrintOrders.ReturnDataBean order, String index) {

        setOrderNumber(order.getWaybillNo())
                .setGoodsSentTime(processSentTime(order.getConsignDate()))
                .setSender(order.getShipper())
                .setSenderTel(order.getDeliveryTel())
                .setGoodsName(order.getArticleName())
                .setFreight(processFee(order.getPickUpPayAmount()))
                .setGoodsCount(order.getTotalPieces() + "")
                .setCollectionFee(processFee(order.getCollectionTradeCharges()))
                .setSettlementWay(order.getSettleWay())
                .setTotalFee(processFee(order.getPickUpPayAmount() + order.getAdvanceTransitFee()))
                .setReceiver(order.getReceiver())
                .setReceiverTel(order.getReceiveTel())
                .setReceiverAddress(order.getReceiveAddress())
                .setSequence(index)
                .setPaperNumber(order.getPaperNumber())
                .setPBranchName(order.getDispatchBranchName())
                .setSBranchName(order.getSendBranchName())
                .setClerkName(order.getClerkName())
                .setRemark(order.getRemark())
                .setWaybillCargoNo(order.getWaybillCargoNo())
                .setSendBranchContactTel(order.getSendBranchContactTel())
                .setDispatchBranchAddress(order.getDispatchBranchAddress())
                .setDispatchBranchContactTel(order.getDispatchBranchContactTel())
                .setCompanyName(order.getCompanyShortName())
                .setCustomerService(order.getServiceTel())
                .setPickUpWay(order.getPickUpWay())
                .setSpaceOrder(getProcessOrder(order.getWaybillNo()))
                .setCompanyUrl(order.getCompanyUrl())
                .setAdWords(order.getAdWords())
                .setDeliveryFee(processFee(order.getDeliveryFee()))
                .setPayModeSuffix(analysePayMode(order,order.getSettleWay()))
                .setAdvanceTransitFee(processFee(order.getAdvanceTransitFee()));
    }

    private String analysePayMode(PrintOrders.ReturnDataBean order, String settleWay) {
        switch (settleWay) {
            case "提付":
                return "";
            case "月结":
                return "(" + processFee(order.getMonthPayAmount()) +  ")";

            case "现付":
                return "(" + processFee(order.getCashPayAmount()) +  ")";

            case "回付":
                return "(" + processFee(order.getReceiptPayAmount()) +  ")";

        }

        return "";
    }

    private String getProcessOrder(String waybillNo) {
        int length = waybillNo.length();
        return waybillNo.substring(0,length - 6) + " " + waybillNo.substring(length - 6);
    }

    public PrintInfo(OrderDetail.WaybillInfoBean order, String index) {
        setOrderNumber(order.getWaybillNo())
                .setGoodsSentTime(processSentTime(order.getOperationTime()))
                .setSender(order.getShipper())
                .setSenderTel(order.getDeliveryTel())
                .setGoodsName(order.getCargoName())
                .setFreight(processFee(order.getPickUpFee()))
                .setGoodsCount(order.getTotalNumber() + "")
                .setCollectionFee(processCllectionFee(order.getCollectionTradeCharges()))
                .setSettlementWay(order.getPayMode())
                .setTotalFee(processFee(order.getPickUpFee() + Float.parseFloat(order.getAdvanceTransitFee())))
                .setReceiver(order.getReceiver())
                .setReceiverTel(order.getReceiveTel())
                .setReceiverAddress(order.getReceiveAddress())
                .setSequence(index)
                .setPaperNumber(order.getPaperNumber())
                .setPBranchName(order.getDispatchBranchName())
                .setSBranchName(order.getSendBranchName())
                .setClerkName(order.getClerkName())
                .setRemark(order.getRemark())
                .setWaybillCargoNo(order.getWaybillCargoNo())
                .setSendBranchContactTel(order.getSendBranchContactTel())
                .setDispatchBranchAddress(order.getDispatchBranchAddress())
                .setDispatchBranchContactTel(order.getDispatchBranchContactTel())
                .setCompanyName(order.getCompanyShortName())
                .setCustomerService(order.getServiceTel())
                .setPickUpWay(order.getPickUpWay())
                .setSpaceOrder(getProcessOrder(order.getWaybillNo()))
                .setCompanyUrl(order.getCompanyUrl())
                .setAdWords(order.getAdWords())
                .setDeliveryFee(processFee(order.getDeliveryFee()))
                .setPayModeSuffix(analysePayMode(order,order.getPayMode()))
                .setAdvanceTransitFee(processFee(Float.parseFloat(order.getAdvanceTransitFee())));
    }

    private String analysePayMode(OrderDetail.WaybillInfoBean order, String settleWay) {
        switch (settleWay) {
            case "月结":
                return "(" + processFee(Float.parseFloat(order.getMonthAmount())) +  ")";

            case "现付":
                return "(" + processFee(Float.parseFloat(order.getCashAmount())) +  ")";

            case "回付":
                return "(" + processFee(Float.parseFloat(order.getReceiptPayAmount())) +  ")";

        }

        return "";
    }

    public String processCllectionFee(String fee) {
        if (TextUtils.isEmpty(fee.trim())) {
            return "0";
        }

        String result = sDecimalFormat.format(Float.parseFloat(fee));

        if (result.endsWith(".00")){
            return result.substring(0,result.length() - 3);
        }

        if (result.endsWith("0")){
            return result.substring(0,result.length() - 1);
        }

        return "0";
    }

    public static String processFee(float fee) {

        String result = sDecimalFormat.format(fee);

        if (result.endsWith(".00")){
            return result.substring(0,result.length() - 3);
        }

        if (result.endsWith("0")){
            return result.substring(0,result.length() - 1);
        }

        return "0";

    }

    public static String processFee(String feeStr) {
        if (TextUtils.isEmpty(feeStr)){
            return "0";
        }else {
            try {
                return processFee(Float.parseFloat(feeStr));
            }catch (NumberFormatException e){
                return "0";
            }

        }

    }

    public static java.text.DecimalFormat sDecimalFormat = new java.text.DecimalFormat("#0.00");

    public static String processSentTime(String time){

        if (TextUtils.isEmpty(time)){
            return "";
        }

        int index = time.indexOf(" ");
        return time.substring(0,index);
    }
}
