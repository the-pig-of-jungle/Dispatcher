package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/4/27.
 */
public class Message {

    /**
     * Id : 41
     * Title : 配送的，快开门
     * PrimaryContent : 您有新的快递
     * Content :
     * ContentType : 0
     * IsRead : false
     * CreateTime : 2017-04-27 10:50:18
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
