package com.liinji.ppgdeliver.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.Message;
import com.liinji.ppgdeliver.configure.MyApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
public class MessageAdapter extends BaseQuickAdapter<Message> {

    public MessageAdapter(List<Message> data) {
        super(R.layout.message_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Message message) {
        baseViewHolder.setText(R.id.message_time,message.getCreateTime())
                .setText(R.id.msg_title,"【" + message.getTitle() + "】")
                .setText(R.id.msg_content,message.getPrimaryContent());

        int colorRes = message.isIsRead() ? R.color.c_light_gray : R.color.colorPrimary;
        int drawableRes = message.isIsRead() ? R.drawable.message_more : R.drawable.wlxx_more;
        Drawable d = ContextCompat.getDrawable(MyApplication.sContext,drawableRes);
        d.setBounds(0,0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
        TextView seeDetail = (TextView) baseViewHolder.getView(R.id.see_detail);
        seeDetail.setTextColor(ContextCompat.getColor(MyApplication.sContext,colorRes));

        seeDetail.setCompoundDrawables(null,null,d,null);
        baseViewHolder.addOnClickListener(R.id.see_detail);
    }
}
