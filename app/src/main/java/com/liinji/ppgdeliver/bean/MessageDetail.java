package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/5/4.
 */

public class MessageDetail {

    /**
     * Id : 121
     * Title : 积分活动上线啦
     * PrimaryContent : 参与积分活动，赢大奖
     * Content : 积分活动：
     积分兑换：
     * ContentType : 0
     * IsRead : false
     * CreateTime : 2017-04-27 17:51:47
     */

    private int Id;
    private String Title;
    private String PrimaryContent;
    private String Content;
    private int ContentType;
    private boolean IsRead;
    private String CreateTime;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPrimaryContent() {
        return PrimaryContent;
    }

    public void setPrimaryContent(String PrimaryContent) {
        this.PrimaryContent = PrimaryContent;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public int getContentType() {
        return ContentType;
    }

    public void setContentType(int ContentType) {
        this.ContentType = ContentType;
    }

    public boolean isIsRead() {
        return IsRead;
    }

    public void setIsRead(boolean IsRead) {
        this.IsRead = IsRead;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }
}
