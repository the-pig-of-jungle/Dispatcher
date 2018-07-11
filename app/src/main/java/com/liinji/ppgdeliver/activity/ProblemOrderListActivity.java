package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.ProblemOrderItemAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.ProblemOrderItem;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ProblemOrderListActivity extends BaseRecycleviewActivity {
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.to_top_fb)
    FloatingActionButton mToTopFb;
    private boolean mNotScroll;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mNotScroll) {
                mToTopFb.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("问题记录");
    }

    @Override
    protected void initRestData() {

    }

    @Override
    protected void initRestView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                mNotScroll = true;
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    recyclerView.postDelayed(mRunnable,1200);
                    return;
                }
                mNotScroll = false;

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mToTopFb.setVisibility(View.VISIBLE);


            }
        });


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_problem_order_list;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ProblemOrderItemAdapter(new ArrayList<ProblemOrderItem>());
    }

    @Override
    protected void getRecyclerViewNetData() {
        HttpTools.getProblemOrderList(this,this,pageIndex,pagesize);
    }

    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.GET_PROBLEM_ORDER_LIST;
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        if (bean.getReturnTotalRecords().equals("0")) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mToTopFb.setVisibility(View.GONE);
    }

    @OnClick(R.id.to_top_fb)
    public void onClick() {
        recyclerView.scrollToPosition(0);
    }


    @Override
    protected void onBaseItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        super.onBaseItemClick(baseQuickAdapter, view, i);
        String problemId = ((ProblemOrderItem) baseQuickAdapter.getItem(i)).getProblemId() + "";
        Intent intent = new Intent(this,ProblemOrderDetailActivity.class);
        intent.putExtra("problem_id",problemId);
        startActivity(intent);
    }
}
