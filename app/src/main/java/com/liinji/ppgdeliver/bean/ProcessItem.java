package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/6/16.
 */

public class ProcessItem{

    /**
     * ProblemId : 10
     * ReplyBranchName : 富阳
     * ReplyTime : 2017-06-02 11:10:41
     * ReplyUserId : 18
     * ReplyUserName : 张实
     * ReplyStatus : 1
     * ReplyStatusRemark : 处理中
     * HandleSuggestion : 哥就不输入你咋地
     */

    private int ProblemId;
    private String ReplyBranchName;
    private String ReplyTime;
    private int ReplyUserId;
    private String ReplyUserName;
    private int ReplyStatus;
    private String ReplyStatusRemark;
    private String HandleSuggestion;

    public int getProblemId() {
        return ProblemId;
    }

    public void setProblemId(int ProblemId) {
        this.ProblemId = ProblemId;
    }

    public String getReplyBranchName() {
        return ReplyBranchName;
    }

    public void setReplyBranchName(String ReplyBranchName) {
        this.ReplyBranchName = ReplyBranchName;
    }

    public String getReplyTime() {
        return ReplyTime;
    }

    public void setReplyTime(String ReplyTime) {
        this.ReplyTime = ReplyTime;
    }

    public int getReplyUserId() {
        return ReplyUserId;
    }

    public void setReplyUserId(int ReplyUserId) {
        this.ReplyUserId = ReplyUserId;
    }

    public String getReplyUserName() {
        return ReplyUserName;
    }

    public void setReplyUserName(String ReplyUserName) {
        this.ReplyUserName = ReplyUserName;
    }

    public int getReplyStatus() {
        return ReplyStatus;
    }

    public void setReplyStatus(int ReplyStatus) {
        this.ReplyStatus = ReplyStatus;
    }

    public String getReplyStatusRemark() {
        return ReplyStatusRemark;
    }

    public void setReplyStatusRemark(String ReplyStatusRemark) {
        this.ReplyStatusRemark = ReplyStatusRemark;
    }

    public String getHandleSuggestion() {
        return HandleSuggestion;
    }

    public void setHandleSuggestion(String HandleSuggestion) {
        this.HandleSuggestion = HandleSuggestion;
    }




}
