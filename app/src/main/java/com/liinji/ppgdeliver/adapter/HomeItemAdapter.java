package com.liinji.ppgdeliver.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.HomeItem;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */
public class HomeItemAdapter extends BaseQuickAdapter<HomeItem> {
    public HomeItemAdapter(List<HomeItem> data) {
        super(R.layout.home_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeItem o) {
        baseViewHolder.setText(R.id.item_label, o.getLabel())
                .setText(R.id.item_badge, o.getBadgeNum() + "")
                .setAlpha(R.id.item_badge, o.getBadgeNum() == 0 ? 0 : 1)
                .setImageResource(R.id.item_icon, o.getIconRes());

    }


    public void setSendMark(int waitToSend) {
        getData().get(0).setBadgeNum(waitToSend);
        notifyDataSetChanged();
    }

    public void setDebitMark(int waitToDebit) {
        getData().get(1).setBadgeNum(waitToDebit);
        notifyDataSetChanged();
    }
}
