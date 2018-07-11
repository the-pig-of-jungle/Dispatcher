package com.liinji.ppgdeliver.sendreport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.SendReportResult;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lunger on 7/13 0013 16:06
 */
public class LvInfoAdapter extends BaseAdapter {
    private Context context;
    private List<SendReportResult.ListWaybillBean> mData = new ArrayList<>();

    public LvInfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public SendReportResult.ListWaybillBean getItem(int position) {
        return mData.get(position);
    }

    public void addData(List<SendReportResult.ListWaybillBean> tableLines) {
        mData.addAll(tableLines);
        notifyDataSetChanged();
    }
    public void clearAndAddData(List<SendReportResult.ListWaybillBean> tableLines){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_good_info, null);
            holder.mSendDate = (TextView) convertView.findViewById(R.id.send_date);
            holder.mYunFee = (TextView) convertView.findViewById(R.id.deliver_fee);

            holder.mDaishouFee = (TextView) convertView.findViewById(R.id.daishou_fee);

            holder.mSender = (TextView) convertView.findViewById(R.id.sender);
            holder.mReceiver = (TextView) convertView.findViewById(R.id.receiver);

            holder.mJiesuanType = (TextView) convertView.findViewById(R.id.jiesuan_type);
            holder.mIfConsign = (TextView) convertView.findViewById(R.id.consign);
            holder.mPieces = (TextView) convertView.findViewById(R.id.pieces);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SendReportResult.ListWaybillBean tableLine = getItem(position);
        holder.mSendDate.setText(tableLine.getConsignDate());
        holder.mPieces.setText(tableLine.getNumber() + "");
        holder.mSender.setText(tableLine.getShipper());
        holder.mReceiver.setText(tableLine.getReceiver());

        holder.mYunFee.setText(Utils.getCurrencyStr(tableLine.getFreightAmout()));
        holder.mDaishouFee.setText(Utils.getCurrencyStr(tableLine.getCollectAmount()));
        holder.mJiesuanType.setText(tableLine.getSettleWay());
        holder.mIfConsign.setText(tableLine.getSignDesc());

        return convertView;
    }
}

class ViewHolder {
    TextView mPieces;
    TextView mSender;
    TextView mReceiver;
    TextView mYunFee;
    TextView mDaishouFee;

    TextView mJiesuanType;

    TextView mSendDate;

    TextView mIfConsign;

}