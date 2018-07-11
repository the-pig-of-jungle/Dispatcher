package com.liinji.ppgdeliver.http;


import com.liinji.ppgdeliver.bean.ProblemOrder;
import com.liinji.ppgdeliver.bean.UserInfo;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

public class HttpParams {
    //     获取验证码
    public static final String Phone = "Phone";
    public static final String Action = "Action";
    //    用户注册
    public static final String VerificationCode = "VerificationCode";
    public static final String Password = "Password";
    //用户登录
    public static final String IMEI = "IMEI";
    public static final String LoginName = "LoginName";
    //修改密码
    public static final String NewPassword = "NewPassword";
    //修改认证信息和个人资料
    public static final String UserCertification = "UserCertification";
    public static final String UserInfo = "UserInfo";
    public static final String UserId = "UserId";
    public static final String Token = "Token";
    public static final String Sign = "Sign";
    public static final String FileStream = "FileStream";
    //获取省市区
    public static final String ProvinceCode = "ProvinceCode";
    public static final String CityCode = "CityCode";
    //获取信息
    public static final String UserType = "UserType";
    //获取列表
    public static final String PhoneNumber = "PhoneNumber";
    public static final String PageIndex = "PageIndex";
    public static final String PageSize = "PageSize";
    public static final String SearchData = "SearchData";
    //获取运单详情
    public static final String WaybillNo = "WaybillNo";
    //保存发货人信息
    public static final String JsonData = "JsonData";
    //删除发货人信息
    public static final String Id = "Id";

    public static final String LOGIN_ACCOUNT = "LoginAccount";
    public static final String PASSWORD = "Password";
    public static final String USER_TYPE = "UserType";

    public static final String STAFF_ID = "staffId";
    public static final String STATUS = "status";
    public static final String COMPANY_CODE = "companyCode";
    public static final String BRANCH_CODE = "branchCode";
    public static final String USER_ID = "userId";
    public static final String TOKEN = "token";
    public static final String SIGN = "sign";
    public static final String PAGE_NO = "pageIndex";
    public static final String PAGE_NUM = "pageSize";
    private static final String AMOUNT = "amount";
    private static final String PHONE = "phone";
    private static final String DEVICE_ID = "deviceId";
    private static final String CATEGORY_CODE = "categoryCode";
    private static final String APP_VERSION_ID = "appVersionId";
    private static final String MESSAGE_TYPE = "messageType";
    private static final String PLATFORM = "platForm";


    public static RequestParams setGetTripNosList(String searchDate){
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(STATUS, "1");
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(PAGE_NO, "1");
        requestParams.put(PAGE_NUM, "100");
        requestParams.put("searchDate", searchDate);

        return requestParams;
    }


