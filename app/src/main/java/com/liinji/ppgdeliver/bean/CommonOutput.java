package com.liinji.ppgdeliver.bean;

import android.graphics.Bitmap;

/**
 * Created by pig on 2017/12/19.
 */

public class CommonOutput {
    private Bitmap mBitmap;
    private Bitmap mBarcodeBmp;
    private Bitmap mQRcodeBmp;
    int firstVLineStart;
    int firstVLineYEnd;
    int secondVLineStartY;
    int secondVLineEndY;

    public Bitmap getBarcodeBmp() {
        return mBarcodeBmp;
    }

    public void setBarcodeBmp(Bitmap barcodeBmp) {
        mBarcodeBmp = barcodeBmp;
    }

    public Bitmap getQRcodeBmp() {
        return mQRcodeBmp;
    }

    public void setQRcodeBmp(Bitmap QRcodeBmp) {
        mQRcodeBmp = QRcodeBmp;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public int getFirstVLineStart() {
        return firstVLineStart;
    }

    public void setFirstVLineStart(int firstVLineStart) {
        this.firstVLineStart = firstVLineStart;
    }

    public int getFirstVLineYEnd() {
        return firstVLineYEnd;
    }

    public void setFirstVLineYEnd(int firstVLineYEnd) {
        this.firstVLineYEnd = firstVLineYEnd;
    }

    public int getSecondVLineStartY() {
        return secondVLineStartY;
    }

    public void setSecondVLineStartY(int secondVLineStartY) {
        this.secondVLineStartY = secondVLineStartY;
    }

    public int getSecondVLineEndY() {
        return secondVLineEndY;
    }

    public void setSecondVLineEndY(int secondVLineEndY) {
        this.secondVLineEndY = secondVLineEndY;
    }
}
