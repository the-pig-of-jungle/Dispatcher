package com.liinji.ppgdeliver.print;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 朱志强 on 2017/8/16.
 */

public class PrintEvent {

    public static final int FAIL_BLUETOOTH_CLOSE = 0;
    public static final int FAIL_DEVICE_NOT_BONDED = 1;
    public static final int FAIL_REASON_UNKNOWN = 2;
    public static final int CONNECT_SUCCESSFUL = 3;
    public static final int PRINT_COMPLETE = 4;

    public static final int FAIL_OFFLINE = 5;
    public static final int TIMEOUT = 6;
    public static final int PRINTER_ERROR = 7;
    public static final int PAPER_LACK = 8;
    public static final int JIABO_FAIL = 9;
    public static final int MODE_ERROR = 10;

    @IntDef({FAIL_BLUETOOTH_CLOSE,FAIL_DEVICE_NOT_BONDED,FAIL_REASON_UNKNOWN,CONNECT_SUCCESSFUL,PRINT_COMPLETE,FAIL_OFFLINE,TIMEOUT,PRINTER_ERROR,PAPER_LACK,JIABO_FAIL,MODE_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PRINT_RESULT{}

    @PRINT_RESULT int mResult;

    public PrintEvent(@PRINT_RESULT int result) {
        mResult = result;
    }

    public int getResult() {
        return mResult;
    }
}
