package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by pig on 2017/12/15.
 */

public class UploadSignPicParams {

    /**
     * WaybillNo : string
     * Images : ["string"]
     * CompanyCode : string
     * BranchCode : string
     * UserId : 0
     * Token : string
     * Sign : string
     */

    private String WaybillNo;
    private String CompanyCode;
    private String BranchCode;
    private String UserId;
    private String Token;
    private String Sign;
    private List<String> Images;

    public String getWaybillNo() {
        return WaybillNo;
    }

    public void setWaybillNo(String WaybillNo) {
        this.WaybillNo = WaybillNo;
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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
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

    public List<String> getImages() {
        return Images;
    }

    public void setImages(List<String> Images) {
        this.Images = Images;
    }
}
