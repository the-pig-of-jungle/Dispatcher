package com.liinji.ppgdeliver.print;

import android.graphics.Bitmap;

import com.liinji.ppgdeliver.configure.MyApplication;

import zpSDK.zpSDK.zpBluetoothPrinter;

/**
 * Created by pig on 2017/12/29.
 */

public class ZicoxUtil {

    public static zpBluetoothPrinter getPrinter() {

        return MyApplication.sZpBluetoothPrinter;
    }


    public static void drawImgInCenter(Bitmap bmp, int y) {
//        int width = bmp.getWidth();
//        int height = bmp.getHeight();
//        float x = (PrintSetting.PAGE_H_EDN - width) / 2;
//        zpSDK.zp_draw_bitmap(bmp,x,y * 8);

        int x = (ZicoxSetting.PAGE_H_END - bmp.getWidth()) / 2;

        getPrinter().drawGraphic(x, y, 0, 0, bmp);

    }

    public static void drawHLine(int y) {
//        zpSDK.zp_draw_line(0,y,PrintSetting.PAGE_H_EDN,y,PrintSetting.LINE_SIZE);
        getPrinter().drawLine(ZicoxSetting.LINE_WIDTH, 0, y, ZicoxSetting.PAGE_H_END, y, false);
    }

    //文字大小单位为毫米，坐标偏移为毫米，坐标指文字左下角
    public static void drawTextInCenter(String content, int y, int fontSize, boolean bold) {
//        float x = (PrintSetting.PAGE_H_EDN / 8 - content.length() * fontSize) / 2;
//        zpSDK.zp_draw_text_ex(x,y,content,TEXT_STYLE,fontSize,0,bold,false,false);
        int wordsPerSize = getFontWordSize(fontSize);

        getPrinter().drawText((ZicoxSetting.PAGE_H_END - content.length() * wordsPerSize) / 2, y, content, fontSize, 0, bold ? 1 : 0, false, false);
    }

    public static int getFontWordSize(int fontSize) {
        int wordsPerSize = 0;
        switch (fontSize) {
            case 1:

                break;
            case 2:
                wordsPerSize = 24;
                break;
            case 3:

                break;
            case 4:
                wordsPerSize = 48;
                break;
        }

        return wordsPerSize;
    }


    public static void drawText(String content, int y, int fontSize, boolean bold) {

//        zpSDK.zp_draw_text_ex(PrintSetting.CONTENT_START_SPACE,y,content,TEXT_STYLE,fontSize,0,bold,false,false);

        getPrinter().drawText(ZicoxSetting.CONTENT_START_X, y, content, fontSize, 0, bold ? 1 : 0, false, false);

    }

    public static void drawTextAtX(String content, int x, int y, int fontSize, boolean bold) {
        getPrinter().drawText(x, y, content, fontSize, 0, bold ? 1 : 0, false, false);
    }



    public static void drawTextAtRight(String content, int y, int fontSize, boolean b) {
        int x = ZicoxSetting.PAGE_H_END - (content.length()+ 1) * getFontWordSize(2);


        getPrinter().drawText(x,y,content,fontSize,0,b ? 1:0,false,false);
    }

    public static void drawTextInCenterShift(String content, float shift, float y, float fontSize, boolean bold) {
//        float x = (PrintSetting.PAGE_H_EDN / 8 - content.length() * fontSize) / 2 + shift;
//        zpSDK.zp_draw_text_ex(x,y,content,TEXT_STYLE,fontSize,0,bold,false,false);
    }



    public static void drawVLineInCenter(int firstVLineStart, int firstVLineYEnd) {
//        zpSDK.zp_draw_line(PrintSetting.HALF_X,firstVLineStart,PrintSetting.HALF_X,firstVLineYEnd,PrintSetting.LINE_SIZE);
        getPrinter().drawLine(ZicoxSetting.LINE_WIDTH,ZicoxSetting.PAGE_H_END / 2,firstVLineStart,ZicoxSetting.PAGE_H_END / 2,firstVLineYEnd,true);
    }

    public static int halfShift(int shift) {
//        return (float) (PrintSetting.HALF_X + shift);
        return ZicoxSetting.PAGE_H_END / 2 + shift;
    }

    public static int startShift(int shift) {
//        return (float) (PrintSetting.CONTENT_START_SPACE + shift);
        return ZicoxSetting.CONTENT_START_X + shift;
    }


    public static int endShift(int shift) {

//        return (float) (PrintSetting.PAGE_H_EDN / 8 + shift);


        return ZicoxSetting.PAGE_H_END + shift;
    }

    public static void drawVLine(int x, int startY, int endY) {
//        zpSDK.zp_draw_line(x,startY,x,endY,PrintSetting.LINE_SIZE);
        getPrinter().drawLine(ZicoxSetting.LINE_WIDTH,x,startY,x,endY,true);
    }

    public static void drawBarCode(String content, int y) {

        getPrinter().drawBarCode(ZicoxSetting.CONTENT_START_X, y, content,
                ZicoxSetting.BARCODE_TYPE,false,ZicoxSetting.BARCODE_PER_WIDTH,ZicoxSetting.BARCODE_HEIGHT);

    }

    public static void drawBarCode(Bitmap bmp, int y) {

        getPrinter().drawGraphic(0,y,0,0,bmp);

    }

    public static void drawQRCode(int x, int y, String content, int width) {
        getPrinter().drawQrCode(x,y,content,0,width,20);
    }

    public static void drawQRCode(int x, int y, Bitmap bmp) {
        getPrinter().drawGraphic(x,y,0,0,bmp);
    }
}
