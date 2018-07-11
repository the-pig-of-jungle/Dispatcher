package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/3/16.
 */
public class BlueTooth {

    private String DeviceName;
    private String DeviceAddress;

    public BlueTooth(String deviceName, String deviceAddress) {

        DeviceName = deviceName;
        DeviceAddress = deviceAddress;
    }


    public String getDeviceAddress() {
        return DeviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        DeviceAddress = deviceAddress;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

}
