package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by 朱志强 on 2017/7/25.
 */

public class ToDispatcher {

    /**
     * ListWaybillNo : ["string"]
     * DistributeUserId : 0
     * DistributeUserName : string
     * CompanyCode : string
     * BranchCode : string
     * UserId : 0
     * Token : string
     * Sign : string
     */

    private int DistributeUserId;
    private String DistributeUserName;
    private String CompanyCode;
    private String BranchCode;
    private int UserId;
    private String Token;
    private String Sign;
    private List<String> ListWaybillNo;

    public int getDistributeUserId() {
        return DistributeUserId;
    }

    public void setDistributeUserId(int DistributeUserId) {
        this.DistributeUserId = DistributeUserId;
    }

    public String getDistributeUserName() {
        return DistributeUserName;
    }

    public void setDistributeUserName(String DistributeUserName) {
        this.DistributeUserName = DistributeUserName;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String CompanyCode) {
        this.CompanyCode = CompanyCode;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String Sign) {
        this.Sign = Sign;
    }

    public List<String> getListWaybillNo() {
        return ListWaybillNo;
    }

    public void setListWaybillNo(List<String> ListWaybillNo) {
        this.ListWaybillNo = ListWaybillNo;
    }
}
