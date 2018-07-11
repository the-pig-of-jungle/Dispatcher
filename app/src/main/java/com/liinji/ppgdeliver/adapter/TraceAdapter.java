package com.liinji.ppgdeliver.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.TraceInfo;

import java.util.List;


/**
 * Created by Administrator on 2017/3/21.
 */
public class TraceAdapter extends BaseQuickAdapter<TraceInfo.ListTrackingBean> {
    public TraceAdapter(List<TraceInfo.ListTrackingBean> data) {
        super(R.layout.trace_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TraceInfo.ListTrackingBean traceItem) {
        baseViewHolder.setText(R.id.trace_info_time,traceItem.getCreateTime())
                .setText(R.id.trace_info_position,traceItem.getDescription());

        if (baseViewHolder.getAdapterPosition() == 0){
            ((ImageView) baseViewHolder.getView(R.id.imgv_ball)).setImageResource(R.drawable.green_ball);
            ((ImageView) baseViewHolder.getView(R.id.imgv_stick)).setImageResource(R.drawable.green_rect);
        }else {
            ((ImageView) baseViewHolder.getView(R.id.imgv_ball)).setImageResource(R.drawable.gray_ball);
            ((ImageView) baseViewHolder.getView(R.id.imgv_stick)).setImageResource(R.drawable.gray_rect);
        }

        if (baseViewHolder.getAdapterPosition() == getData().size() - 1){
            ((ImageView) baseViewHolder.getView(R.id.imgv_stick)).setImageDrawable(null);
        }
    }
}
