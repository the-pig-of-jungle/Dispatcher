package com.liinji.ppgdeliver.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 朱志强 on 2017/7/25.
 */
@Entity
public class LibBuddy {

    private int zId;
    private String Name;
    private String Telephone;
    private String BranchLetter;
    @Generated(hash = 1603714546)
    public LibBuddy(int zId, String Name, String Telephone, String BranchLetter) {
        this.zId = zId;
        this.Name = Name;
        this.Telephone = Telephone;
        this.BranchLetter = BranchLetter;
    }
    @Generated(hash = 215154314)
    public LibBuddy() {

    }
    public int getZId() {
        return this.zId;
    }
    public void setZId(int zId) {
        this.zId = zId;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getTelephone() {
        return this.Telephone;
    }
    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    public String getBranchLetter() {
        return this.BranchLetter;
    }
    public void setBranchLetter(String BranchLetter) {
        this.BranchLetter = BranchLetter;
    }


}
