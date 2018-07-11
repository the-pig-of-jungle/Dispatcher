package com.liinji.ppgdeliver.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    //用来存放子View
    private SparseArray<View> mSparseArray;

    public ViewHolder(View itemView) {
        super(itemView);
        mSparseArray = new SparseArray<>();
    }

    //通过Id获取View
    public <T extends View> T getView(int ViewId){
         //先从集合中寻找
        View view = mSparseArray.get(ViewId);
        if(view == null){
            //通过Id查找，保存在集合中
            view = itemView.findViewById(ViewId);
            mSparseArray.put(ViewId,view);
        }
        return (T) view;
    }

    //设置textview文本
    public ViewHolder setText(int ViewId, CharSequence text){
        TextView view =  getView(ViewId);
        view.setText(text);
        return this;
    }

    //设置View的Visibility
    public ViewHolder setVisibility(int ViewId, int Visibility){
        getView(ViewId).setVisibility(Visibility);
        return this;
    }

    /**
     * 设置条目点击事件
     */
    public void setOnIntemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

    /**
     * 设置条目长按事件
     */
    public void setOnIntemLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);
    }


}
