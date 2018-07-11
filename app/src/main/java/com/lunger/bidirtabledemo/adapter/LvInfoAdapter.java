package com.lunger.bidirtabledemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.TableInfo;
import com.liinji.ppgdeliver.print.PrintInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lunger on 7/13 0013 16:06
 */
public class LvInfoAdapter extends BaseAdapter {
    private Context context;
    private List<TableInfo.ReturnData1Bean> mData = new ArrayList<>();

    public LvInfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public TableInfo.ReturnData1Bean getItem(int position) {
        return mData.get(position);
    }

    public void addData(List<TableInfo.ReturnData1Bean> tableLines) {
        mData.addAll(tableLines);
        notifyDataSetChanged();
    }

    public void clearAndAddData(List<TableInfo.ReturnData1Bean> tableLines) {
        mData.clear();
        addData(tableLines);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_good_info_src, null);
            holder.tv_barcode = (TextView) convertView.findViewById(R.id.send_date);
            holder.tv_category = (TextView) convertView.findViewById(R.id.deliver_fee);

            holder.tv_supplier = (TextView) convertView.findViewById(R.id.daishou_fee);
            holder.tv_sale_money = (TextView) convertView.findViewById(R.id.daishou_fee_pay_type);
            holder.tv_income_money = (TextView) convertView.findViewById(R.id.status);
            holder.paiedDaishou = (TextView) convertView.findViewById(R.id.paied_daishou_fee);
            holder.paiedFreight = (TextView) convertView.findViewById(R.id.deliver_fee_paid);
            holder.sender = (TextView) convertView.findViewById(R.id.sender);
            holder.receiver = (TextView) convertView.findViewById(R.id.receiver);
            holder.yunFeePayTime = (TextView) convertView.findViewById(R.id.deliver_fee_paid_time);
            holder.daishouPayTime = (TextView) convertView.findViewById(R.id.paied_daishou_fee_time);

            holder.signer = (TextView) convertView.findViewById(R.id.signer);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TableInfo.ReturnData1Bean tableLine = getItem(position);
        holder.tv_barcode.setText(tableLine.getConsignDate());
        holder.paiedFreight.setText(PrintInfo.processFee(tableLine.getPickUpPayAmountPaid()));
        holder.tv_category.setText(PrintInfo.processFee(tableLine.getPickUpPayAmount()));
        holder.tv_supplier.setText(PrintInfo.processFee(tableLine.getCollectionTradeCharges()));
        float daishou = tableLine.getCollectionTradeCharges();
        float daishouPaied = tableLine.getCollectionTradeChargesPaid();
        float yunfee = tableLine.getPickUpPayAmount();
        float yunfeePaid = tableLine.getPickUpPayAmountPaid();
        if (tableLine.getPayType().equals("现金支付")) {
            if ((daishou != 0 && daishouPaied == 0) || (yunfee != 0 && yunfeePaid == 0)) {
                holder.tv_sale_money.setText("欠现金");
            } else {

                holder.tv_sale_money.setText(tableLine.getPayType());
            }

        } else {
            holder.tv_sale_money.setText(tableLine.getPayType());
        }

        holder.tv_income_money.setText(tableLine.getCollectionTradeChargesStatus());

        holder.paiedDaishou.setText(PrintInfo.processFee(tableLine.getCollectionTradeChargesPaid()));

        holder.sender.setText(processName(tableLine.getShipper()));

        holder.receiver.setText(processName(tableLine.getReceiver()));

        holder.yunFeePayTime.setText(tableLine.getPickUpPayTime());

        holder.daishouPayTime.setText(tableLine.getCollectionTradeChargesPayTime());

        holder.signer.setText(processName(tableLine.getSigner()));

        return convertView;
    }


    public String processName(String src){
        if (TextUtils.isEmpty(src)){
            src = "";
        }
        if (src.length() < 15){
            return src;
        }else {
            src = src.substring(0,13) + "...";
        }

        return src;
    }
}

class ViewHolder {

    TextView tv_barcode;
    TextView tv_category;
    TextView tv_supplier;
    TextView tv_sale_money;
    TextView tv_income_money;

    TextView paiedDaishou;

    TextView paiedFreight;

    TextView sender;

    TextView receiver;

    TextView yunFeePayTime;

    TextView daishouPayTime;

    TextView signer;

}