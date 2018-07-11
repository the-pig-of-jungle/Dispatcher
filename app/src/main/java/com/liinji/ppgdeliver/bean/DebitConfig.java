package com.liinji.ppgdeliver.bean;

/**
 * Created by 朱志强 on 2017/8/1.
 */

public class DebitConfig {

    /**
     * Id : 1
     * CompanyCode : 001
     * CompanyName : 千一物流
     * AllFee : 0
     * TotalFee : 1
     * CargoFee : 1
     *
     *
     * JJAllFee : 1
     * JJTotalFee : 1
     * JJCargoFee : 1
     */


    public static final String MODE_1_1_1 = "111";
    public static final String MODE_0_0_0 = "000";
    public static final String MODE_0_0_1 = "001";
    public static final String MODE_0_1_0 = "010";
    public static final String MODE_0_1_1 = "011";





    private int Id;
    private String CompanyCode;
    private String CompanyName;
    private int AllFee;
    private int TotalFee;
    private int CargoFee;
    private int JJAllFee;
    private int JJTotalFee;
    private int JJCargoFee;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String CompanyCode) {
        this.CompanyCode = CompanyCode;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public int getAllFee() {
        return AllFee;
    }

    public void setAllFee(int AllFee) {
        this.AllFee = AllFee;
    }

    public int getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(int TotalFee) {
        this.TotalFee = TotalFee;
    }

    public int getCargoFee() {
        return CargoFee;
    }

    public void setCargoFee(int CargoFee) {
        this.CargoFee = CargoFee;
    }

    public int getJJAllFee() {
        return JJAllFee;
    }

    public void setJJAllFee(int JJAllFee) {
        this.JJAllFee = JJAllFee;
    }

    public int getJJTotalFee() {
        return JJTotalFee;
    }

    public void setJJTotalFee(int JJTotalFee) {
        this.JJTotalFee = JJTotalFee;
    }

    public int getJJCargoFee() {
        return JJCargoFee;
    }

    public void setJJCargoFee(int JJCargoFee) {
        this.JJCargoFee = JJCargoFee;
    }


    public String getJJMode(){
        return JJAllFee + "" + JJTotalFee + "" + JJCargoFee;
    }

    public String getNormalMode(){
        return AllFee + "" + TotalFee + "" + CargoFee;
    }
}
