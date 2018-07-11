package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by 朱志强 on 2017/7/13.
 */

public class ProcessDetail {

    /**
     * ProblemId : 2
     * WaybillNo : 010051830044
     * RegisterBranchName : 宁波
     * ReceiveBranchName : 宁波
     * ProblemStatus : 客户拒付运费
     * ProblemDescribe : 客户拒付运费
     * ReplyList : [{"ProblemId":2,"ReplyBranchName":"宁波","ReplyTime":"2017-07-08 19:34:42","ReplyUserId":15,"ReplyUserName":"管理员","ReplyStatus":2,"ReplyStatusRemark":"处理结束","HandleSuggestion":"赔偿"},{"ProblemId":2,"ReplyBranchName":"宁波","ReplyTime":"2017-07-08 19:33:36","ReplyUserId":15,"ReplyUserName":"管理员","ReplyStatus":1,"ReplyStatusRemark":"处理中","HandleSuggestion":"同意"}]
     */

    private int ProblemId;
    private String WaybillNo;
    private String RegisterBranchName;
    private String ReceiveBranchName;
    private String ProblemStatus;
    private String ProblemDescribe;
    private List<ReplyListBean> ReplyList;

    public int getProblemId() {
        return ProblemId;
    }

    public void setProblemId(int ProblemId) {
        this.ProblemId = ProblemId;
    }

    public String getWaybillNo() {
        return WaybillNo;
    }

    public void setWaybillNo(String WaybillNo) {
        this.WaybillNo = WaybillNo;
    }

    public String getRegisterBranchName() {
        return RegisterBranchName;
    }

    public void setRegisterBranchName(String RegisterBranchName) {
        this.RegisterBranchName = RegisterBranchName;
    }

    public String getReceiveBranchName() {
        return ReceiveBranchName;
    }

    public void setReceiveBranchName(String ReceiveBranchName) {
        this.ReceiveBranchName = ReceiveBranchName;
    }

    public String getProblemStatus() {
        return ProblemStatus;
    }

    public void setProblemStatus(String ProblemStatus) {
        this.ProblemStatus = ProblemStatus;
    }

    public String getProblemDescribe() {
        return ProblemDescribe;
    }

    public void setProblemDescribe(String ProblemDescribe) {
        this.ProblemDescribe = ProblemDescribe;
    }

    public List<ReplyListBean> getReplyList() {
        return ReplyList;
    }

    public void setReplyList(List<ReplyListBean> ReplyList) {
        this.ReplyList = ReplyList;
    }

    public static class ReplyListBean {
        /**
         * ProblemId : 2
         * ReplyBranchName : 宁波
         * ReplyTime : 2017-07-08 19:34:42
         * ReplyUserId : 15
         * ReplyUserName : 管理员
         * ReplyStatus : 2
         * ReplyStatusRemark : 处理结束
         * HandleSuggestion : 赔偿
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
}
