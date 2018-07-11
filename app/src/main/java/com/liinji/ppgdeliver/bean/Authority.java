package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/9/1.
 */

public class Authority {

    /**
     * ReduceFreightAuthority : 0
     * ReducePaymentAuthority : 0
     */

    private int ReduceFreightAuthority;
    private int ReducePaymentAuthority;

    public int getReduceFreightAuthority() {
        return ReduceFreightAuthority;
    }

    public void setReduceFreightAuthority(int ReduceFreightAuthority) {
        this.ReduceFreightAuthority = ReduceFreightAuthority;
    }

    public int getReducePaymentAuthority() {
        return ReducePaymentAuthority;
    }

    public void setReducePaymentAuthority(int ReducePaymentAuthority) {
        this.ReducePaymentAuthority = ReducePaymentAuthority;
    }
}
