package com.liinji.ppgdeliver.jiaboprint;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.RemoteException;

import com.gprinter.command.GpCom;
import com.gprinter.io.GpDevice;
import com.gprinter.service.GpPrintService;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.print.PrintEvent;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by 朱志强 on 2017/9/27.
 */

public class JiaboPrinterMonitorReceiver extends BroadcastReceiver {

    private static final int MAIN_QUERY_PRINTER_STATUS = 0xfe;

    private ArrayList<PrintInfo> mPrintInfos;
    private int mPrintIndex = 0;
    public static final String LONG_ADDRESS = "内蒙古自治区呼和浩特市新城区人民路批发市场A区1235号";
    public static final String SHORT_ADDRESS = "内蒙古自治区呼和浩特市新城区人民路";
    public static final String SHORT_BRANCK_ADDRESS = "姚琳西路";
    public static final String LONG_BRANCH_ADDRESS = "浙江桐庐延林西路与虬江路交叉路口批发市场";


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action == null) {
            return;
        }

        // GpCom.ACTION_DEVICE_REAL_STATUS 为广播的IntentFilter
        if (action.equals(GpCom.ACTION_DEVICE_REAL_STATUS)) {

            // 业务逻辑的请求码，对应哪里查询做什么操作
            int requestCode = intent.getIntExtra(GpCom.EXTRA_PRINTER_REQUEST_CODE, -1);
            // 判断请求码，是则进行业务操作
            if (requestCode == MAIN_QUERY_PRINTER_STATUS) {

                int status = intent.getIntExtra(GpCom.EXTRA_PRINTER_REAL_STATUS, 16);

                if (status == GpCom.STATE_NO_ERR) {
                    if (MyApplication.sMyApplication.mGpService != null){
                        try {
                            int mode = MyApplication.sMyApplication.mGpService.getPrinterCommandType(2);
                            if (mode != GpCom.ESC_COMMAND){
                                EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.MODE_ERROR));
                                return;
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.CONNECT_SUCCESSFUL));
                    if (mPrintInfos != null && !mPrintInfos.isEmpty()){
                        PrintUtil.sendESCPrintInfo(mPrintInfos.get(mPrintIndex));
                    }

                } else {

                    if ((byte) (status & GpCom.STATE_OFFLINE) > 0) {
                        EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.FAIL_OFFLINE));
                    }

                    if ((byte) (status & GpCom.STATE_PAPER_ERR) > 0) {
                        EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.PAPER_LACK));
                        resetPrintData();
                        return;
                    }
                    if ((byte) (status & GpCom.STATE_COVER_OPEN) > 0) {

                        return;
                    }
                    if ((byte) (status & GpCom.STATE_ERR_OCCURS) > 0) {
                        EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.PRINTER_ERROR));
                        resetPrintData();
                        return;
                    }
                    if ((byte) (status & GpCom.STATE_TIMES_OUT) > 0) {
                        EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.TIMEOUT));
                        resetPrintData();
                        return;
                    }
                }


            }
        } else if (action.equals(GpCom.ACTION_LABEL_RESPONSE)) {


        } else if (action.equals(GpCom.ACTION_RECEIPT_RESPONSE)) {
            mPrintIndex++;

            if (mPrintInfos == null || mPrintInfos.isEmpty()) {
                mPrintInfos = null;
                return;
            }
            if (mPrintInfos.size() == 1) {
                CountDownTimer countDownTimer = new CountDownTimer(4000, 1100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.PRINT_COMPLETE));
                    }
                };
                countDownTimer.start();
                mPrintInfos.clear();
                mPrintInfos = null;
                return;
            }

            if (mPrintIndex < mPrintInfos.size()){
                PrintUtil.sendESCPrintInfo(mPrintInfos.get(mPrintIndex));
            }else {
                mPrintInfos.clear();
                mPrintInfos = null;
            }


        } else if (action.equals(GpCom.ACTION_CONNECT_STATUS)) {
            int type = intent.getIntExtra(GpPrintService.CONNECT_STATUS, 0);
            int id = intent.getIntExtra(GpPrintService.PRINTER_ID, 0);

            if (type == GpDevice.STATE_CONNECTING) {
                MyApplication.isConnect = true;

            } else if (type == GpDevice.STATE_NONE) {
                if (MyApplication.hasClose) {
                    return;
                }
                if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                    return;
                }
                if (MyApplication.isConnect) {
                    EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.JIABO_FAIL));

                }
                resetPrintData();
            } else if (type == GpDevice.STATE_VALID_PRINTER) {
                if (MyApplication.sMyApplication.mGpService != null){
                    try {
                        int mode = MyApplication.sMyApplication.mGpService.getPrinterCommandType(2);
                        if (mode != GpCom.ESC_COMMAND){
                            EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.MODE_ERROR));
                            return;
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (mPrintInfos != null && !mPrintInfos.isEmpty()) {
                    EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.CONNECT_SUCCESSFUL));
                    PrintUtil.sendESCPrintInfo(mPrintInfos.get(mPrintIndex));

                }

            } else if (type == GpDevice.STATE_INVALID_PRINTER) {

            } else if (type == GpDevice.STATE_CONNECTED) {
                Logger.d("连接打印机成功！");
            }

        }


    }

    private void resetPrintData() {
        if (mPrintInfos != null) {
            mPrintInfos.clear();
        }

        mPrintInfos = null;
        mPrintIndex = 0;
    }

    public static JiaboPrinterMonitorReceiver register(Context context) {
        JiaboPrinterMonitorReceiver receiver = new JiaboPrinterMonitorReceiver();
        context.registerReceiver(receiver, new IntentFilter(GpCom.ACTION_DEVICE_REAL_STATUS));
        context.registerReceiver(receiver, new IntentFilter(GpCom.ACTION_LABEL_RESPONSE));
        context.registerReceiver(receiver, new IntentFilter(GpCom.ACTION_RECEIPT_RESPONSE));
        context.registerReceiver(receiver, new IntentFilter(GpCom.ACTION_CONNECT_STATUS));
        return receiver;
    }

    public static JiaboPrinterMonitorReceiver unregister(Context context, JiaboPrinterMonitorReceiver receiver) {
        context.unregisterReceiver(receiver);
        return null;
    }


    public void setPrintInfo(ArrayList<PrintInfo> printInfos) {
        mPrintInfos = printInfos;
        mPrintIndex = 0;
    }


    public static boolean mConnectTag = false;
}
