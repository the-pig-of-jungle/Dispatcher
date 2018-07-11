package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/5/8.
 */
public class ConnectBlueDevice {
    private boolean isConnect;

    public ConnectBlueDevice(boolean isConnect) {
        this.isConnect = isConnect;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}
