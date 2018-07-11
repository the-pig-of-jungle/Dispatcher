package com.liinji.ppgdeliver.activity;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.BlueToothAdapter;
import com.liinji.ppgdeliver.bean.BlueDevice;
import com.liinji.ppgdeliver.bean.ChooseBlueTooth;
import com.liinji.ppgdeliver.bean.ConnectBlueDevice;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.tools.CustomDialogTool;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class BluetoothActivity extends BaseDarkStatusBarActivity {
    @BindView(R.id.searchDevice)
    TextView mSearchDevice;
    @BindView(R.id.hintInfo)
    TextView mHintInfo;
    @BindView(R.id.empty)
    RelativeLayout mEmpty;
    @BindView(R.id.recycler_device)
    RecyclerView mRecyclerDevice;
    private BluetoothAdapter mBluetoothAdapter;
    private Set<BluetoothDevice> mNotConnectDevices;
    private Set<BluetoothDevice> mIsConnectDevices;
    private List<BlueDevice> mBlueTooths;
    private BluetoothDevice mDevice;
    private BluetoothDevice mDevice1;
    private int chooseBlueTooth = -1;
    private BlueToothAdapter mBlueToothAdapter;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Logger.e("开始扫描");

            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                //找到蓝牙设备找未连接的设备
                mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (mDevice.getBluetoothClass().getMajorDeviceClass() != 0x600) {
                    return;
                }
                Logger.e(mDevice.getName() + "----" + mDevice.getAddress());

                if (mDevice.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNotConnectDevices.add(mDevice);
                } else if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                    //获取配对的设备
                    mIsConnectDevices.add(mDevice);
                }

            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                //正在连接
                mDevice1 = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                switch (mDevice1.getBondState()) {
                    case BluetoothDevice.BOND_BONDING:
                        Logger.e("正在绑定！");

                        break;
                    case BluetoothDevice.BOND_BONDED:
                        Logger.e("已经绑定！");
                        update();
                        CustomDialogTool.dissProgressDialog();
                        break;

                    case BluetoothDevice.BOND_NONE:
                        Logger.e("没有绑定");
                        break;
                }

            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                Logger.e("扫描完毕");
                SmartToast.showInCenter("扫描完毕！");
                //扫描完毕
                CustomDialogTool.dissProgressDialog();

                setDate();
            }
        }
    };


    private void updateRemove() {
        mIsConnectDevices = mBluetoothAdapter.getBondedDevices();
        mNotConnectDevices.add(mDevice1);
        setDate();
    }

    private void update() {
        mIsConnectDevices = mBluetoothAdapter.getBondedDevices();
        mNotConnectDevices.remove(mDevice1);
        setDate();
    }

    private void setDate() {
        mBlueTooths.clear();
        if (mIsConnectDevices != null && mIsConnectDevices.size() > 0) {
            mBlueTooths.add(new BlueDevice(BlueDevice.Header, "已配对设备"));
            for (BluetoothDevice isConnectDevice : mIsConnectDevices) {
                mBlueTooths.add(new BlueDevice(BlueDevice.Body, isConnectDevice, true));
            }
        }

        if (mNotConnectDevices != null && mNotConnectDevices.size() > 0) {
            mBlueTooths.add(new BlueDevice(BlueDevice.Header, "未配对设备"));
            for (BluetoothDevice notConnectDevice : mNotConnectDevices) {
                mBlueTooths.add(new BlueDevice(BlueDevice.Body, notConnectDevice, false));
            }
        }
        checkIsEmpty();
        mBlueToothAdapter.setNewData(mBlueTooths);
    }


    @Override
    protected int returnContentView() {
        return R.layout.activity_bluetooth;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mNotConnectDevices = new HashSet<>();
        mIsConnectDevices = new HashSet<>();
        mBlueTooths = new ArrayList<>();
        mBlueToothAdapter = new BlueToothAdapter(mBlueTooths);
        checkIsEmpty();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        initBlueTooth();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerDevice.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerDevice.setAdapter(mBlueToothAdapter);
        mRecyclerDevice.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Logger.e("点击了条目");
                BlueDevice item = (BlueDevice) baseQuickAdapter.getItem(i);
                // showIsConnect(item);
                if (item.getItemType() != BlueDevice.Header) {
                    BluetoothDevice bluetoothDevice = item.getBluetoothDevice();
                    if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                        //选择配对设备并跳转
                        showDevice(bluetoothDevice);
                        //removeBond(bluetoothDevice.getAddress());
                    } else {
                        showIsConnect(item);
                    }
                }

            }
        });

    }

    private void showDevice(final BluetoothDevice bluetoothDevice) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("是否选择该打印设备？" + "\n名称：" + bluetoothDevice.getName() == null ? "" : bluetoothDevice.getName() + "\n地址：" + bluetoothDevice.getAddress())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //将选择的蓝牙设备保存在本地
                        String srcDevice = SharePrefUtils.getSavedDeviceName();
                        SharePrefUtils.storeDeviceName(bluetoothDevice.getName());
                        SharePrefUtils.storeDeviceAddress(bluetoothDevice.getAddress());

                        setResult(RESULT_OK,new Intent());
                        if (!srcDevice.equals(SharePrefUtils.getSavedDeviceName())){
                            try {
                                if (MyApplication.sMyApplication.mGpService != null){
                                    MyApplication.sMyApplication.mGpService.closePort(2);
                                }

                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();

        dialog.show();
    }

    private void showIsConnect(final BlueDevice item) {
        new AlertDialog.Builder(this)
                .setMessage("是否绑定该设备：" + item.getBluetoothDevice().getName() + "?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        doConnect(item.getBluetoothDevice().getAddress());
                    }
                }).create().show();
    }


    private void removeBond(String address) {

        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }

        BluetoothDevice remoteDevice = mBluetoothAdapter.getRemoteDevice(address);
        try {
            Method removeBond = BluetoothDevice.class.getMethod("removeBond");
            boolean value = (boolean) removeBond.invoke(remoteDevice);

        } catch (Exception e) {
            e.printStackTrace();
            SmartToast.showInCenter("解除配对失败！");
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getConnectInfo(ConnectBlueDevice info) {
        Logger.e("获取到连接状态");
        if (info.isConnect()) {
            CustomDialogTool.showProgressDialog(this);
        } else {
            CustomDialogTool.dissProgressDialog();
        }
    }

    private void doConnect(String address) {
        ConnectThread connectThread = new ConnectThread(address);
        connectThread.run();
    }

    private void initBlueTooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //注册广播接受蓝牙信息
        toRegisterReceiver();
        //判断蓝牙是否开启
        isOpen();
    }

    private void checkIsEmpty() {
        if (mBlueTooths != null && mBlueTooths.size() > 0) {
            mEmpty.setVisibility(View.GONE);
            mRecyclerDevice.setVisibility(View.VISIBLE);

        } else {
            mEmpty.setVisibility(View.VISIBLE);
            mHintInfo.setText("暂未发现蓝牙设备");
            mRecyclerDevice.setVisibility(View.GONE);
        }
    }

    private void isOpen() {

        if (mBluetoothAdapter == null) {
            SmartToast.showLongInCenter("该设备不支持蓝牙！");
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            //弹框提示用户开启蓝牙
            Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabler, 1);
        }

        //获取已配对设备
        mIsConnectDevices.addAll(mBluetoothAdapter.getBondedDevices());
        checkIsEmpty();
        setDate();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            SmartToast.show("蓝牙开启成功！");
        }
    }

    private void toRegisterReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void initToolbarStyle() {
        toolbar_title.setText("蓝牙设置");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void chooseBlueDeviceToSave(ChooseBlueTooth info) {
        chooseBlueTooth = info.getIsCustomer();
    }


    @OnClick({R.id.searchDevice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchDevice:
                if (Build.VERSION.SDK_INT >= 23) {
                    Logger.e("版本高于23");
                    if (getTargetSdkVersion() >= 23) {
                        int permission = ContextCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            Logger.e("targetVersion23无定位权限");
                            if (ActivityCompat.shouldShowRequestPermissionRationale(BluetoothActivity.this, Manifest.permission.READ_CALENDAR)) {
                                SmartToast.showInCenter("无权限将不能搜索到蓝牙设备");
                            } else {
                                ActivityCompat.requestPermissions(BluetoothActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0x123456);
                            }
                        } else {

                            Logger.e("targetVersion23有定位权限");
                            doDiscovery();
                        }
                    } else {
                        if (PermissionChecker.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == PermissionChecker.PERMISSION_GRANTED) {
                            Logger.e("targetVersion23以下有定位权限");
                            doDiscovery();
                        } else {
                            Logger.e("targetVersion23以下没有定位权限");
                            showDialog();
                        }
                    }
                } else {
                    doDiscovery();
                }
                break;
        }
    }

    private void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("该功能需要开启定位服务，否则将无法搜索到附近设备,是否开启？")
                .setPositiveButton("前往设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent localIntent = new Intent();
                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (Build.VERSION.SDK_INT >= 9) {
                            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                        } else if (Build.VERSION.SDK_INT <= 8) {
                            localIntent.setAction(Intent.ACTION_VIEW);
                            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
                        }
                        startActivity(localIntent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    private int getTargetSdkVersion() {
        try {
            final PackageInfo info = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            int targetSdkVersion = info.applicationInfo.targetSdkVersion;
            return targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Logger.e("onRequestPermissionsResult");
        if (requestCode == 0x123456) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                doDiscovery();
            } else {
                SmartToast.showInCenter("权限被拒绝");
            }
        }
    }

    private void doDiscovery() {
        CustomDialogTool.showProgressDialogNoCancel(this);
        if (mBluetoothAdapter != null && mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.startDiscovery();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(receiver);
    }


}
