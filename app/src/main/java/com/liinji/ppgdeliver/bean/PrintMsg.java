package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/5/4.
 */
public class PrintMsg {

    private String PrintComplete;


    public PrintMsg(String printComplete) {
        PrintComplete = printComplete;

    }

    public String getPrintComplete() {
        return PrintComplete;
    }

    public void setPrintComplete(String printComplete) {
        PrintComplete = printComplete;
    }

}
