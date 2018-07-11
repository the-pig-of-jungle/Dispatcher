package com.liinji.ppgdeliver.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BillRecord;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import java.util.List;


/**
 * Created by Administrator on 2017/3/25.
 */
public class BillRecordAdapter extends BaseQuickAdapter<BillRecord> {
    public BillRecordAdapter(List<BillRecord> data) {
        super(R.layout.item_bill_record,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BillRecord billRecord) {

        String status = "";
        String amountMark = "";
        int bg = 0;
        String time = "";

        Logger.t("记录");
        Logger.d(billRecord.getStatus());

        if (billRecord.getStatus().equals("1")){
            status = "交账金额\n (已申请)";
            amountMark = "已申请";
            bg = R.drawable.record_bg1;
            time = billRecord.getApplyDate();
        }else if (billRecord.getStatus().equals("2")){
            status = "交账金额\n (已完成)";
            amountMark = "已收款";
            bg = R.drawable.jl_bg;
            time = billRecord.getVerifyDate();
        }else if (billRecord.getStatus().equals("3")){
            status = "交账金额\n (未通过)";
            amountMark = "未通过";
            bg = R.drawable.record_bg2;
            time = billRecord.getVerifyDate();
        }

        baseViewHolder.setText(R.id.record_time,time)
                .setText(R.id.status,status)
                .setText(R.id.amount_mark,amountMark)
                .setBackgroundRes(R.id.record_type,bg)
                .setText(R.id.amount, Utils.getCurrencyStr(billRecord.getApplyAmount()));

    }
}
