package com.liinji.ppgdeliver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private int layoutid;
    private Context mContext;
    private List<T> mShowItems;

    public RecyclerAdapter(Context paramContext, List<T> paramList, int paramInt) {
        mContext = paramContext;
        mShowItems = paramList;
        layoutid = paramInt;
    }

    public void addDatas(List<T> paramList) {
        mShowItems = paramList;
        notifyDataSetChanged();
    }

    public abstract void convert(ViewHolder paramViewHolder, T paramT, int LayId);

    public int getItemCount() {
        if (mShowItems == null)
            return 0;
        return mShowItems.size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        convert(paramViewHolder, mShowItems.get(paramInt), paramInt);
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(layoutid, paramViewGroup, false));
    }

    public T getHolderByPosition(int position) {
        return mShowItems.get(position);
    }
}