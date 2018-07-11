package com.liinji.ppgdeliver.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BillOrder;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.List;

import static com.liinji.ppgdeliver.bean.BillOrder.ITEM_TYPE_COMPLETE;
import static com.liinji.ppgdeliver.bean.BillOrder.ITEM_TYPE_DAISHOU;

/**
 * Created by 朱志强 on 2017/7/18.
 */

public class BillOrderAdapter extends BaseMultiItemQuickAdapter<BillOrder> {

    public BillOrderAdapter(List<BillOrder> data) {
        super(data);
        addItemType(ITEM_TYPE_COMPLETE, R.layout.new_order_item);
        addItemType(BillOrder.ITEM_TYPE_DAISHOU, R.layout.new_qiankuan_item);

    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, BillOrder billOrder) {
        switch (billOrder.getItemType()) {
            case ITEM_TYPE_COMPLETE:
                if (billOrder.getShipper().length() <= 12){
                    billOrder.setShipper(billOrder.getShipper() + "      " + billOrder.getDeliveryTel());
                }else {
                    billOrder.setShipper(billOrder.getShipper() + "\n" + billOrder.getDeliveryTel());
                }
                if (billOrder.getReceiver().length() <= 12) {
                    billOrder.setReceiver(billOrder.getReceiver() +  "      " + billOrder.getReceiveTel());

                } else {
                    billOrder.setReceiver(billOrder.getReceiver() +  "\n" + billOrder.getReceiveTel());
                }
                baseViewHolder.setText(R.id.order_number, billOrder.getWaybillNo())
                        .setText(R.id.goods_name, billOrder.getArticleName())
                        .setText(R.id.receiver, billOrder.getReceiver())

                        .setText(R.id.address, billOrder.getReceiveAddress())
                        .setText(R.id.time, "签收时间：" + billOrder.getSignDate())
                        .setText(R.id.sender, billOrder.getShipper())
                        .setText(R.id.mailing_branch_name,billOrder.getDepartureBranchName())
                        .setText(R.id.order_paper_number,billOrder.getPaperNumber())
                        .setText(R.id.deliver_fee, Utils.getCurrencyStr(billOrder.getPickUpPayAmount()))
                        .setText(R.id.daishou, Utils.getCurrencyStr(billOrder.getCollectionTradeCharges()))
                        .setText(R.id.goods_count, "共" + billOrder.getTotalPieces() + "件  合计: ")
                        .setText(R.id.total_fee, Utils.getCurrencyStr(billOrder.getCollectionTradeCharges() + billOrder.getPickUpPayAmount()))
                        .setText(R.id.goods_name_number,billOrder.getWaybillCargoNo())
                        .addOnClickListener(R.id.see_detail)
                        .addOnClickListener(R.id.call_phone);
                baseViewHolder.getView(R.id.send_time_line).setVisibility(View.VISIBLE);




                baseViewHolder.setText(R.id.send_time,billOrder.getConsignDate());
                baseViewHolder.getView(R.id.order_paper_number_line).setVisibility(TextUtils.isEmpty(billOrder.getPaperNumber()) ? View.GONE : View.VISIBLE);
                baseViewHolder.getView(R.id.show_cut).setVisibility(View.INVISIBLE);
                baseViewHolder.getView(R.id.sign_mark).setVisibility(billOrder.getSignStatus() == 1 ? View.VISIBLE : View.INVISIBLE);
                baseViewHolder.getView(R.id.jijie_mark).setVisibility(billOrder.getIsSendAndLoad() == 1 ? View.VISIBLE : View.INVISIBLE);

                baseViewHolder.setText(R.id.order_type, "已完成");
                ((TextView) baseViewHolder.getView(R.id.order_type)).setTextColor(Color.parseColor("#FF4500"));

                baseViewHolder.getView(R.id.goods_name_number_line).setVisibility(TextUtils.isEmpty(billOrder.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);

            case ITEM_TYPE_DAISHOU:

                baseViewHolder.setText(R.id.order_number, billOrder.getWaybillNo())
                        .setText(R.id.goods_name, billOrder.getArticleName())
                        .setText(R.id.goods_count, billOrder.getTotalPieces() + "件")
                        .setText(R.id.receiver, billOrder.getReceiver())
                        .setText(R.id.goods_name_number,billOrder.getWaybillCargoNo())
                        .setText(R.id.address, billOrder.getReceiveAddress())
                        .setText(R.id.time, "签收时间：" + billOrder.getSignDate())
                        .setText(R.id.sender, billOrder.getShipper())
                        .setText(R.id.mailing_branch_name,billOrder.getDepartureBranchName())
                        .setText(R.id.order_paper_number, billOrder.getPaperNumber())
                        .setText(R.id.phone,billOrder.getReceiveTel())
                        .setText(R.id.payback_amount, Utils.getCurrencyStr(billOrder.getPickUpPayAmount()))
                        .setText(R.id.send_time,billOrder.getConsignDate())
                        .setText(R.id.yiqiankuan, billOrder.getDayToSignDate() == 0 ? "今日欠款" : "已欠款" + billOrder.getDayToSignDate() + "天")
                        .addOnClickListener(R.id.see_detail)
                        .addOnClickListener(R.id.call_phone);

                baseViewHolder.getView(R.id.receiver).setVisibility(TextUtils.isEmpty(billOrder.getReceiver()) ? View.GONE : View.VISIBLE);
                baseViewHolder.getView(R.id.order_paper_number_line).setVisibility(TextUtils.isEmpty(billOrder.getPaperNumber()) ? View.GONE : View.VISIBLE);
                baseViewHolder.getView(R.id.call_phone).setVisibility(TextUtils.isEmpty(billOrder.getReceiveTel()) ? View.GONE : View.VISIBLE);
                baseViewHolder.getView(R.id.goods_name_number_line).setVisibility(TextUtils.isEmpty(billOrder.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);
                break;
        }
    }


}
