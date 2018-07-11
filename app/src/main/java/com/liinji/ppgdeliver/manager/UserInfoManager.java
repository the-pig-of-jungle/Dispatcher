package com.liinji.ppgdeliver.manager;


import com.liinji.ppgdeliver.bean.UserInfo;

/**
 * Created by YUEYINGSK on 2016/8/26.
 */
public class UserInfoManager {

    private static UserInfo userInfo;

    public static void init() {
//        userInfo = new UserInfo();
//        userInfo.setUserPhone(SharedPreManager.getString(AppConstants.USERPHONE));
//        userInfo.setPassword(SharedPreManager.getString(AppConstants.USER_PSW));
//        userInfo.setUserId(SharedPreManager.getInt(AppConstants.USERID));
//        userInfo.setUserName(SharedPreManager.getString(AppConstants.USERNAME));
//        userInfo.setIsAuthentication(SharedPreManager.getInt(AppConstants.ISAUTHENTICATION));
//        userInfo.setHeadPortrait(SharedPreManager.getString(AppConstants.HEADPORTRAIT));
//        userInfo.setCanSendAndLoad(SharedPreManager.getInt(AppConstants.CANSENDANDLOAD));
//        userInfo.setIsSendAndLoad(SharedPreManager.getInt(AppConstants.ISSENDANDLOAD));
//        userInfo.setGender(SharedPreManager.getInt(AppConstants.GENDER));
//        userInfo.setBirthDay(SharedPreManager.getString(AppConstants.BIRTHDAY));
//        userInfo.setToKen(SharedPreManager.getString(AppConstants.TOKEN));
    }

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo info) {
        if (info != null) {
            userInfo = info;
//            SharedPreManager.putString(AppConstants.USERPHONE, userInfo.getUserPhone());
//            SharedPreManager.putString(AppConstants.USER_PSW, userInfo.getPassword());
//            SharedPreManager.putInt(AppConstants.USERID, userInfo.getUserId());
//            SharedPreManager.putString(AppConstants.USERNAME, userInfo.getUserName());
//            SharedPreManager.putInt(AppConstants.ISAUTHENTICATION, userInfo.getIsAuthentication());
//            SharedPreManager.putString(AppConstants.HEADPORTRAIT, userInfo.getHeadPortrait());
//            SharedPreManager.putInt(AppConstants.CANSENDANDLOAD, userInfo.getCanSendAndLoad());
//            SharedPreManager.putInt(AppConstants.ISSENDANDLOAD, userInfo.getIsSendAndLoad());
//            SharedPreManager.putInt(AppConstants.GENDER, userInfo.getGender());
//            SharedPreManager.putString(AppConstants.BIRTHDAY, userInfo.getBirthDay());
//            SharedPreManager.putString(AppConstants.TOKEN, userInfo.getToKen());
        }
    }
}
