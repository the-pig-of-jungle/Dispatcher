package com.liinji.ppgdeliver.bean;

import java.util.List;

/**
 * Created by pig on 2017/12/16.
 */

public class OrderPics {

    private List<ReturnDataBean> ReturnData;

    public List<ReturnDataBean> getReturnData() {
        return ReturnData;
    }

    public void setReturnData(List<ReturnDataBean> ReturnData) {
        this.ReturnData = ReturnData;
    }

    public static class ReturnDataBean {
        /**
         * WaybillId : 8b0d7f36-e66b-44d5-9522-10349a371e5e
         * WaybillNo : 02200734800091
         * ImageUrl : http://testimg.liinji.cn/WaybillSignImg/02200734800091/ae8c0471209647b1a74bb40176533388.jpg
         * CompanyCode : 002
         * CreatedDate : 2017-12-16 08:02:10
         */

        private String WaybillId;
        private String WaybillNo;
        private String ImageUrl;
        private String CompanyCode;
        private String CreatedDate;

        public String getWaybillId() {
            return WaybillId;
        }

        public void setWaybillId(String WaybillId) {
            this.WaybillId = WaybillId;
        }

        public String getWaybillNo() {
            return WaybillNo;
        }

        public void setWaybillNo(String WaybillNo) {
            this.WaybillNo = WaybillNo;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getCompanyCode() {
            return CompanyCode;
        }

        public void setCompanyCode(String CompanyCode) {
            this.CompanyCode = CompanyCode;
        }

        public String getCreatedDate() {
            return CreatedDate;
        }

        public void setCreatedDate(String CreatedDate) {
            this.CreatedDate = CreatedDate;
        }
    }
}
