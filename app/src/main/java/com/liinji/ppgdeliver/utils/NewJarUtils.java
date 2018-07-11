package com.liinji.ppgdeliver.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.configure.MyApplication;

import zpSDK.zpSDK.zpBluetoothPrinter;

/**
 * Created by pig on 2017/12/29.
 */

public class NewJarUtils {
    public static zpBluetoothPrinter sZpBluetoothPrinter;
    public static final String ADDRESS = "DC:1D:30:40:D2:07";
    public static void init(){
        sZpBluetoothPrinter = new zpBluetoothPrinter(MyApplication.sContext);
    }

    public static void print(){
        sZpBluetoothPrinter.connect(ADDRESS);
        sZpBluetoothPrinter.pageSetup(700,500);

       Bitmap bitmap = BitmapFactory.decodeResource(MyApplication.sContext.getResources(),R.raw.test);

        sZpBluetoothPrinter.drawGraphic(0,0,bitmap.getWidth(),bitmap.getHeight(),bitmap);
        sZpBluetoothPrinter.print(0,0);
        sZpBluetoothPrinter.disconnect();
    }
}
