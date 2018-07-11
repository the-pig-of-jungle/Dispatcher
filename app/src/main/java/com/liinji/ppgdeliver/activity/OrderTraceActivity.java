package com.liinji.ppgdeliver.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.TraceAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.TraceInfo;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.widget.CusSwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/3/20.
 */
public class OrderTraceActivity extends BaseLightStatusBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.cusswipeRefreshlayout)
    CusSwipeRefreshLayout mCusswipeRefreshlayout;
    @BindView(R.id.empty_logo)
    ImageView mEmptyLogo;
    @BindView(R.id.empty_tip)
    RelativeLayout mEmptyTip;
    @BindView(R.id.order)
    TextView mOrderTextView;
    @BindView(R.id.space)
    View space;
    private String mOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("物流详情");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_order_trace;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mOrder = getIntent().getStringExtra("order");
        mOrderTextView.append(mOrder);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCusswipeRefreshlayout.setOnRefreshListener(this);
        HttpTools.getOrderTraceInfo(this, mOrder, this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        mCusswipeRefreshlayout.setRefreshing(false);
        List<TraceInfo.ListTrackingBean> beanList = ((TraceInfo) bean.getReturnData()).getListTracking();
        mRecyclerView.setAdapter(new TraceAdapter(beanList));
        if (beanList.size() == 0) {
            mOrderTextView.setVisibility(View.GONE);
            mEmptyTip.setVisibility(View.VISIBLE);
            space.setVisibility(View.GONE);
        } else {
            mEmptyTip.setVisibility(View.GONE);
            mOrderTextView.setVisibility(View.VISIBLE);
            space.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        mCusswipeRefreshlayout.setRefreshing(false);
        mEmptyTip.setVisibility(View.VISIBLE);
        mOrderTextView.setVisibility(View.GONE);
        space.setVisibility(View.GONE);
        setResult(RESULT_OK);
    }

    @Override
    public void onRefresh() {
        HttpTools.getOrderTraceInfo(this, mOrder, this);
    }
}

