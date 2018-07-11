package com.liinji.ppgdeliver.bean;

import android.bluetooth.BluetoothDevice;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017/4/14.
 */
public class BlueDevice implements MultiItemEntity {

    public static final int Header = 1000000;
    public static final int Body = 200000;

    private int mItemType;

    private String Content;
    private BluetoothDevice mBluetoothDevice;

    public BluetoothDevice getBluetoothDevice() {
        return mBluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        mBluetoothDevice = bluetoothDevice;
    }

    private boolean IsConnect;

    public boolean isConnect() {
        return IsConnect;
    }

    public void setConnect(boolean connect) {
        IsConnect = connect;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public BlueDevice(int itemType, BluetoothDevice bluetoothDevice, boolean isConnect) {
        mItemType = itemType;
        mBluetoothDevice = bluetoothDevice;
        IsConnect = isConnect;
    }

    public BlueDevice(int itemType, String content) {
        mItemType = itemType;
        Content = content;
    }


    @Override
    public int getItemType() {
        return mItemType;
    }

}
