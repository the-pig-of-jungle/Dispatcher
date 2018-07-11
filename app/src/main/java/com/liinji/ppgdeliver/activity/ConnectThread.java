package com.liinji.ppgdeliver.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.liinji.ppgdeliver.bean.ConnectBlueDevice;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;


/**
 * Created by Administrator on 2017/5/8.
 */
public class ConnectThread extends Thread {

    private final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private String macAddress;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mDevice;
    private boolean Connected;
    private BluetoothSocket mSocket;


    public ConnectThread(String macAddress) {
        this.macAddress = macAddress;
    }

    public void run() {
        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }

        mDevice = mBluetoothAdapter.getRemoteDevice(macAddress);
        mBluetoothAdapter.cancelDiscovery();

        try {
            boolean returnValue = false;
            if (mDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                Method method = BluetoothDevice.class.getMethod("createBond");
                returnValue = (boolean) method.invoke(mDevice);
            } else if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                connect(mDevice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void connect(BluetoothDevice device) {
        EventBus.getDefault().postSticky(new ConnectBlueDevice(true));
        BluetoothSocket socket = null;
        try {
            socket = device.createRfcommSocketToServiceRecord(UUID.fromString(SPP_UUID));
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.e("蓝牙连接失败");
            //连接失败关闭Socket
            try {
                socket.close();
                socket = null;
                EventBus.getDefault().post(new ConnectBlueDevice(false));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void cancle() {
        try {
            mSocket.close();
            mSocket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
