package com.liinji.ppgdeliver.jiaboprint;

import android.app.IntentService;
import android.content.Intent;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by 朱志强 on 2017/9/27.
 */

public class JiaboPrintService extends IntentService {
    private static final int MAIN_QUERY_PRINTER_STATUS = 0xfe;
    private static final int REQUEST_PRINT_LABEL = 0xfd;
    private JiaboPrinterMonitorReceiver mMonitorReceiver;

    public static final String EXTRA_MULTIPLE_DATA = "multiple_data";

    public JiaboPrintService() {
        super("佳博print service");
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Logger.d("onhandle");
        final ArrayList<PrintInfo> printInfoList = intent.getParcelableArrayListExtra(EXTRA_MULTIPLE_DATA);

            if (MyApplication.sMyApplication.mGpService == null){
                MyApplication.sMyApplication.BindJiaboService(new MyApplication.JiaboCallback() {
                    @Override
                    public void onConnected() {
                        MyApplication.sMyApplication.mJiaboPrinterMonitorReceiver.setPrintInfo(printInfoList);
                        try {
                            MyApplication.sMyApplication.mGpService.queryPrinterStatus(2,1000,MAIN_QUERY_PRINTER_STATUS);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }else {
                MyApplication.sMyApplication.mJiaboPrinterMonitorReceiver.setPrintInfo(printInfoList);
                try {
                    MyApplication.sMyApplication.mGpService.queryPrinterStatus(2,1000,MAIN_QUERY_PRINTER_STATUS);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
    }


    public static void startService(ArrayList<PrintInfo> list){
        Intent intent = new Intent(MyApplication.sContext,JiaboPrintService.class);
        intent.putExtra(JiaboPrintService.EXTRA_MULTIPLE_DATA,list);
        MyApplication.sContext.startService(intent);
    }

    public static void startService(PrintInfo printInfo){
        ArrayList<PrintInfo> list = new ArrayList<>();
        list.add(printInfo);
        startService(list);
    }
}
