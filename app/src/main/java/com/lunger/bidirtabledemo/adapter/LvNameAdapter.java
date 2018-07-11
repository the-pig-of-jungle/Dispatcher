package com.lunger.bidirtabledemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.TableInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lunger on 7/13 0013 16:05
 */
public class LvNameAdapter extends BaseAdapter {
    private Context context;
    private List<TableInfo.ReturnData1Bean> mTableLines = new ArrayList<>();

    public LvNameAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mTableLines.size();
    }

    @Override
    public TableInfo.ReturnData1Bean getItem(int position) {
        return mTableLines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addData(List<TableInfo.ReturnData1Bean> tableLines){
        mTableLines.addAll(tableLines);
        notifyDataSetChanged();
    }

    public void clearAndAddData(List<TableInfo.ReturnData1Bean> tableLines){
        mTableLines.clear();
        addData(tableLines);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_good_name_src, null);
            holder.tv_goodname = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_goodname.setText(getItem(position).getWaybillNo() + "  （" + getItem(position).getTotalPieces() + "件）");
        return convertView;
    }
    class ViewHolder{
        TextView tv_goodname;
    }
}