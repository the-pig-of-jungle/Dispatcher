package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/8/9.
 */

public class PrintCompanyInfo {

    /**
     * CompanyName : 千二物流1
     * CompanyTel : 021-12312312
     * CompanyAddress :
     * CompanyUrl : http://liinji.uicp.cn:24010/company/002.png
     */

    private String CompanyName;
    private String CompanyTel;
    private String CompanyAddress;
    private String CompanyUrl;
    private String BranchAddress;
    private String BranchContact;
    private String BranchContactTel;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyTel() {
        return CompanyTel;
    }

    public void setCompanyTel(String companyTel) {
        CompanyTel = companyTel;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getCompanyUrl() {
        return CompanyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        CompanyUrl = companyUrl;
    }

    public String getBranchAddress() {
        return BranchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        BranchAddress = branchAddress;
    }

    public String getBranchContact() {
        return BranchContact;
    }

    public void setBranchContact(String branchContact) {
        BranchContact = branchContact;
    }

    public String getBranchContactTel() {
        return BranchContactTel;
    }

    public void setBranchContactTel(String branchContactTel) {
        BranchContactTel = branchContactTel;
    }
}
