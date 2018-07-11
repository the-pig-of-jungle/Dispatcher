package com.liinji.ppgdeliver.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.DebitOrder;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.List;


/**
 * Created by Administrator on 2017/4/19.
 */
public class PaybackAdapter extends BaseQuickAdapter<DebitOrder.DetailBean> {
    public PaybackAdapter(List<DebitOrder.DetailBean> data) {
        super(R.layout.pay_back_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DebitOrder.DetailBean detailBean) {
        baseViewHolder.setText(R.id.order_number, detailBean.getOrderId())
                .setText(R.id.goods_name, detailBean.getCargoName())
                .setText(R.id.goods_count, detailBean.getNumber() + "件")
                .setText(R.id.receiver, detailBean.getReceiveInfo())
                .setText(R.id.phone, detailBean.getReceiveTel())
                .setText(R.id.address, detailBean.getReceiveAddress())
                .setText(R.id.time, detailBean.getSignDate())
                .setText(R.id.mailing_branch_name,detailBean.getSendBranchName())
                .setText(R.id.order_paper_number,"纸质单号：" + detailBean.getPaperNumber())
                .setText(R.id.goods_name_number,"货号：" + detailBean.getWaybillCargoNo())
                .setText(R.id.payback_amount, Utils.getCurrencyStr(detailBean.getAmount()))
                .setText(R.id.payback_desc, TextUtils.isEmpty(detailBean.getAmountDescribe()) ? "" : "(" + detailBean.getAmountDescribe() + ")")
                .setText(R.id.yuqi,detailBean.getDescribe().equals("已欠款0天") ? "今日欠款" : detailBean.getDescribe())
                .setText(R.id.send_time,detailBean.getConsignDate())
                .addOnClickListener(R.id.see_detail)
                .addOnClickListener(R.id.call_phone);

        baseViewHolder.getView(R.id.order_paper_number).setVisibility(TextUtils.isEmpty(detailBean.getPaperNumber()) ? View.GONE : View.VISIBLE);
        baseViewHolder.getView(R.id.goods_name_number).setVisibility(TextUtils.isEmpty(detailBean.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);

        baseViewHolder.getView(R.id.phone).setVisibility(TextUtils.isEmpty(detailBean.getReceiveTel()) ? View.GONE : View.VISIBLE);
        baseViewHolder.getView(R.id.call_phone).setVisibility(TextUtils.isEmpty(detailBean.getReceiveTel()) ? View.GONE : View.VISIBLE);
    }
}
