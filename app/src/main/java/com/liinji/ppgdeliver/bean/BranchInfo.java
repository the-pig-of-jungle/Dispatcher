package com.liinji.ppgdeliver.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/6/12.
 */
@Entity
public class BranchInfo {

    @Id(autoincrement = true)
    private Long id;
    private String BranchName;
    private String BranchCode;
    private String BranchLetter;
    @Generated(hash = 1358632262)
    public BranchInfo(Long id, String BranchName, String BranchCode,
                      String BranchLetter) {
        this.id = id;
        this.BranchName = BranchName;
        this.BranchCode = BranchCode;
        this.BranchLetter = BranchLetter;
    }
    @Generated(hash = 1167890691)
    public BranchInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBranchName() {
        return this.BranchName;
    }
    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }
    public String getBranchCode() {
        return this.BranchCode;
    }
    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }
    public String getBranchLetter() {
        return this.BranchLetter;
    }
    public void setBranchLetter(String BranchLetter) {
        this.BranchLetter = BranchLetter;
    }

}
