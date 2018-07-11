package com.liinji.ppgdeliver.configure;


/**
 * Created by YUEYINGSK on 2016/8/15.
 */
public class AppConstants {

    public static final String BLUETOOTHADDRESS = "bluetoothaddress";
    public static final String BLUETOOTHNAME = "bluetoothname";
    public static final String USER_TYPE = "1";
    public static final String MESSAGE_TYPE = "2";
    public static final String UPDATEISREAD = "updateisread";
    public static final String CUSTOMERBLUETOOTHNAME = "customerbluetoothname";
    public static final String CUSTOMERBLUETOOTHADDRESS = "customerbluetoothaddress";
    public static final String PRINTWHAT = "printwhat";
    public static final String CUSTOMER = "customer";
    public static final String WAYBILLING = "waybilling";
    public static final String CONNNECTERROR = "蓝牙连接失败,请重试";
    public static final String CREATEPAGEERROR = "创建打印页面失败";
    public static final String PRINTMESSAGE = "正在打印,请稍等...";
    public static final String PRINTCOMPLETE = "打印完成";
    public static final String PRINTCONTINUE = "printcontinue";
    public static final String PRINTONE = "printone";
    public static final String PRINTNUMBER = "printnumber";
    public static final String PRINTOVER = "printover";
    public static final String PRINTSTART = "printstart";
    public static final String GETBRANCHDATA = "getbranchdata";
    public static final String ISCONNECT = "连接蓝牙成功";
    public static final String NOTCONNECT = "连接蓝牙失败，请重试";
    public static final String TRYCONNECT = "正在尝试连接蓝牙打印设备，请稍等";

    /**
     * 密码加密密钥
     */
    public static final String SECRET_KEY = "#WMMP@ssword!qazXSW@(~A)1&3m0_s%";

    /**
     * 用户信息
     */
    public static final String USERPHONE = "UserPhone";
    public static final String USER_PSW = "user_psw";
    public static final String USERID = "UserId";
    public static final String USERNAME = "UserName";
    public static final String ISAUTHENTICATION = "IsAuthentication";
    public static final String HEADPORTRAIT = "HeadPortrait";
    public static final String CANSENDANDLOAD = "CanSendAndLoad";
    public static final String ISSENDANDLOAD = "IsSendAndLoad";
    public static final String GENDER = "Gender";
    public static final String BIRTHDAY = "BirthDay";
    public static final String TOKEN = "ToKen";

    public static final String USERTYPE = "1";
    /**
     * 　前后端统一定义正确值
     */
    public final static int TRUE = 1;
    /**
     * 下载id,版本号
     */
    public final static String DOWNLOADID = "downloadId";
    public final static String DOWNLOADVERSIONNAME = "downloadversionname";

    /**
     * 版本环境
     */
    public static class VersionEnvironment {
        /**
         * 用来存储此APK的版本环境
         */
        public static String NOW_ENVIROMENT;

        /**
         * 测试环境
         */
        public static final String TEST = "TEST";

        /**
         * 正式环境
         */
        public static final String RELEASE = "RELEASE";

        /**
         * 是否是测试
         */
        public static boolean IS_TEST;
    }

    /**
     * 获取验证码的action
     */
    public static class ConfirmCodeAction {
        public static final String register = "ZC";
        public static final String resetpsw = "CZ";
        public static final String findorder = "CX";
    }

    /**
     * 运费方式
     */
    public static class PayType {
        public static final String nowpay = "现付";
        public static final String getpay = "提付";
        public static final String monthpay = "月结";
    }

    /**
     * 证件类型
     */
    public static class CardType {
        public static final int IDCARD = 1;
    }

    /**
     * 请求码
     */
    public static final int NORMAL_REQUESTCODE = 001;
    public static final int REQUESTCODE_IV1 = 002;
    public static final int REQUESTCODE_IV2 = 003;
    public static final int REQUEST_CONTACT = 004;

    /**
     * 传递数据
     */
    public static final String INTENTDATA_OK = "intentdata_ok";

    /**
     * 运单icon
     *
     * @param waybilltype
     * @param transpersontype
     * @return
     */
//    public static int getHomeorderIcon(int waybilltype, int transpersontype) {
//        if (transpersontype == 2) {
//            return R.mipmap.waybill_put;
//        } else {
//            if (waybilltype == 0) {
//                return R.mipmap.waybill_yu;
//            } else {
//                return R.mipmap.waybill_deliver;
//            }
//        }
//    }

    /**
     * 获取运单状态背景
     *
     * @param waybillstatus
     * @return
     */
//    public static int getOrderStatusBack(int waybillstatus) {
//        if (waybillstatus == 0) {
//            return R.drawable.rectangle_orange;
//        } else if (waybillstatus == 1 || waybillstatus == 2 || waybillstatus == 3 || waybillstatus == 4) {
//            return R.drawable.rectangle_green;
//        } else {
//            return R.drawable.rectangle_grey;
//        }
//    }

    /**
     * 收发货人类型
     */
    public static class ShiperAndReceiverType {
        public static final int SHIPER = 1;
        public static final int RECEIVER = 2;
    }
}
