package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public class DebitOrder {


    /**
     * Detail : [{"UserId":20001210,"OrderId":"02200124500211","Amount":23.72,"AmountDescribe":"本金:20.00+违约金:3.72","CargoName":"手机","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款88天","ReceiveTel":"","ReceiveInfo":"","SignDate":"2017-09-03 17:45:09","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-03 16:57:21"},{"UserId":20001210,"OrderId":"02200124500259","Amount":23.72,"AmountDescribe":"本金:20.00+违约金:3.72","CargoName":"F","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款88天","ReceiveTel":"","ReceiveInfo":"","SignDate":"2017-09-03 18:35:44","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-03 17:39:31"},{"UserId":20001210,"OrderId":"02200124500266","Amount":23.72,"AmountDescribe":"本金:20.00+违约金:3.72","CargoName":"E","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款88天","ReceiveTel":"","ReceiveInfo":"","SignDate":"2017-09-03 18:35:59","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-03 17:39:41"},{"UserId":20001210,"OrderId":"02200124500273","Amount":23.72,"AmountDescribe":"本金:20.00+违约金:3.72","CargoName":"D","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款88天","ReceiveTel":"","ReceiveInfo":"","SignDate":"2017-09-03 18:34:28","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-03 17:39:50"},{"UserId":20001210,"OrderId":"02200124500280","Amount":23.72,"AmountDescribe":"本金:20.00+违约金:3.72","CargoName":"C","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款88天","ReceiveTel":"","ReceiveInfo":"","SignDate":"2017-09-03 18:34:50","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-03 17:40:01"},{"UserId":20001210,"OrderId":"02200124500297","Amount":23.72,"AmountDescribe":"本金:20.00+违约金:3.72","CargoName":"B","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款88天","ReceiveTel":"","ReceiveInfo":"","SignDate":"2017-09-03 18:35:07","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-03 17:40:10"},{"UserId":20001210,"OrderId":"02200124500303","Amount":23.72,"AmountDescribe":"本金:20.00+违约金:3.72","CargoName":"A","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款88天","ReceiveTel":"","ReceiveInfo":"","SignDate":"2017-09-03 18:35:23","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-03 17:40:19"},{"UserId":20001211,"OrderId":"02200124800069","Amount":14.12,"AmountDescribe":"本金:12.00+违约金:2.12","CargoName":"花生","TotalFee":10,"CargoFee":2,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款85天","ReceiveTel":"18756157607","ReceiveInfo":"司钰楠","SignDate":"2017-09-06 11:10:08","ReceiveAddress":"内蒙古自治区呼和浩特市新城区人民路批发市场A区1235号","PaperNumber":"","ConsignDate":"2017-09-06 10:45:55"},{"UserId":20001210,"OrderId":"02200125000024","Amount":23.42,"AmountDescribe":"本金:20.00+违约金:3.42","CargoName":"Y","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款83天","ReceiveTel":"13621671346","ReceiveInfo":"赵杨杰","SignDate":"2017-09-08 10:47:10","ReceiveAddress":"安徽省合肥市包河区1000号","PaperNumber":"","ConsignDate":"2017-09-08 09:55:55"},{"UserId":20001210,"OrderId":"02200125000048","Amount":14.05,"AmountDescribe":"本金:12.00+违约金:2.05","CargoName":"nice","TotalFee":10,"CargoFee":2,"AdvanceTransitFee":0,"Number":1,"Overdue":1,"Describe":"已欠款83天","ReceiveTel":"13621671346","ReceiveInfo":"赵杨杰","SignDate":"2017-09-08 10:44:22","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-09-08 09:56:16"},{"UserId":20001211,"OrderId":"02200125700108","Amount":23,"AmountDescribe":"本金:20.00+违约金:3.00","CargoName":"ff","TotalFee":10,"CargoFee":10,"AdvanceTransitFee":0,"Number":2,"Overdue":1,"Describe":"已欠款76天","ReceiveTel":"18756157607","ReceiveInfo":"司钰楠","SignDate":"2017-09-15 14:26:27","ReceiveAddress":"内蒙古自治区呼和浩特市新城区人民路批发市场A区1235号","PaperNumber":"","ConsignDate":"2017-09-15 14:23:40"},{"UserId":20001210,"OrderId":"02200130400062","Amount":12.11,"AmountDescribe":"本金:12.00+违约金:0.11","CargoName":"gyf","TotalFee":10,"CargoFee":2,"AdvanceTransitFee":0,"Number":1,"Overdue":1,"Describe":"已欠款29天","ReceiveTel":"13621671346","ReceiveInfo":"赵杨杰","SignDate":"2017-11-01 16:27:22","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-11-01 16:23:48"},{"UserId":20001211,"OrderId":"02200130400208","Amount":12.11,"AmountDescribe":"本金:12.00+违约金:0.11","CargoName":"光大","TotalFee":10,"CargoFee":2,"AdvanceTransitFee":0,"Number":1,"Overdue":1,"Describe":"已欠款29天","ReceiveTel":"18756157607","ReceiveInfo":"司钰楠","SignDate":"2017-11-01 17:13:35","ReceiveAddress":"安徽省淮南市市辖区汽配城牛行街1000号","PaperNumber":"","ConsignDate":"2017-11-01 17:05:07"},{"UserId":20001210,"OrderId":"02200130400239","Amount":13.12,"AmountDescribe":"本金:13.00+违约金:0.12","CargoName":"啦啦啦","TotalFee":10,"CargoFee":3,"AdvanceTransitFee":0,"Number":1,"Overdue":1,"Describe":"已欠款29天","ReceiveTel":"13621671346","ReceiveInfo":"赵杨杰","SignDate":"2017-11-01 17:34:26","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-11-01 17:30:18"},{"UserId":20001210,"OrderId":"02200130400291","Amount":15.14,"AmountDescribe":"本金:15.00+违约金:0.14","CargoName":"bhu","TotalFee":13,"CargoFee":2,"AdvanceTransitFee":0,"Number":1,"Overdue":1,"Describe":"已欠款29天","ReceiveTel":"13621671346","ReceiveInfo":"赵杨杰","SignDate":"2017-11-01 18:18:02","ReceiveAddress":"浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号","PaperNumber":"","ConsignDate":"2017-11-01 18:16:07"}]
     * TotalMessage : {"TotalAmount":256,"TotalOver5":0,"TotalOver15":4,"TotalOver30":11,"TotalRec":15}
     */

    private TotalMessageBean TotalMessage;
    private List<DetailBean> Detail;

    public TotalMessageBean getTotalMessage() {
        return TotalMessage;
    }

    public void setTotalMessage(TotalMessageBean TotalMessage) {
        this.TotalMessage = TotalMessage;
    }

    public List<DetailBean> getDetail() {
        return Detail;
    }

    public void setDetail(List<DetailBean> Detail) {
        this.Detail = Detail;
    }

    public static class TotalMessageBean {
        /**
         * TotalAmount : 256
         * TotalOver5 : 0
         * TotalOver15 : 4
         * TotalOver30 : 11
         * TotalRec : 15
         */

        private int TotalAmount;
        private int TotalOver5;
        private int TotalOver15;
        private int TotalOver30;
        private int TotalRec;

        public int getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(int TotalAmount) {
            this.TotalAmount = TotalAmount;
        }

        public int getTotalOver5() {
            return TotalOver5;
        }

        public void setTotalOver5(int TotalOver5) {
            this.TotalOver5 = TotalOver5;
        }

        public int getTotalOver15() {
            return TotalOver15;
        }

        public void setTotalOver15(int TotalOver15) {
            this.TotalOver15 = TotalOver15;
        }

        public int getTotalOver30() {
            return TotalOver30;
        }

        public void setTotalOver30(int TotalOver30) {
            this.TotalOver30 = TotalOver30;
        }

        public int getTotalRec() {
            return TotalRec;
        }

        public void setTotalRec(int TotalRec) {
            this.TotalRec = TotalRec;
        }
    }

    public static class DetailBean {
        /**
         * UserId : 20001210
         * OrderId : 02200124500211
         * Amount : 23.72
         * AmountDescribe : 本金:20.00+违约金:3.72
         * CargoName : 手机
         * TotalFee : 10
         * CargoFee : 10
         * AdvanceTransitFee : 0
         * Number : 2
         * Overdue : 1
         * Describe : 已欠款88天
         * ReceiveTel :
         * ReceiveInfo :
         * SignDate : 2017-09-03 17:45:09
         * ReceiveAddress : 浙江省杭州市桐庐县繁华大道1588号东城汽配城D区8888号
         * PaperNumber :
         * ConsignDate : 2017-09-03 16:57:21
         */

        private int UserId;
        private String OrderId;
        private double Amount;
        private String AmountDescribe;
        private String CargoName;
        private int TotalFee;
        private int CargoFee;
        private int AdvanceTransitFee;
        private int Number;
        private int Overdue;
        private String Describe;
        private String ReceiveTel;
        private String ReceiveInfo;
        private String SignDate;
        private String ReceiveAddress;
        private String PaperNumber;
        private String ConsignDate;
        private String WaybillCargoNo;

        private String SendBranchName;

        public String getSendBranchName() {
            return SendBranchName;
        }

        public void setSendBranchName(String sendBranchName) {
            SendBranchName = sendBranchName;
        }

        public String getWaybillCargoNo() {
            return WaybillCargoNo;
        }

        public void setWaybillCargoNo(String waybillCargoNo) {
            WaybillCargoNo = waybillCargoNo;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String OrderId) {
            this.OrderId = OrderId;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }

        public String getAmountDescribe() {
            return AmountDescribe;
        }

        public void setAmountDescribe(String AmountDescribe) {
            this.AmountDescribe = AmountDescribe;
        }

        public String getCargoName() {
            return CargoName;
        }

        public void setCargoName(String CargoName) {
            this.CargoName = CargoName;
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

        public int getAdvanceTransitFee() {
            return AdvanceTransitFee;
        }

        public void setAdvanceTransitFee(int AdvanceTransitFee) {
            this.AdvanceTransitFee = AdvanceTransitFee;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public int getOverdue() {
            return Overdue;
        }

        public void setOverdue(int Overdue) {
            this.Overdue = Overdue;
        }

        public String getDescribe() {
            return Describe;
        }

        public void setDescribe(String Describe) {
            this.Describe = Describe;
        }

        public String getReceiveTel() {
            return ReceiveTel;
        }

        public void setReceiveTel(String ReceiveTel) {
            this.ReceiveTel = ReceiveTel;
        }

        public String getReceiveInfo() {
            return ReceiveInfo;
        }

        public void setReceiveInfo(String ReceiveInfo) {
            this.ReceiveInfo = ReceiveInfo;
        }

        public String getSignDate() {
            return SignDate;
        }

        public void setSignDate(String SignDate) {
            this.SignDate = SignDate;
        }

        public String getReceiveAddress() {
            return ReceiveAddress;
        }

        public void setReceiveAddress(String ReceiveAddress) {
            this.ReceiveAddress = ReceiveAddress;
        }

        public String getPaperNumber() {
            return PaperNumber;
        }

        public void setPaperNumber(String PaperNumber) {
            this.PaperNumber = PaperNumber;
        }

        public String getConsignDate() {
            return ConsignDate;
        }

        public void setConsignDate(String ConsignDate) {
            this.ConsignDate = ConsignDate;
        }
    }
}
