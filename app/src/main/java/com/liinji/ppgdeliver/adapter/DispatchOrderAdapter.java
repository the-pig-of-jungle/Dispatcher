package com.liinji.ppgdeliver.adapter;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.ReDispatchOrder;

import java.util.List;

/**
 * Created by 朱志强 on 2017/7/31.
 */

public class DispatchOrderAdapter extends BaseQuickAdapter<ReDispatchOrder> {


    public DispatchOrderAdapter(List<ReDispatchOrder> data) {
        super(R.layout.dispatch_order, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, ReDispatchOrder order) {
        baseViewHolder.setText(R.id.order_number, "运单号：" + order.getWaybillNo())
                .setText(R.id.receiver, "收货人：" + order.getReceiver())
                .setText(R.id.receiver_tel, order.getReceiveTel())
                .setText(R.id.total, order.getTotalPieces() + "件")
                .setText(R.id.sender, "发货人：" + order.getShipper())
                .setText(R.id.sender_tel, order.getDeliveryTel())
                .addOnClickListener(R.id.root);
        CheckBox checkBox = baseViewHolder.getView(R.id.check_mark);
        checkBox.setChecked(order.isChecked());

    }
}
