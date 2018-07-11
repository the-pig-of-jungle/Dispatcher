package com.liinji.ppgdeliver.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.CompanyBranchInfo;
import com.liinji.ppgdeliver.widget.Frame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 朱志强 on 2017/6/9.
 */

public class PosAdapter extends RecyclerView.Adapter<PosAdapter.ViewHolder> {
    private List<CompanyBranchInfo> mPos = new ArrayList<>(6);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pos_item, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((Frame) holder.itemView).setContent(mPos.get(position).getBranchName());
    }

    @Override
    public int getItemCount() {
        return mPos.size();
    }

    public void addData(CompanyBranchInfo branch) {
        for (int i = 0;i < mPos.size();i++){
            if (mPos.get(i).getBranchCode().equals(branch.getBranchCode())){
                return;
            }
        }
        int posIndex = mPos.size() - 1;
        mPos.add(branch);
        notifyItemInserted(mPos.size() - 1);
        notifyItemRangeChanged(mPos.size() - 1, 1);
    }

    public void removeData(int posIndex) {
        mPos.remove(posIndex);
        notifyItemRemoved(posIndex);
        notifyItemRangeChanged(posIndex,mPos.size() - posIndex);

    }


    public List<CompanyBranchInfo> getDataSet(){
        return mPos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private PosAdapter mPosAdapter;

        public ViewHolder(final View itemView, final PosAdapter adapter) {
            super(itemView);
            mPosAdapter = adapter;
            ((Frame) itemView).setOnCloseClickListener(new Frame.OnCloseClickListener() {
                @Override
                public void onCloseClick(Frame frame) {
                    mPosAdapter.removeData(getAdapterPosition());
                }
            });
        }

    }
}
