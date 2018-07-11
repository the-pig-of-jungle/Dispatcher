package com.liinji.ppgdeliver.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.ProcessDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 朱志强 on 2017/6/16.
 */

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ViewHolder> {

    private List<ProcessDetail.ReplyListBean> mData = new ArrayList<>();

    public ProcessAdapter addData(List<ProcessDetail.ReplyListBean> data){
        mData.addAll(data);
        notifyDataSetChanged();

        return this;
    }

    public ProcessAdapter addData(ProcessDetail.ReplyListBean processItem){
        mData.add(processItem);
        notifyDataSetChanged();
        return this;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.process_item_01,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProcessDetail.ReplyListBean processItem = mData.get(position);
        holder.mCircle.setImageResource(R.drawable.wtxq_current_circle);

        holder.mText01.setText("[" + processItem.getReplyBranchName() + "] " + processItem.getReplyUserName() + " " + processItem.getReplyStatusRemark() );
        holder.mText02.setText(processItem.getHandleSuggestion());
        holder.mText03.setText(processItem.getReplyTime());



        if (position == mData.size() - 1){
            holder.mBottomLine.setVisibility(View.INVISIBLE);
        }else {
            holder.mBottomLine.setVisibility(View.VISIBLE);
        }

        if (processItem.getReplyStatusRemark().equals("处理中")){

            holder.mCircle.setImageResource(R.drawable.wtxq_circle);
        }else {

            holder.mCircle.setImageResource(R.drawable.wtxq_current_circle);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mCircle;
        public TextView mText01;
        public TextView mText02;
        public TextView mText03;
        public View mBottomLine;

        public ViewHolder(final View itemView) {
            super(itemView);
            mCircle = (ImageView) itemView.findViewById(R.id.circle);
            mText01 = (TextView) itemView.findViewById(R.id.text_line_01);
            mText02 = (TextView) itemView.findViewById(R.id.text_line_02);
            mText03 = (TextView) itemView.findViewById(R.id.text_line_03);
            mBottomLine = itemView.findViewById(R.id.bottom_line);
        }
    }

}
