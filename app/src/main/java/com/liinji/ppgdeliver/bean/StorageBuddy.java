package com.liinji.ppgdeliver.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 朱志强 on 2017/7/25.
 */
@Entity
public class StorageBuddy {
    @Id(autoincrement = true)
    private Long id;

    private int netId;
    private String Name;
    private String Telephone;

    @Generated(hash = 1384386956)
    public StorageBuddy(Long id, int netId, String Name, String Telephone) {
        this.id = id;
        this.netId = netId;
        this.Name = Name;
        this.Telephone = Telephone;
    }

    @Generated(hash = 1810736030)
    public StorageBuddy() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
