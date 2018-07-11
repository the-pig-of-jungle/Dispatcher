package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/30.
 */
public class ReportItem {

    /**
     * AccountStatusText : 1
     * Amount : 4235
     */

    private String AccountStatusText;
    private int Amount;

    public String getAccountStatusText() {
        return AccountStatusText;
    }

    public void setAccountStatusText(String AccountStatusText) {
        this.AccountStatusText = AccountStatusText;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }


    public boolean isOffline(){
        return AccountStatusText.equals("1");
    }
}
