package com.liinji.ppgdeliver.print;

/**
 * Created by pig on 2017/12/30.
 */

public class ForwardIndicator {
    private static int sCurrentValue;
    private static int sIncreaseAmount;

    private ForwardIndicator(){

    }


    public static void init(int currentValue,int increaseAmount){
        sCurrentValue = currentValue;
        sIncreaseAmount = increaseAmount;
    }


    public static int getCurrentValue() {
        return sCurrentValue;
    }

    public static void setCurrentValue(int currentValue) {
        sCurrentValue = currentValue;
    }

    public static int getIncreaseAmount() {
        return sIncreaseAmount;
    }

    public static void setIncreaseAmount(int increaseAmount) {
        sIncreaseAmount = increaseAmount;
    }



    public static int generateNewValue(){

        sCurrentValue += sIncreaseAmount;
        return sCurrentValue;
    }

    public static int generateNewValue(int fontSize){

        sCurrentValue += (sIncreaseAmount + ZicoxUtil.getFontWordSize(fontSize));
        return sCurrentValue;
    }

    public static int setCurrentValueByAdd(int amount){
        sCurrentValue += amount;
        return sCurrentValue;
    }


    public static int generateNewValueNotSaved(){
        return sCurrentValue + sIncreaseAmount;
    }


    public static int generateNewValueNotSaved(int fontSize){
        return sCurrentValue + sIncreaseAmount + fontSize;
    }
}
