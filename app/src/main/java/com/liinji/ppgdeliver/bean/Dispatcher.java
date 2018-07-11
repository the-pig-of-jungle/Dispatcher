package com.liinji.ppgdeliver.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 朱志强 on 2017/7/25.
 */
@Entity
public class Dispatcher {
    @Id(autoincrement = true)
    private Long id;
    private int netId;
    private String Name;
    private String Telephone;
    private String BranchLetter;
    @Generated(hash = 1712641402)
    public Dispatcher(Long id, int netId, String Name, String Telephone,
                      String BranchLetter) {
        this.id = id;
        this.netId = netId;
        this.Name = Name;
        this.Telephone = Telephone;
        this.BranchLetter = BranchLetter;
    }
    @Generated(hash = 1940940286)
    public Dispatcher() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNetId() {
        return this.netId;
    }
    public void setNetId(int netId) {
        this.netId = netId;
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
