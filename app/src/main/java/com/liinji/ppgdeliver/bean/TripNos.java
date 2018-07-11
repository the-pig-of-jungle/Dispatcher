package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by 喜欢、陪你看风景 on 2017/12/6.
 */

public class TripNos {

    /**
     * ReturnData : [{"TripNo":"CT00217101300004"}]
     * TotalMessage : {"SumRecords":"1"}
     */

    private TotalMessageBean TotalMessage;
    private List<ReturnDataBean> ReturnData;

    public TotalMessageBean getTotalMessage() {
        return TotalMessage;
    }

    public void setTotalMessage(TotalMessageBean TotalMessage) {
        this.TotalMessage = TotalMessage;
    }

    public List<ReturnDataBean> getReturnData() {
        return ReturnData;
    }

    public void setReturnData(List<ReturnDataBean> ReturnData) {
        this.ReturnData = ReturnData;
    }

    public static class TotalMessageBean {
        /**
         * SumRecords : 1
         */

        private String SumRecords;

        public String getSumRecords() {
            return SumRecords;
        }

        public void setSumRecords(String SumRecords) {
            this.SumRecords = SumRecords;
        }
    }

    public static class ReturnDataBean {
        /**
         * TripNo : CT00217101300004
         */

        private String TripNo;
        private boolean IsChecked;

        public String getTripNo() {
            return TripNo;
        }

        public void setTripNo(String TripNo) {
            this.TripNo = TripNo;
        }

        public boolean isChecked() {
            return IsChecked;
        }

        public void setChecked(boolean checked) {
            IsChecked = checked;
        }
    }
}
