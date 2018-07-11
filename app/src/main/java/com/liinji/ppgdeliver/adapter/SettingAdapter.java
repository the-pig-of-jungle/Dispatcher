package com.liinji.ppgdeliver.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.List;


/**
 * Created by Administrator on 2017/4/10.
 */
public class SettingAdapter extends BaseQuickAdapter<String> {
    public SettingAdapter(List<String> data) {
        super(R.layout.setting_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.setting_item,s);
        baseViewHolder.setText(R.id.version_mark, s.equals("版本更新") ? Utils.appVersionName() : "");
    }
}
