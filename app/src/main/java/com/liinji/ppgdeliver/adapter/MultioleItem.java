package com.liinji.ppgdeliver.adapter;

import android.bluetooth.BluetoothDevice;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017/4/14.
 */
public class MultioleItem implements MultiItemEntity {
    public static final int Header = 1000000;
    public static final int Body = 200000;

    private int itemType;
    private String mContent;
    private BluetoothDevice mDevice;

    private MultioleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getContent() {
        return mContent;
    }




}
