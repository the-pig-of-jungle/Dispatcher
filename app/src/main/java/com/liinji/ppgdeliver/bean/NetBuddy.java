package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/7/25.
 */

public class NetBuddy {

    /**
     * Id : 49
     * Name : 张小实
     * Telephone : 13482347495
     */

    private int Id;
    private String Name;
    private String Telephone;
    private String Initial;

    public String getInitial() {
        return Initial;
    }

    public void setInitial(String initial) {
        Initial = initial;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
}
