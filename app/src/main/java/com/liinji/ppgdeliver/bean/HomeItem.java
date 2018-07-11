package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/9.
 */
public class HomeItem {

    private int mIconRes;
    private int mBadgeNum;
    private String mLabel;

    public HomeItem() {

    }

    public HomeItem(int iconRes, int badgeNum, String label) {
        mIconRes = iconRes;
        mBadgeNum = badgeNum;
        mLabel = label;
    }

    public int getIconRes() {
        return mIconRes;
    }

    public void setIconRes(int iconRes) {
        mIconRes = iconRes;
    }

    public int getBadgeNum() {
        return mBadgeNum;
    }

    public void setBadgeNum(int badgeNum) {
        mBadgeNum = badgeNum;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

}
