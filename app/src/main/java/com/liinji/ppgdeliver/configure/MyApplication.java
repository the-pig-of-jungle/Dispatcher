package com.liinji.ppgdeliver.configure;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.gprinter.aidl.GpService;
import com.gprinter.service.GpPrintService;
import com.liinji.ppgdeliver.BuildConfig;
import com.liinji.ppgdeliver.jiaboprint.JiaboPrinterMonitorReceiver;
import com.liinji.ppgdeliver.manager.SharedPreManager;
import com.liinji.ppgdeliver.manager.UserInfoManager;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;
import zpSDK.zpSDK.zpBluetoothPrinter;


/**
 * Created by YUEYINGSK on 2016/8/16.
 */
public class MyApplication extends Application {

    public static final String AD = "千一物流，发货最好、最快、最安全，值得信赖，是您发货的不二之选。发货网点一千多个，有颐高、上塘路、科奥机电、长城机电、宝马、金恒德、电子市场、新武林门、安踏、沈家门、定海、英特医药、老余杭等等。\n";

    public static Bitmap sCompanyLogo;

    public static final String sBuglyId = BuildConfig.BUGLY_ID;

    //更改测试
    public static Context sContext;

    public static MyApplication sMyApplication;
    public static final boolean sDebugMode = false;

    /**
     * 储存短暂数据
     */
    public static Map<Object, Object> shortsaveDataMap;
    public static boolean upImg;
    public static boolean upTotal;

    public static boolean sCancelNetTip;


    public GpService mGpService = null;

    public PrinterServiceConnection conn = null;

    public JiaboPrinterMonitorReceiver mJiaboPrinterMonitorReceiver;

    class PrinterServiceConnection implements ServiceConnection {
        public JiaboCallback mJiaboCallback;

        public PrinterServiceConnection(JiaboCallback callback) {
            mJiaboCallback = callback;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("ServiceConnection", "onServiceDisconnected() called");
            mGpService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mGpService = GpService.Stub.asInterface(service);
            if (mJiaboCallback != null) {
                mJiaboCallback.onConnected();

            }
        }
    }


//    public void loadCompanyLogo(String imgUrl){
//        if(!TextUtils.isEmpty(imgUrl)){
//            Glide.with(this).load(imgUrl).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
//                @Override
//                public void onResourceReady(byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
//                    sCompanyLogo = BitmapFactory.decodeByteArray(resource,0,resource.length);
//                }
//            });
//        }
//    }


    public void loadCompanyLogo(String imgUrl){
        if(!TextUtils.isEmpty(imgUrl)){
            Glide.with(this).load(imgUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    MyApplication.sCompanyLogo = resource;
                }
            });
        }
    }


    public static zpBluetoothPrinter sZpBluetoothPrinter;




    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sMyApplication = this;

        sZpBluetoothPrinter = new zpBluetoothPrinter(this);

        SmartToast.plainToast(this);
        sCancelNetTip = false;
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.resumePush(this);

        init();
        initManager();
        initNetWork();
        ZXingLibrary.initDisplayOpinion(this);
        CrashReport.initCrashReport(getApplicationContext(), sBuglyId, true);
        BindJiaboService(null);
        mJiaboPrinterMonitorReceiver = JiaboPrinterMonitorReceiver.register(this);
    }

    public interface JiaboCallback {
        void onConnected();
    }

    public static void BindJiaboService(JiaboCallback callback) {
        MyApplication.sMyApplication.conn = MyApplication.sMyApplication.getServiceConn(callback);
        Intent intent = new Intent(MyApplication.sMyApplication, GpPrintService.class);
        MyApplication.sMyApplication.bindService(intent, MyApplication.sMyApplication.conn, Context.BIND_AUTO_CREATE); // bindService
    }

    public PrinterServiceConnection getServiceConn(JiaboCallback callback) {
        return new PrinterServiceConnection(callback);
    }


    private void initManager() {
        SharedPreManager.init(getApplicationContext());
        UserInfoManager.init();
    }

    private void init() {
        shortsaveDataMap = new HashMap<>();
    }

    private void initNetWork() {
        OkHttpClient okHttpClient;
        if (AppConstants.VersionEnvironment.IS_TEST) {
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
            okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
//                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    //其他配置
                    .addInterceptor(new LoggerInterceptor("LiinjiAs")).build();
        } else {
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
            okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
//                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)

                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    //其他配置
                    .build();
        }

        OkHttpUtils.initClient(okHttpClient);

    }



    /**
     * 初始化正式环境
     */
    private void initReleaseVersionEnvironment() {
        AppConstants.VersionEnvironment.IS_TEST = false;
        Logger.init().logLevel(LogLevel.NONE);
    }

    /**
     * 初始化测试环境
     */
    private void initTestVersionEnvironment() {
        AppConstants.VersionEnvironment.IS_TEST = true;
    }

    public static boolean hasClose = false;

    public static boolean isConnect = false;
}
