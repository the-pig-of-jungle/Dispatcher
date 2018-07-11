package com.liinji.ppgdeliver.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.TripNos;

import java.util.List;

/**
 * Created by 朱志强 on 2017/9/13.
 */

public class TripNoAdapter extends BaseQuickAdapter<TripNos.ReturnDataBean> {

    public TripNoAdapter(List<TripNos.ReturnDataBean> data) {
        super(R.layout.trip_no_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TripNos.ReturnDataBean s) {
        baseViewHolder.setText(R.id.trip_no, s.getTripNo())
                .setChecked(R.id.trip_selector, s.isChecked());
    }

}
