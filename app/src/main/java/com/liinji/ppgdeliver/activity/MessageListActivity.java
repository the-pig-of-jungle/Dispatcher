package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.MessageAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.Message;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/27.
 */
public class MessageListActivity extends BaseLightRecyclerviewActivity {

    private static final int REQ_SEE_MSG = 0x001;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.to_top_fb)
    FloatingActionButton toTopFb;
    @BindView(R.id.ignore_unread)
    TextView ignoreUnread;
    private boolean mNotScroll;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mNotScroll) {
                toTopFb.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("我的消息");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                mNotScroll = true;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    recyclerView.postDelayed(mRunnable, 1200);
                    return;
                }
                mNotScroll = false;

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                toTopFb.setVisibility(View.VISIBLE);


            }
        });
    }

    @Override
    protected void initRestData() {

    }

    @Override
    protected void initRestView() {
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(MessageListActivity.this, MessageDetailActivity.class);
                intent.putExtra("msg_id", ((Message) baseQuickAdapter.getData().get(i)).getId());
                intent.putExtra("item_id", i);
                startActivityForResult(intent, REQ_SEE_MSG);
            }
        });

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_message_list;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new MessageAdapter(new ArrayList<Message>());
    }

    @Override
    protected void getRecyclerViewNetData() {
        HttpTools.msgList(this, pageIndex, pagesize, this);
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        switch (type){
            case HttpRequestType.MSG_LIST:
                super.onSuccess(type, bean);
                if (((List<Message>) bean.getReturnData()).isEmpty()) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
                break;
            case HttpRequestType.IGNORE_UNREAD_MSG:
                onRefresh();
                break;
        }
    }

    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.MSG_LIST;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_SEE_MSG && resultCode == RESULT_OK) {
            int id = data.getIntExtra("item_id", -1);
            ((BaseQuickAdapter<Message>) recyclerView.getAdapter()).getData().get(id).setIsRead(true);
            ((BaseQuickAdapter<Message>) recyclerView.getAdapter()).notifyItemChanged(id);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        toTopFb.setVisibility(View.GONE);

    }

    @OnClick(R.id.to_top_fb)
    public void onClick() {
        recyclerView.scrollToPosition(0);
    }


    @OnClick(R.id.ignore_unread)
    public void onViewClicked() {
        HttpTools.ignoreUnreadMsg(this,this);
    }
}
