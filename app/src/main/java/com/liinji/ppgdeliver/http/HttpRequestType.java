package com.liinji.ppgdeliver.http;

/**
 * 该类作用：
 * 用于在回调时区别调用了请求的类别
 *
 * @author Lee
 */
public class HttpRequestType {
    /**
     * 成功获取数据返回码
     */
    public static final String SUCCESS_CODE = "1";

    public static final int LOGIN_REQUEST = 0x001;

    public static final int WAIT_SENDED_MARK = 0x002;

    public static final int COMPLETE_ORDER_LIST = 0x003;

    public static final int WAIT_SENDED_LIST = 0x004;

    public static final int APPLY_SUBMIT_BILL = 0x005;

    public static final int DEBIT_LIST = 0x006;

    public static final int EEBIT_MARK = 0x007;


    public static final int ORDER_TRACE_INFO = 0x008;

    public static final int CHANGE_PASSWORD = 0x009;

    public static final int REAL_CASH_PAY_AMOUNT = 0x00a;

    public static final int CASH_PAY = 0x00b;

    public static final int CHANGE_PWD = 0x00c;

    public static final int BILL_RECORD = 0x00d;

    public static final int IS_MY_ORDER = 0x00e;

    public static final int HOW_MANY_TO_SUBMIT = 0x00f;

    public static final int ORDER_DETAIL = 0x010;

//    public static final int CUT_DELIVER = 0x011;

    public static final int SUGGESTION_MESSAGE = 0x012;

    public static final int REPORT = 0x013;

    public static final int VERSION_UPDATE = 0x014;


    public static final int HAS_UNREAD_MSG = 0x015;

    public static final int MSG_LIST = 0x016;

    public static final int MSG_DETAIL = 0x017;

    public static final int IGNORE_UNREAD_MSG = 0x018;

    public static final int GET_BRANCH = 0x019;

    public static final int GET_STATE = 0x020;

    public static final int UPLOAD_PROBLEM_IMG = 0x021;

    public static final int SUBMIT_PROBLEM_INFO = 0x022;

    public static final int GET_PROBLEM_ORDER_LIST = 0x023;

    public static final int GET_PROCESS_LIST = 0x024;


    public static final int GET_NORMAL_QIANKUAN = 0x025;

    public static final int DAISHOU_DEBIT_DETAIL = 0x026;

    public static final int DAISHOU_PAY = 0x027;

    public static final int GET_REPORT = 0x028;

    public static final int getBillOrderList = 0x029;

    public static final int IS_DEFAULT_DISPATCHER = 0x030;

    public static final int GET_BUDDY = 0x031;

    public static final int TO_BUDDY = 0x032;

    public static final int DEBIT_CONFIG = 0x033;

    public static final int GET_PRINT_COMPANY_INFO = 0x034;

    public static final int GET_REDISPATCH_ORDER_LIST = 0x035;

    public static final int GET_SEARCHED_LIST =     0x036;

    public static final int GET_AUTHORITY = 0x037;

    public static final int REDUCE_YUNFEE = 0x038;
    public static final int REDUCE_DAISHOU = 0x039;

    public static final int GET_COMPLETER_OUTLINE = 0x040;

    public static final int GET_TRIP_NO = 0x041;

    public static final int UPDATE_SIGN_REMARKS = 0x042;

    public static final int QUICK_DISPATCH = 0x043;

    public static final int GET_SEND_REPORT = 0x044;

    public static final int GET_WAITED_ORDER_LIST = 0x045;

    public static final int GET_PRINT_ORDER_LIST = 0x046;

    public static final int UPDATE_TRANSFER_FEE = 0x047;

    public static final int UPLOAD_SIGN_PIC = 0x048;

    public static final int DELETE_PIC = 0x049;

    public static final int GET_ORDER_PIC = 0x050;

}
