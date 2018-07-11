package com.liinji.ppgdeliver.http;

import com.liinji.ppgdeliver.BuildConfig;

public class HttpUrl {


    public static final String BASE_URL = BuildConfig.API_BASE_URL;


    public static final String LOGIN_URL = BASE_URL + "/api/StuffService/StuffLogin";

    public static final String WAIT_DEBIT_COUNT = BASE_URL + "/api/DeliveryService/GetDistributorUnpaidDebtNumber";

    public static final String ORDER_COMPLETE = BASE_URL + "/api/DeliveryService/GetDeliverStock";

    public static final String WAIT_SENDED_LIST = BASE_URL + "/api/DeliveryService/GetDeliverStock";

    public static final String APPLY_SUBMIT_BILL = BASE_URL + "/api/DeliveryService/SendSettlememtRequest";

    public static final String DEBIT_LIST = BASE_URL + "/api/DeliveryService/GetUnrepayWaybillList";

    public static final String ORDER_TRACE_INFO = BASE_URL + "/api/WaybillService/GetWaybillDetailByWaybillNo";

    public static final String CHANGE_PASSWORD = BASE_URL + "/api/StuffService/ChangeStuffPassword";

    public static final String REAL_CASH_PAY_AMOUNT = BASE_URL + "/api/DeliveryService/GetWaybillWithPaymentByWaybillNo";

    public static final String CASH_PAY = BASE_URL + "/api/WaybillSettlementService/ProcessWaybillPayment_Deliver";

    public static final String IS_MY_ORDER = BASE_URL + "/api/DeliveryService/CheckStuffWaybillRelationship";

    public static final String HOW_MANY_TO_SUMIT = BASE_URL + "/api/DeliveryService/GetStuffSettlementAmount";

    public static final String ORDER_DETAIL = BASE_URL + "/api/DeliveryService/GetWaybillWithPaymentByWaybillNo";

    public static final String BILL_RECORD = BASE_URL + "/api/DeliveryService/GetSettlememtRequestList";

    public static final String SUGGEST_MESSAGE = BASE_URL + "/api/MessageService/SubmitSugesstion";

    public static final String REPORT = BASE_URL + "/api/DeliveryService/GetTotalAmountSumByDeliveryId";



    public static final String VERSION_UPDATE = BASE_URL + "/api/ResourceService/GetAppVersionV1";

    public static final String HAS_UNREAD_MSG = BASE_URL + "/api/MessageService/GetMessageSummaryByUser";

    public static final String MSG_LIST = BASE_URL + "/api/MessageService/GetMessageListByUser";

    public static final String MSG_DETAIL = BASE_URL + "/api/MessageService/GetMessageDetail";

    public static final String IGNORE_UNREAD_MSG = BASE_URL + "/api/MessageService/SetMessageReadByUser";

    public static final String GET_BRANCH = BASE_URL + "/api/CommonService/GetCompanyBranche";

    public static final String GET_STATE = BASE_URL + "/api/CommonService/GetCompanyProblemStatus";

    public static final String UPLOAD_PROBLEM_IMG = BASE_URL + "/api/ResourceService/UploadProblemWaybillImage";

    public static final String SUBMIT_PROBLEM_INFO = BASE_URL + "/api/WaybillService/CreateProblemWaybill";

    public static final String GET_PROBLEM_ORDER_LIST = BASE_URL + "/api/WaybillService/GetRegistedProblemPageList";

    public static final String GET_PROCESS_LIST = BASE_URL + "/api/WaybillService/GetReplyProblemWaybillList";


    public static final String USER_CONTRACT = "http://liinji.uicp.cn:24002/Html/ServerProtocol.html";

    public static final String GET_NORMAL_QIANKUAN = BASE_URL + "/api/DeliveryService/GetUnpaidCargoPaymentWaybillList";

    public static final String DAISHOU_DEBIT_DETAIL = BASE_URL + "/api/DeliveryService/GetUnpaidCargoFeeWaybill";


    public static final String DAISHOU_PAY = BASE_URL + "/api/DeliveryService/PayUnpaidCollectAmount";

    public static final String GET_REPORT = BASE_URL + "/api/DeliveryService/GetPaymentSummaryByPayTypeStuff";


    public static final String GET_BILL_ORDER_LIST = BASE_URL + "/api/DeliveryService/GetWaybillListByCourierSettleRequest";


    public static final String IS_DEFAULT_DISPATCHER = BASE_URL + "/api/DeliveryService/IsDefaultDispatcher";

    public static final String GET_BUDDY = BASE_URL + "/api/DeliveryService/GetDistributeMember";

    public static final String TO_BUDDY = BASE_URL + "/api/DeliveryService/RedistributeMember";

    public static final String DEBIT_CONFIG = BASE_URL + "/api/CommonService/GetDebtConfig";

    public static final String GET_PRINT_COMPANY_INFO = BASE_URL + "/api/DeliveryService/GetDeliveryClerkPrintCompanyInfo";

    public static final String GET_REDISPATCH_ORDER_LIST = BASE_URL + "/api/DeliveryService/GetReDistributeWaybill";

    public static final String GET_SEARCHED_LIST = BASE_URL + "/api/DeliveryService/GetWaybillByPartialNo";

    public static final String GET_AUTHORITY = BASE_URL + "/api/DeliveryService/GetDeliveryClerkAuthority";

    public static final String REDUCE_YUNFEE = BASE_URL + "/api/DeliveryService/ReduceFreight";

    public static final String REDUCE_DAISHOU = BASE_URL + "/api/DeliveryService/ReducePayment";

    public static final String GET_COMPLETE_OUTLINE = BASE_URL + "/api/DeliveryService/GetDeliveryClerkCompletedReport";

    public static final String UPDATE_SIGN_REMARKS = BASE_URL + "/api/WaybillSettlementService/ModifySignRemark";

    public static final String QUICK_DISPATCH = BASE_URL + "/api/DeliveryService/ProcessReceiverCourierXref";

    public static final String GET_SEND_REPORT = BASE_URL + "/api/DeliveryService/GetDistributeAllSummary";

    public static final String GET_WAITED_ORDER_LIST = BASE_URL + "/api/DeliveryService/GetDeliverWaitForDistribute";

    public static final String GET_PRINT_ORDER_LIST = BASE_URL + "/api/DeliveryService/GetDeliverStockForPrint";

    public static final String GET_TRIP_NO = BASE_URL + "/api/DeliveryService/GetTripNoListByDate";

    public static final String UPDATE_TRANSFER_FEE = BASE_URL + "/api/DeliveryService/RegisterTransferInfo";

    public static final String UPLOAD_SIGN_PIC = BASE_URL + "/api/WaybillSettlementService/PushWaybillSignImage";

    public static final String DELETE_PIC = BASE_URL + "/api/WaybillSettlementService/RemoveWaybillSignImage";

    public static final String RETRIEVE_ORDER_PIC = BASE_URL + "/api/WaybillSettlementService/GetWaybillSignImage";

}
