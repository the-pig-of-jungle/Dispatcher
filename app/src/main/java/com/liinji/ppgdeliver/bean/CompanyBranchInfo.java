package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/6/13.
 */

public class CompanyBranchInfo {

    /**
     * BranchCode : 888888
     * BranchName : 总部
     */

    private String BranchCode;
    private String BranchName;
    private String initial;

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }


}
