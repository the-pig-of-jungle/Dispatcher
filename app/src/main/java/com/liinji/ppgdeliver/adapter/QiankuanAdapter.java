package com.liinji.ppgdeliver.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.Qiankuan;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.List;

/**
 * Created by 朱志强 on 2017/6/29.
 */

public class QiankuanAdapter extends BaseQuickAdapter<Qiankuan> {
    public QiankuanAdapter(List<Qiankuan> data) {
        super(R.layout.qiankuan_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Qiankuan qiankuan) {
        int yunFeeStatus = qiankuan.getFreightStatus();
        int daishouStatus = qiankuan.getCollectPayStatus();
        float yunFee = yunFeeStatus == 1 ? 0 : qiankuan.getFreightAmount();
        float daishouFee = daishouStatus == 1 ? 0 : qiankuan.getCollectAmount();



        baseViewHolder.setText(R.id.order_number, qiankuan.getWaybillNo())
                .setText(R.id.goods_name, qiankuan.getCargoName())
                .setText(R.id.goods_count, qiankuan.getNumber() + "件")
                .setText(R.id.receiver, qiankuan.getReceiver())
                .setText(R.id.phone, qiankuan.getReceiveTel())
                .setText(R.id.address, qiankuan.getReceiveAddress())
                .setText(R.id.time, qiankuan.getSignDate())
                .setText(R.id.send_time,qiankuan.getConsignDate())
                .setText(R.id.mailing_branch_name,qiankuan.getSendBranchName())
                .setText(R.id.payback_amount, Utils.getCurrencyStr(yunFee + daishouFee))
                .setText(R.id.yiqiankuan,qiankuan.getOweDay() == 0 ? "今日欠款" : "已欠款" + qiankuan.getOweDay() + "天")
                .setText(R.id.order_paper_number,"纸质单号：" + qiankuan.getPaperNumber())
                .setText(R.id.goods_name_number,"货号：" + qiankuan.getWaybillCargoNo())
                .addOnClickListener(R.id.see_detail)

                .addOnClickListener(R.id.call_phone);



        baseViewHolder.getView(R.id.order_paper_number).setVisibility(TextUtils.isEmpty(qiankuan.getPaperNumber()) ? View.GONE : View.VISIBLE);
        baseViewHolder.getView(R.id.goods_name_number).setVisibility(TextUtils.isEmpty(qiankuan.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);
        baseViewHolder.getView(R.id.phone).setVisibility(TextUtils.isEmpty(qiankuan.getReceiveTel()) ? View.GONE : View.VISIBLE);
        baseViewHolder.getView(R.id.call_phone).setVisibility(TextUtils.isEmpty(qiankuan.getReceiveTel()) ? View.GONE : View.VISIBLE);
    }
}
