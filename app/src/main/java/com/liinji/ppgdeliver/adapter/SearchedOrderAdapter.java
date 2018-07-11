package com.liinji.ppgdeliver.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liinji.ppgdeliver.bean.SearchedOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 朱志强 on 2017/8/22.
 */

public class SearchedOrderAdapter extends BaseAdapter {

    private List<SearchedOrder> mSearchedOrders = new ArrayList<>();

    @Override
    public int getCount() {
        return mSearchedOrders.size();
    }

    @Override
    public SearchedOrder getItem(int position) {
        return mSearchedOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        }

        ((TextView) convertView).setText(getItem(position).getWaybillNo() + "  " + getItem(position).getReceiver());

        return convertView;
    }


    public List<SearchedOrder> getData(){
        return mSearchedOrders;
    }

    public void resetData(List<SearchedOrder> searchedOrders){
        mSearchedOrders.clear();
        mSearchedOrders.addAll(searchedOrders);
        notifyDataSetChanged();
    }

    public void clear() {
        mSearchedOrders.clear();
        notifyDataSetChanged();
    }
}