    public static RequestParams setWaitSendedParams(String keywords, String pageNo, String pageSize, String startDate, String endDate, String tripNo, String sequence) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(STATUS, "1");
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(PAGE_NO, pageNo);
        requestParams.put(PAGE_NUM, pageSize);
        requestParams.put("startDate", startDate);
        requestParams.put("endDate", endDate);
        requestParams.put("keywords", keywords);
        requestParams.put("tripNo", tripNo);
        requestParams.put("orderBy",sequence);
        return requestParams;
    }

    public static RequestParams setDebiyParams(String keywords) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put("keywords", keywords);
        return requestParams;
    }

    public static RequestParams setWaitDebitParams() {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(STATUS, "2");
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(PAGE_NO, "0");
        requestParams.put(PAGE_NUM, "0");
        return requestParams;
    }

    public static RequestParams setCompleteOrderParams(String pageNo, String pageSize, String beginDate, String endDate, String keywords) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());

        requestParams.put("startDate", beginDate);
        requestParams.put("endDate", endDate);
        requestParams.put("keywords",keywords);
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(PAGE_NO, pageNo);
        requestParams.put(PAGE_NUM, pageSize);
        return requestParams;
    }

    public static RequestParams setApplyBillParams(String amount) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(AMOUNT, amount);
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    public static final String WAYBILL_NO = "waybillNo";
    public static final String FINDER_PHONE = "phone";

    public static RequestParams setTraceInfoParams(String order) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(WAYBILL_NO, order);
        requestParams.put(FINDER_PHONE, userInfo.getPhone());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());

        return requestParams;
    }

    public static RequestParams setChangePasswordParams(String oldPwd, String newPwd) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("UserId", userInfo.getUserId());
        requestParams.put("Password", oldPwd);
        requestParams.put("NewPassword", newPwd);
        requestParams.put("LoginAccount", userInfo.getLoginName());
        requestParams.put("UserType", "3");

        return requestParams;
    }


    public static RequestParams setRealCashPayParams(String order) {

        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("waybillNo", order);
        requestParams.put("phone", userInfo.getPhone());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());

        return requestParams;
    }

    public static final String SETTLEMENT_REQUEST_STATUS_ID = "settlememtRequestStatusId";

    public static RequestParams setBillRecordParams(String pageNo, String pageSize) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("staffId", userInfo.getUserId());
        requestParams.put(SETTLEMENT_REQUEST_STATUS_ID, 0 + "");
        requestParams.put(PAGE_NO, pageNo);
        requestParams.put(PAGE_NUM, pageSize);
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        Logger.d(requestParams.getParams());
        return requestParams;
    }

    /*
    Parameters
Parameter	Value	Description	Parameter Type	Data Type
request.staffID
35
query	integer
request.companyCode
001
公司编号
query	string
request.branchCode
029
网点编号
query	string
request.userId
35
用户ID
query	integer
request.token
814712d6-a517-443d-a141-fff4bee89684
token
query	string
request.sign
sign
query	string
     */
    public static RequestParams setHowManyToSubmitParams() {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    /*
    {
  "StaffID": 41,
  "WaybillNo": "88888886",
  "Paymethod": 3,
  "Amount": 0,
  "Signer": "string",
  "Password": "",
  "CompanyCode": userInfo.getCompanyCode(),
  "BranchCode": userInfo.getBranchCode(),
  "UserId": 15,
  "Token": "13e62b87-4105-43ba-87cd-c9935ad57513",
  "Sign": ""
}
     */
    public static RequestParams setCashPayParams(String waybillNo, String signer, String signRemarks, float amount, int payWay) {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("StaffID", userInfo.getUserId());
        requestParams.put("WaybillNo", waybillNo);
        requestParams.put("Paymethod", "3");
        requestParams.put("Amount", String.valueOf(amount));
        requestParams.put("Signer", signer);
        requestParams.put("Password", "");
        requestParams.put("PayWay", payWay + "");
        requestParams.put("CompanyCode", userInfo.getCompanyCode());
        requestParams.put("BranchCode", userInfo.getBranchCode());
        requestParams.put("UserId", userInfo.getUserId());
        requestParams.put("Token", userInfo.getToken());
        requestParams.put("SignRemark", signRemarks);
        requestParams.put("Sign", userInfo.getSign());

        return requestParams;
    }

    /*
    {
  "UserId": 0,
  "NewPassword": "string",
  "LoginAccount": "string",
  "Password": "string",
  "UserType": 0
}
     */

    public static RequestParams setChangePwdParams(String oldPwd, String newPwd) {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("UserType", "3");
        requestParams.put("Password", oldPwd);
        requestParams.put("NewPassword", newPwd);
        requestParams.put("LoginAccount", userInfo.getLoginName());
        requestParams.put("UserId", userInfo.getUserId());


        return requestParams;
    }


    public static RequestParams setIsMyOrderParams(String order) {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put(WAYBILL_NO, order);
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    public static RequestParams setOrderDetail(String order) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(WAYBILL_NO, order);
        requestParams.put(PHONE, userInfo.getPhone());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }


    public static final String SUGGESTION = "Suggestion";

    public static RequestParams setSuggestionParams(String suggestion) {

        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(SUGGESTION, suggestion);
        requestParams.put("UserId", userInfo.getUserId());
        requestParams.put("Token", userInfo.getToken());
        requestParams.put("Sign", userInfo.getSign());
        return requestParams;

    }


    public static final String DATE_FROM = "dtFrom";
    public static final String DATE_TO = "dtTo";

    public static RequestParams setReportParams(String dateFrom, String dateTo) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(DATE_FROM, dateFrom);
        requestParams.put(DATE_TO, dateTo);
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    public static RequestParams setVersionUpdateParams() {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put(DEVICE_ID, "1");
        requestParams.put(CATEGORY_CODE, "PS");
        requestParams.put(APP_VERSION_ID, Utils.appVersionCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    public static RequestParams setHasUnreadMsgParams() {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("platForm", 3 + "");
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());

        return requestParams;
    }

    public static RequestParams setMsgListParams(int pageNo, int pageNum) {
        UserInfo userInfo = SharePrefUtils.getUserInfo();

        RequestParams requestParams = new RequestParams();
        requestParams.put(MESSAGE_TYPE, 0 + "");
        requestParams.put(PAGE_NO, pageNo + "");
        requestParams.put(PAGE_NUM, pageNum + "");
        requestParams.put(PLATFORM, 3 + "");
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());

        return requestParams;
    }


    public static RequestParams setMsgDetailParams(int msgId) {
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("messageId", msgId + "");
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    /*
    {
  "PlatForm": 0,
  "UserId": 0,
  "Token": "string",
  "Sign": "string"
}
     */

    public static RequestParams setIgnoreUnreadMsgParams() {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("PlatForm", "3");
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    public static RequestParams setGetBranchParams() {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }

    public static RequestParams setGetStateParams() {
        return setGetBranchParams();
    }

    public static RequestParams setUploadProblemImgParams(String fileStream, String waybillNo) {
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("FileStream", fileStream);
        requestParams.put("WaybillNo", waybillNo);
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }


    public static RequestParams setSubmitProblemInfoParams(ProblemOrder problemOrder) {
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        problemOrder.setCompanyCode(userInfo.getCompanyCode());
        problemOrder.setRegisterBranchCode(userInfo.getBranchCode());
        problemOrder.setRegisterUserId(userInfo.getUserId());
        problemOrder.setRegisterBranchName(userInfo.getBranchName());
        problemOrder.setRegisterUserName(userInfo.getUserName());
        RequestParams requestParams = new RequestParams();
        requestParams.put("RequestJsonData", JsonUtils.jsonStr(problemOrder));
        requestParams.put("UserId", userInfo.getUserId());
        requestParams.put("Token", userInfo.getToken());
        requestParams.put("Sign", userInfo.getSign());
        return requestParams;
    }

    public static RequestParams setGetProblemOrderListParams(int pageNo, int pageSize) {

        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();

        requestParams.put("companyCode", userInfo.getCompanyCode());
        requestParams.put("registeBranchCode", userInfo.getBranchCode());
        requestParams.put("pageIndex", pageNo + "");
        requestParams.put("pageSize", pageSize + "");
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());

        return requestParams;
    }

    public static RequestParams setProcessListParams(String problemId) {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("problemId", problemId);
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        return requestParams;
    }


    public static RequestParams setNormalQiankuanParams(int pageNo, int pageSize, String keywords) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();

        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(PAGE_NO, pageNo + "");
        requestParams.put(PAGE_NUM, pageSize + "");
        requestParams.put("keywords", keywords);
        return requestParams;
    }


    public static RequestParams setDaishouDebitDetailParams(String orderId) {
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();

        requestParams.put("waybillId", orderId);
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }


    public static RequestParams setDaishouPayParams(String orderId, String collectAmount, String yunFeeAmount) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("WaybillId", orderId);
        requestParams.put("CollectAmount", collectAmount);
        requestParams.put("FreightAmount", yunFeeAmount);
        requestParams.put("UserName", userInfo.getUserName());
        requestParams.put("PayMode", "3");
        requestParams.put("CompanyCode", userInfo.getCompanyCode());
        requestParams.put("BranchCode", userInfo.getBranchCode());
        requestParams.put("UserId", userInfo.getUserId());
        requestParams.put("Token", userInfo.getToken());
        requestParams.put("Sign", userInfo.getSign());
        return requestParams;
    }


    public static RequestParams setReportParams(String startDate, String endDate, int payType, int pageNo, int pageSize, String signer) {

        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(PAGE_NO, pageNo + "");
        requestParams.put(PAGE_NUM, pageSize + "");
        requestParams.put("PayType", payType + "");
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put("startDate", startDate);
        requestParams.put("endDate", endDate);
        requestParams.put("signer",signer);
        return requestParams;

    }

    public static RequestParams setBillOrderListParams(int billId, int pageNo, int pageSize) {
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put("courierSettleRequestId", billId + "");
        requestParams.put("stuffId", userInfo.getUserId());
        requestParams.put(PAGE_NO, pageNo + "");
        requestParams.put(PAGE_NUM, pageSize + "");
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }


    public static RequestParams setIsDefaultDispatcherParams() {
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }

    public static RequestParams setGetBuddyParam() {
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }

    public static RequestParams setDebitConfigParam() {
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }

    public static RequestParams setPrintCompanyParam() {
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }


    public static RequestParams setRedispatchOrderListParams(String keywords, String pageNo, String pageSize) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(STAFF_ID, userInfo.getUserId());
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(TOKEN, userInfo.getToken());
        requestParams.put(SIGN, userInfo.getSign());
        requestParams.put(PAGE_NO, pageNo);
        requestParams.put(PAGE_NUM, pageSize);
        requestParams.put("keywords", keywords);
        return requestParams;
    }


    public static RequestParams setSearchedListParams(String keywords) {
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put("keywords", keywords);
        return requestParams;
    }

    public static RequestParams setAuthorityParams() {
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo info = SharePrefUtils.getUserInfo();
        requestParams.put(USER_ID, info.getUserId());
        return requestParams;
    }

    /*
    {
  "CompanyCode": "string",
  "BranchCode": "string",
  "WaybillId": "string",
  "ChangeReason": "string",
  "ReduceAmount": 0,
  "UserId": 0,
  "Token": "string",
  "Sign": "string"
}
     */
    public static RequestParams setReduceYunfeeParams(String orderId, float amount) {
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put("WaybillId", orderId);
        requestParams.put("ChangeReason", "");
        requestParams.put("ReduceAmount", String.valueOf(amount));
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }

    public static RequestParams setReduceDaishParams(String orderId, float amount) {
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        RequestParams requestParams = new RequestParams();
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put("WaybillId", orderId);
        requestParams.put("ChangeReason", "");
        requestParams.put("ReduceAmount", String.valueOf(amount));
        requestParams.put(USER_ID, userInfo.getUserId());
        return requestParams;
    }


    public static RequestParams setCompleteOutlineParams(String beginDate, String endDate, String keywords) {
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put(USER_ID, userInfo.getUserId());
        requestParams.put(COMPANY_CODE, userInfo.getCompanyCode());
        requestParams.put(BRANCH_CODE, userInfo.getBranchCode());
        requestParams.put("startDate", beginDate);
        requestParams.put("endDate", endDate);
        requestParams.put("keywords",keywords);
        return requestParams;
    }


    public static RequestParams setUpdateSignRemarksParams(String waybillId, String signRemarks) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("WaybillId",waybillId);
        requestParams.put("SignRemark",signRemarks);
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("UserId",userInfo.getUserId());
        return requestParams;
    }

    /*
    {
  "StaffID": 0,
  "WaybillList": "string",
  "CompanyCode": "string",
  "BranchCode": "string",
  "UserId": 0,
  "Token": "string",
  "Sign": "string"
}
     */

    public static RequestParams setQuickDispatchParams(String orderList){
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("StaffID",userInfo.getUserId());
        requestParams.put("CompanyCode",userInfo.getCompanyCode());
        requestParams.put("BranchCode",userInfo.getBranchCode());
        requestParams.put("UserId",userInfo.getUserId());
        requestParams.put("WaybillList",orderList);
        return requestParams;
    }


    public static RequestParams setGetSendReportParams(String startDate, String endDate, int pageNo, int pageSize, int senderId, int isDefaultSender, int ifConsign){
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("companyCode",userInfo.getCompanyCode());
        requestParams.put("branchCode",userInfo.getBranchCode());
        requestParams.put("startDate",startDate);
        requestParams.put("endDate",endDate);
        requestParams.put("pageIndex",pageNo + "");
        requestParams.put("pageSize",pageSize + "");
        requestParams.put("searchUserId",senderId + "");
        requestParams.put("isDefault",isDefaultSender + "");
        requestParams.put("isSigned",ifConsign + "");
        requestParams.put("userId",userInfo.getUserId());
        return requestParams;
    }

    public static RequestParams setWaitedOrderListParams(String keywords, int pageIndex, int pageSize){
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("keywords",keywords);
        requestParams.put("userId",userInfo.getUserId());
        requestParams.put("branchCode",userInfo.getBranchCode());
        requestParams.put("companyCode",userInfo.getCompanyCode());
        requestParams.put("pageIndex",pageIndex + "");
        requestParams.put("pageSize",pageSize + "");
        requestParams.put("staffID",userInfo.getUserId());
        return requestParams;
    }

    public static RequestParams setPrintOrderListParams(String startDate, String endDate, String tripNo,
                                                        String keywords, String orderBy, int pageIndex, int pageSize){
        RequestParams requestParams = new RequestParams();
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("startDate",startDate);
        requestParams.put("endDate",endDate);
        requestParams.put("tripNo",tripNo);
        requestParams.put("keywords",keywords);
        requestParams.put("orderBy",orderBy);
        requestParams.put("userId",userInfo.getUserId());
        requestParams.put("staffId",userInfo.getUserId());
        requestParams.put("companyCode",userInfo.getCompanyCode());
        requestParams.put("branchCode",userInfo.getBranchCode());
        requestParams.put("pageIndex",pageIndex + "");
        requestParams.put("pageSize",pageSize + "");
        requestParams.put("status","1");
        return requestParams;
    }


    /*
    {
  "WaybillNo": "string",
  "TransferAmount": 0,
  "TransferCompanyName": "string",
  "TransferNo": "string",
  "UserName": "string",
  "CompanyCode": "string",
  "BranchCode": "string",
  "UserId": 0,
  "Token": "string",
  "Sign": "string"
}
     */

    public static RequestParams setUpdateTransferFeeParams(String waybillNo, String transferAmount, String transferCompanyName, String transferNo){
        RequestParams requestParams = new RequestParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("WaybillNo",waybillNo);
        requestParams.put("TransferAmount",transferAmount);
        requestParams.put("TransferNo",transferNo);
        requestParams.put("transferCompanyName",transferCompanyName);
        requestParams.put("UserName",userInfo.getUserName());
        requestParams.put("UserId",userInfo.getUserId());
        requestParams.put("CompanyCode",userInfo.getCompanyCode());
        requestParams.put("BranchCode",userInfo.getBranchCode());
        return requestParams;
    }



    public static RequestParams setRetrieveOrderPicParams(String waybillNo){
        RequestParams requestParams = new RequestParams();
        requestParams.put("waybillNo",waybillNo);
        com.liinji.ppgdeliver.bean.UserInfo userInfo = SharePrefUtils.getUserInfo();
        requestParams.put("userId",userInfo.getUserId());
        requestParams.put("branchCode",userInfo.getBranchCode());
        return requestParams;
    }
}
