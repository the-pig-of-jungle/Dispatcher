package com.liinji.ppgdeliver.adapter;


import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BlueDevice;

import java.util.List;


/**
 * Created by Administrator on 2017/4/14.
 */
public class BlueToothAdapter extends BaseMultiItemQuickAdapter<BlueDevice> {

    public BlueToothAdapter(List data) {
        super(data);
        addItemType(MultioleItem.Header, R.layout.bluetooth_header);
        addItemType(MultioleItem.Body, R.layout.item_bluetooth);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, BlueDevice multioleItem) {
        switch (baseViewHolder.getItemViewType()) {
            case MultioleItem.Header:
                baseViewHolder.setText(R.id.blueTooth_header, multioleItem.getContent());
                break;
            case MultioleItem.Body:

                baseViewHolder.setText(R.id.blueName, "蓝牙名称：" +( TextUtils.isEmpty(multioleItem.getBluetoothDevice().getName()) ? "未知设备":multioleItem.getBluetoothDevice().getName())).setText(R.id.blueAddress, "蓝牙地址：" + multioleItem.getBluetoothDevice().getAddress());

                if (multioleItem.isConnect()) {
                    baseViewHolder.setImageResource(R.id.isConnect, R.mipmap.bonddevice);
                } else {
                    baseViewHolder.setImageResource(R.id.isConnect, R.mipmap.newdevice);
                }

                break;
        }
    }
}
