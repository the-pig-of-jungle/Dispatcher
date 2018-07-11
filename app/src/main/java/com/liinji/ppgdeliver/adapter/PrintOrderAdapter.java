package com.liinji.ppgdeliver.adapter;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.PrintOrders;

import java.util.List;

/**
 * Created by 朱志强 on 2017/8/22.
 */

public class PrintOrderAdapter extends BaseQuickAdapter<PrintOrders.ReturnDataBean> {



    public PrintOrderAdapter(List<PrintOrders.ReturnDataBean> data) {
        super(R.layout.print_order,data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, final PrintOrders.ReturnDataBean order) {


        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("打印序号：");
        spannableStringBuilder.append(String.valueOf(baseViewHolder.getAdapterPosition() + 1));
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(20,true),5,spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        baseViewHolder.setText(R.id.order_number,"运单号：" + order.getWaybillNo())
                .setText(R.id.receiver,order.getReceiver())
                .setText(R.id.total,order.getTotalPieces() + "件")
                .setText(R.id.sender,order.getShipper())
                .setText(R.id.sent_time,order.getConsignDate().trim())
                .setChecked(R.id.check_mark,order.isChecked())
                .setText(R.id.print_sequence,spannableStringBuilder)
                .setText(R.id.handover,"交接单号：" + order.getTripNo())
                .addOnClickListener(R.id.root);


        if (order.getReceiver().length() <= 12) {
            ((TextView) baseViewHolder.getView(R.id.receiver)).append("      " + order.getReceiveTel());
        } else {
            ((TextView) baseViewHolder.getView(R.id.receiver)).append("\n" + order.getReceiveTel());
        }

        if (order.getShipper().length() <= 12){
            ((TextView) baseViewHolder.getView(R.id.sender)).append("      " + order.getDeliveryTel());
        }else {
            ((TextView) baseViewHolder.getView(R.id.sender)).append("\n" + order.getDeliveryTel());
        }
        CheckBox checkBox = baseViewHolder.getView(R.id.check_mark);
        checkBox.setChecked(order.isChecked());

    }


}
