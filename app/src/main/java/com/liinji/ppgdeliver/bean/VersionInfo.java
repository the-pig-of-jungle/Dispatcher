package com.liinji.ppgdeliver.bean;

/**
 * Created by YUEYINGSK on 2016/9/6.
 */
public class VersionInfo {

    /**
     * DownLoadUrl : http://m.dahanis.com:24088/AppFolders/20150807/youkutudou_pd_v1.ipa
     * VersionInfo :
     * ForceUpdate : 0
     * VersionId : 5
     * IsUpdate : 2
     * VersionName : 发货人
     */
    private String DownLoadUrl;
    private String VersionInfo;
    private int ForceUpdate;
    private String VersionId;
    private int IsUpdate;
    private String VersionName;

    public void setDownLoadUrl(String DownLoadUrl) {
        this.DownLoadUrl = DownLoadUrl;
    }

    public void setVersionInfo(String VersionInfo) {
        this.VersionInfo = VersionInfo;
    }

    public void setForceUpdate(int ForceUpdate) {
        this.ForceUpdate = ForceUpdate;
    }

    public void setVersionId(String VersionId) {
        this.VersionId = VersionId;
    }

    public void setIsUpdate(int IsUpdate) {
        this.IsUpdate = IsUpdate;
    }

    public void setVersionName(String VersionName) {
        this.VersionName = VersionName;
    }

    public String getDownLoadUrl() {
        return DownLoadUrl;
    }

    public String getVersionInfo() {
        return VersionInfo;
    }

    public int getForceUpdate() {
        return ForceUpdate;
    }

    public String getVersionId() {
        return VersionId;
    }

    public int getIsUpdate() {
        return IsUpdate;
    }

    public String getVersionName() {
        return VersionName;
    }
}
