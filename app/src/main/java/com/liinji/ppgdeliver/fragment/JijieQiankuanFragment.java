package com.liinji.ppgdeliver.fragment;

/**
 * Created by 朱志强 on 2017/6/29.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.activity.JijieDetailActivity;
import com.liinji.ppgdeliver.adapter.PaybackAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.DebitOrder;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.liinji.ppgdeliver.R.id.recyclerView;

/**
 * Created by 朱志强 on 2017/6/29.
 */

public class JijieQiankuanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.cusswipeRefreshlayout)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.empty_view)
    RelativeLayout mEmptyView;

    @BindView(R.id.to_top_fb)
    FloatingActionButton mToTopFb;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.edt_search)
    EditText mEdtSearch;
    @BindView(R.id.clear)
    ImageView mClear;
    Unbinder unbinder;

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
    protected int returnContentView() {
        return R.layout.fragment_qiankuan;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new PaybackAdapter(new ArrayList<DebitOrder.DetailBean>()));
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.call_phone:
                        Utils.dialSomeone(getActivity(), ((DebitOrder.DetailBean) baseQuickAdapter.getData().get(i)).getReceiveTel().trim());
                        break;
                    case R.id.see_detail:
                        Intent intent = new Intent(getActivity(), JijieDetailActivity.class);
                        intent.putExtra("order", ((DebitOrder.DetailBean) baseQuickAdapter.getData().get(i)).getOrderId());
                        startActivity(intent);
                        break;
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                mToTopFb.setVisibility(View.VISIBLE);


            }
        });

        HttpTools.getDebitList(getActivity(), this, mKeywords);

    }

    private String mKeywords = "";

    @Override
    public void onRefresh() {
        HttpTools.getDebitList(getActivity(), this, mKeywords);
    }

    private View mHeaderView;
    @Override
    protected void initView(Bundle savedInstanceState) {
        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.offline_debit_header,null);
        ((PaybackAdapter) mRecyclerView.getAdapter()).addHeaderView(mHeaderView);
        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    mClear.setVisibility(View.GONE);
                }else {
                    mClear.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        Logger.d(JsonUtils.jsonStr(bean));
        mSwipeRefresh.setRefreshing(false);
        ((BaseQuickAdapter<DebitOrder.DetailBean>) mRecyclerView.getAdapter()).setNewData(((DebitOrder) bean.getReturnData()).getDetail());

        int total = 0;
        if (TextUtils.isEmpty(bean.getReturnTotalRecords())){
            total = 0;
        }else {
            total = Integer.parseInt(bean.getReturnTotalRecords());
        }

        if (total == 0){
            mEmptyView.setVisibility(View.VISIBLE);
        }else {
            mEmptyView.setVisibility(View.GONE);
        }

        ((TextView) mHeaderView.findViewById(R.id.total_orders)).setText( Utils.getInserBoldText(8,"积借欠款总票数：" + ((DebitOrder) bean.getReturnData()).getTotalMessage().getTotalRec() + " 票"));

        ((TextView) mHeaderView.findViewById(R.id.overdue_30)).setText(Utils.getInserBoldText(0,((DebitOrder) bean.getReturnData()).getTotalMessage().getTotalOver30() + "票"));
        ((TextView) mHeaderView.findViewById(R.id.overdue_15)).setText(Utils.getInserBoldText(0,((DebitOrder) bean.getReturnData()).getTotalMessage().getTotalOver15() + "票"));
        ((TextView) mHeaderView.findViewById(R.id.overdue_5)).setText(Utils.getInserBoldText(0,((DebitOrder) bean.getReturnData()).getTotalMessage().getTotalOver5() + "票"));
    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        mSwipeRefresh.setRefreshing(false);
    }


    @OnClick(R.id.to_top_fb)
    public void onViewClicked() {
        mRecyclerView.scrollToPosition(0);
    }


    @OnClick({R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                mKeywords = mEdtSearch.getText().toString().trim();
                onRefresh();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEdtSearch.getWindowToken(), 0);
                break;
        }
    }




    @OnClick(R.id.clear)
    public void onViewClearClicked() {
        mEdtSearch.setText("");
    }
}

