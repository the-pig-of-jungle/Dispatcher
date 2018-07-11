package com.liinji.ppgdeliver.sendreport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.SendReportResult;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lunger on 7/13 0013 16:05
 */
public class LvNameAdapter extends BaseAdapter {
    private Context context;
    private List<SendReportResult.ListWaybillBean> mTableLines = new ArrayList<>();

    public LvNameAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mTableLines.size();
    }

    @Override
    public SendReportResult.ListWaybillBean getItem(int position) {
        return mTableLines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addData(List<SendReportResult.ListWaybillBean> tableLines){
        mTableLines.addAll(tableLines);
        notifyDataSetChanged();
    }

    public void clearAndAddData(List<SendReportResult.ListWaybillBean> tableLines){
        mTableLines.clear();
        addData(tableLines);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_good_name, null);
            holder.tv_goodname = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_goodname.setText(getItem(position).getWaybillNo());
        return convertView;
    }
    class ViewHolder{
        TextView tv_goodname;
    }
}