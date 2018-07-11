
package com.liinji.ppgdeliver.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.WaitedOrder;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.List;


/**
 * Created by Administrator on 2017/11/8.
 */

public class OrderToSendAdapter extends BaseQuickAdapter<WaitedOrder.ReturnDataBean> {


    public OrderToSendAdapter(List<WaitedOrder.ReturnDataBean> data) {
        super(R.layout.order_to_send, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WaitedOrder.ReturnDataBean order) {
        baseViewHolder.setText(R.id.order_number, order.getWaybillNo())
                .setText(R.id.goods_name, order.getArticleName())
                .setText(R.id.receiver,order.getReceiver())
                .setText(R.id.address, order.getReceiveAddress())
                .setText(R.id.time, order.getConsignDate())
                .setText(R.id.goods_number,order.getWaybillCargoNo())
                .setText(R.id.deliver_fee, Utils.getCurrencyStr(order.getPickUpPayAmount()))
                .setText(R.id.daishou, Utils.getCurrencyStr(order.getCollectionTradeCharges()))
                .setText(R.id.goods_count, "共" + order.getTotalPieces() + "件  合计: ")
                .setText(R.id.total_fee, Utils.getCurrencyStr(order.getCollectionTradeCharges() + order.getPickUpPayAmount()))
                .setText(R.id.sender, order.getShipper())
                .setText(R.id.mailing_branch_name,order.getSendBranchName())
                .addOnClickListener(R.id.see_detail)

                .addOnClickListener(R.id.offline_pay);

                baseViewHolder.getView(R.id.goods_number_line).setVisibility(TextUtils.isEmpty(order.getWaybillCargoNo()) ? View.GONE : View.VISIBLE);



        if (order.getReceiver().length() <= 12) {
            ((TextView) baseViewHolder.getView(R.id.receiver)).append("      " + order.getReceiverTel());
        } else {
            ((TextView) baseViewHolder.getView(R.id.receiver)).append("\n" + order.getReceiverTel());
        }

        if (order.getShipper().length() <= 12){
            ((TextView) baseViewHolder.getView(R.id.sender)).append("      " + order.getDeliverTel());
        }else {
            ((TextView) baseViewHolder.getView(R.id.sender)).append("\n" + order.getReceiverTel());
        }


        ((TextView) baseViewHolder.getView(R.id.offline_pay)).setText((order.getPickUpPayAmount() == 0 && order.getCollectionTradeCharges() == 0) ? "确认签收" : "线下支付");

        baseViewHolder.getView(R.id.sign_mark).setVisibility(order.getSignStatus() == 1 ? View.VISIBLE : View.INVISIBLE);
        baseViewHolder.getView(R.id.jijie_mark).setVisibility(order.getIsSendAndLoad() == 1 ? View.VISIBLE : View.INVISIBLE);

        baseViewHolder.setText(R.id.order_paper_number, order.getPaperNumber());
        baseViewHolder.getView(R.id.order_paper_number_line).setVisibility(TextUtils.isEmpty(order.getPaperNumber().trim()) ? View.GONE : View.VISIBLE);

        baseViewHolder.setText(R.id.time, "发货时间:" + order.getConsignDate());

    }
}
