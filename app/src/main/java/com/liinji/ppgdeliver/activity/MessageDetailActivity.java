package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.MessageDetail;
import com.liinji.ppgdeliver.http.HttpTools;

import butterknife.BindView;

public class MessageDetailActivity extends BaseLightStatusBarActivity {


    @BindView(R.id.message_title)
    TextView messageTitle;
    @BindView(R.id.message_time)
    TextView messageTime;
    @BindView(R.id.message_content)
    TextView messageContent;


    private int mMsgId;
    private int mItemId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mMsgId = getIntent().getIntExtra("msg_id", -1);
        mItemId = getIntent().getIntExtra("item_id",-1);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();

        toolbar_title.setText("消息详情");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.wlxx_back);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        HttpTools.msgDetail(this, mMsgId, this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    Intent mBackData = new Intent();
    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        MessageDetail messageDetail = (MessageDetail) bean.getReturnData();
        mBackData.putExtra("item_id",mItemId);
        setResult(RESULT_OK,mBackData);
        messageTitle.setText(messageDetail.getTitle());
        messageTime.setText(messageDetail.getCreateTime());
        messageContent.setText(messageDetail.getContent());
    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
    }


}
