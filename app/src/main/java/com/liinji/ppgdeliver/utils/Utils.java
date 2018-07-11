package com.liinji.ppgdeliver.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ViewFlipper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.tools.EncodingHandler;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.text.NumberFormat;
import java.text.ParseException;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/3/9.
 */
public class Utils {


    public static float dp2px(Context context, float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }

    public static int dp2px(float dp){
        return (int) dp2px(MyApplication.sContext,dp);
    }

    public static void dialSomeone(Context context, String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }


    public static String trimOrder(String orderStr){

        int markIndex = orderStr.indexOf("-");
        int realIndex = markIndex == -1 ? orderStr.length() : markIndex;
        return orderStr.substring(0,realIndex);

    }


    private static NumberFormat sCurrencyFormat = NumberFormat.getCurrencyInstance();

    public static String getCurrencyStr(int value){
        return sCurrencyFormat.format(value);
    }
    public static String getCurrencyStr(String value){
        return getCurrencyStr(Float.parseFloat(value));
    }
    public static String getCurrencyStr(float value){
        return sCurrencyFormat.format(value);
    }

    public static String getCurrencyStr(double value){
        return sCurrencyFormat.format(value);
    }

    public static float currencyStrToNumber(String str){
        try {
            return sCurrencyFormat.parse(str).floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }


    public static String appVersionCode(){

        try {
            return MyApplication.sContext.getPackageManager().getPackageInfo(MyApplication.sContext.getPackageName(),0).versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String appVersionName(){
        try {
            return "v " + MyApplication.sContext.getPackageManager().getPackageInfo(MyApplication.sContext.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static SpannableString getJiJie(){
        SpannableString s = new SpannableString("(积借服务)");
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#0dc25f")),1,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }


    public static final String toBase64Str(String src){
        return Base64.encodeToString(src.getBytes(), Base64.DEFAULT);
    }

    public static final String restoreFromBase64(String base64Str){
        return new String(Base64.decode(base64Str, Base64.DEFAULT));
    }

    public static Animation sSlideInRight = AnimationUtils.loadAnimation(MyApplication.sContext,R.anim.slide_in_right);
    public static Animation sSlideOutLeft = AnimationUtils.loadAnimation(MyApplication.sContext,R.anim.slide_out_left);
    public static Animation sSlideInLeft = AnimationUtils.loadAnimation(MyApplication.sContext,android.R.anim.slide_in_left);
    public static Animation sSlideOutRight = AnimationUtils.loadAnimation(MyApplication.sContext,android.R.anim.slide_out_right);

    public static void showNext(ViewFlipper viewFlipper){

        viewFlipper.setInAnimation(sSlideInRight);
        viewFlipper.setOutAnimation(sSlideOutLeft);
        viewFlipper.showNext();
    }

    public static void showPrevious(ViewFlipper viewFlipper){
        viewFlipper.setInAnimation(sSlideInLeft);
        viewFlipper.setOutAnimation(sSlideOutRight);
        viewFlipper.showPrevious();
    }


    public static Bitmap createOneDCode(String content, int mWidth, int mHeight) throws WriterException {

        // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.CODE_128, mWidth, mHeight);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }


    public static Bitmap createPrintImg(PrintInfo printInfo){
        Bitmap combined = null;
        try {
            Bitmap barCode = createOneDCode(printInfo.getOrderNumber(),1300,300);
            Bitmap QRCode = CodeUtils.createImage(printInfo.getOrderNumber(),460,460,null);
            combined = Bitmap.createBitmap(1900, 460,
                    Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(combined);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(barCode,0,0,null);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTextSize(120);

            canvas.drawText("      " + printInfo.getSpaceOrder(),0,450,paint);
            canvas.drawBitmap(QRCode,1300,0,null);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return combined;
    }

    public static Bitmap createZicoxPrintImg(PrintInfo printInfo){
        Bitmap combined = null;
        try {
            Bitmap barCode = createOneDCode(printInfo.getOrderNumber(),450,90);
            Bitmap QRCode = CodeUtils.createImage(printInfo.getOrderNumber(),150,150,null);
            combined = Bitmap.createBitmap(600, 150,
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(combined);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(barCode,-20,0,null);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTextSize(40);



            canvas.drawText("     " + printInfo.getSpaceOrder(),0,140,paint);
            canvas.drawBitmap(QRCode,400,0,null);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return combined;
    }

    public static Bitmap createBarCode(String text, int width, int height){
        Bitmap bitmap = null;
        try {
            bitmap = EncodingHandler.createBarCode(text, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    public static SpannableString getInserBoldText(int i, String s) {
        SpannableString span = new SpannableString(s);
        span.setSpan(new StyleSpan(Typeface.BOLD),i,span.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return span;
    }

    public static void processDialog(SweetAlertDialog sweetAlertDialog) {
        sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
            }
        });
    }


    public static void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) MyApplication.sContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
