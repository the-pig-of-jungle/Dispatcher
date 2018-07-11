package com.liinji.ppgdeliver.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.CompleteOrder;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
public class CompleteOrderAdapter extends BaseQuickAdapter<CompleteOrder.ReturnDataBean> {

    public CompleteOrderAdapter(List<CompleteOrder.ReturnDataBean> data) {
        super(R.layout.order_item, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, CompleteOrder.ReturnDataBean order) {

        baseViewHolder.setText(R.id.order_number, order.getWaybillNo())
                .setText(R.id.goods_name, order.getArticleName())
                .setText(R.id.receiver, order.getReceiver())
                .setText(R.id.address, order.getReceiveAddress())
                .setText(R.id.time, order.getConsignDate())
                .setText(R.id.deliver_fee, Utils.getCurrencyStr(order.getPickUpPayAmount()))
                .setText(R.id.daishou, Utils.getCurrencyStr(order.getCollectionTradeCharges()))
                .setText(R.id.goods_count, "共" + order.getTotalPieces() + "件  合计: ")
                .setText(R.id.total_fee, Utils.getCurrencyStr(order.getCollectionTradeCharges() + order.getPickUpPayAmount()))
                .setText(R.id.sender, order.getShipper())
                .setText(R.id.goods_name_number,order.getWaybillCargoNo())
                .setText(R.id.mailing_branch_name,order.getSendBranchName())
                .addOnClickListener(R.id.see_detail);

        baseViewHolder.getView(R.id.goods_name_number_line).setVisibility(TextUtils.isEmpty(order.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);


        if (order.getReceiver().length() <= 12) {
            ((TextView) baseViewHolder.getView(R.id.receiver)).append("      " + order.getReceiveTel());
        } else {
            ((TextView) baseViewHolder.getView(R.id.receiver)).append("\n" + order.getReceiveTel());
        }

        if (order.getShipper().length() <= 12) {
            ((TextView) baseViewHolder.getView(R.id.sender)).append("      " + order.getDeliveryUserTel());
        } else {
            ((TextView) baseViewHolder.getView(R.id.sender)).append("\n" + order.getDeliveryUserTel());
        }


        baseViewHolder.getView(R.id.sign_mark).setVisibility(order.getSignStatus() == 1 ? View.VISIBLE : View.INVISIBLE);
        baseViewHolder.getView(R.id.jijie_mark).setVisibility(order.getIsSendAndLoad() == 1 ? View.VISIBLE : View.INVISIBLE);


        baseViewHolder.setText(R.id.order_paper_number, order.getPaperNumber());
        baseViewHolder.getView(R.id.order_paper_number_line).setVisibility(TextUtils.isEmpty(order.getPaperNumber().trim()) ? View.GONE : View.VISIBLE);


        baseViewHolder.setText(R.id.order_type, order.getIsReturn() == 1 ? "已退货" : "已完成");
        ((TextView) baseViewHolder.getView(R.id.order_type)).setTextColor(Color.parseColor("#FF4500"));

        baseViewHolder.setText(R.id.time, order.getIsReturn() == 1 ? "签收时间:" : "签收时间：" + order.getSignDate());
        baseViewHolder.getView(R.id.send_time_line).setVisibility(View.VISIBLE);
        ((TextView) baseViewHolder.getView(R.id.send_time)).setText(order.getConsignDate());


    }

}
